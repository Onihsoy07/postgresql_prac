package com.test.postgresql_test.Service.ServiceImpl;

import com.test.postgresql_test.Service.UsersService;
import com.test.postgresql_test.domain.Entity.Role;
import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.dto.UsersJoinDto;
import com.test.postgresql_test.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
}
