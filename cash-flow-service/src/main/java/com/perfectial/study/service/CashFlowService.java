package com.perfectial.study.service;

import com.perfectial.study.domain.CashFlow;
import com.perfectial.study.dto.CashFlowDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by bomel on 1/23/2018.
 */
public interface CashFlowService {

    Optional<CashFlow> findFirstByUserNameOrderByUpdatedDateDesc(String userName);

    List<CashFlow> findByUserNameOrderByUpdatedDateDesc(String userName);

    CashFlow addCashFlow(CashFlow cashFlow);

    Optional<CashFlow> getById(Long id);
}
