package com.perfectial.study.repository;

import com.perfectial.study.domain.UserCashFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

/**
 * Created by bomel on 1/23/2018.
 */
@RepositoryRestResource
public interface UserCashFlowRepository extends JpaRepository<UserCashFlow, Long> {
    Optional<UserCashFlow> findFirstByUserNameOrderByUpdatedDateDesc(String userName);
}
