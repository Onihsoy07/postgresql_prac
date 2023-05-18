package com.test.postgresql_test.Service;

import com.test.postgresql_test.domain.Entity.CfrData;
import com.test.postgresql_test.domain.dto.UsersJoinDto;

import java.util.Set;

public interface UsersService {

    void join(UsersJoinDto usersJoinDto);

    Set<CfrData> findCfrAll(Long id);

}
