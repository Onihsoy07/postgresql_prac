package com.test.postgresql_test.Service.ServiceImpl;

import com.test.postgresql_test.Service.UsersService;
import com.test.postgresql_test.config.jwt.TokenProvider;
import com.test.postgresql_test.domain.Entity.CfrData;
import com.test.postgresql_test.domain.Entity.Role;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.JwtTokenDto;
import com.test.postgresql_test.domain.dto.UsersJoinDto;
import com.test.postgresql_test.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public JwtTokenDto login(String id, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(id, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        JwtTokenDto tokenInfo = tokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    @Override
    @Transactional
    public void join(UsersJoinDto usersJoinDto) {
        Users users = new Users().builder()
                .username(usersJoinDto.getUsername())
                .password(bCryptPasswordEncoder.encode(usersJoinDto.getPassword()))
                .email(usersJoinDto.getEmail())
                .role(Role.USER)
                .build();
        usersRepository.save(users);
    }

    @Transactional(readOnly = true)
    public Set<CfrData> findCfrAll(Long id) {
        Users users = usersRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("아이디를 찾을 수 없음");
        });
        return users.getCfrDataList();
    }

    @Transactional(readOnly = true)
    public Users findById(Long id) {
        return usersRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("user 찾을 수 없음");
        });
    }
}
