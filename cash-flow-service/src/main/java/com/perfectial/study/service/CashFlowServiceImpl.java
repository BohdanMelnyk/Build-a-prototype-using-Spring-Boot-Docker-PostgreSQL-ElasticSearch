package com.perfectial.study.service;

import com.perfectial.study.repository.CashFlowRepository;
import org.springframework.stereotype.Service;

/**
 * Created by bomel on 1/23/2018.
 */
@Service
public class CashFlowServiceImpl implements CashFlowService {

    private final CashFlowRepository cashFlowRepository;

    public CashFlowServiceImpl(CashFlowRepository cashFlowRepository) {
        this.cashFlowRepository = cashFlowRepository;
    }

}
