package com.test.postgresql_test.config.auth;

import com.test.postgresql_test.domain.Entity.Users;
import com.test.postgresql_test.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users principal = usersRepository.findByUsername(username).orElseThrow(() -> {
            throw new IllegalArgumentException(String.format("UserName : %s 으로 Users를 찾을 수 없습니다.", username));
        });
        return new PrincipalDetails(principal);
    }
}
