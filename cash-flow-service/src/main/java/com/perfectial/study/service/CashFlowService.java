package com.perfectial.study.service;

import com.perfectial.study.domain.UserCashFlow;

import java.util.Optional;

/**
 * Created by bomel on 1/23/2018.
 */
public interface CashFlowService {

    Optional<UserCashFlow> findFirstByUserNameOrderByUpdatedDateDesc(String userName);
}
