package com.perfectial.study.service;

import com.perfectial.study.domain.UserCashFlow;
import com.perfectial.study.repository.UserCashFlowRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by bomel on 1/23/2018.
 */
@Service
public class CashFlowServiceImpl implements CashFlowService {

    private final UserCashFlowRepository cashFlowRepository;

    public CashFlowServiceImpl(UserCashFlowRepository cashFlowRepository) {
        this.cashFlowRepository = cashFlowRepository;
    }

    @Override
    public Optional<UserCashFlow> findFirstByUserNameOrderByUpdatedDateDesc(String userName) {
        return cashFlowRepository.findFirstByUserNameOrderByUpdatedDateDesc(userName);
    }
}
