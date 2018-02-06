package com.perfectial.study.service;

import com.perfectial.study.domain.CashFlow;
import com.perfectial.study.repository.CashFlowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CashFlowServiceImpl implements CashFlowService {

    private final CashFlowRepository cashFlowRepository;

    public CashFlowServiceImpl(CashFlowRepository cashFlowRepository) {
        this.cashFlowRepository = cashFlowRepository;
    }

    @Override
    public Optional<CashFlow> findFirstByUserNameOrderByUpdatedDateDesc(String userName) {
        return cashFlowRepository.findFirstByUserNameOrderByUpdatedDateDesc(userName);
    }

    @Override
    public List<CashFlow> findByUserNameOrderByUpdatedDateDesc(String userName) {
        return cashFlowRepository.findByUserNameOrderByUpdatedDateDesc(userName);
    }

    @Override
    public CashFlow addCashFlow(CashFlow cashFlow) {

        Optional<CashFlow> existedCashFlowOptional = findFirstByUserNameOrderByUpdatedDateDesc(cashFlow.getUserName());

        if(!existedCashFlowOptional.isPresent()){
            return cashFlowRepository.save(cashFlow);
        } else{
            CashFlow existedCashFlow = existedCashFlowOptional.get();
            CashFlow toSave = new CashFlow();
            toSave.setUserName(cashFlow.getUserName());
            toSave.setUpdatedDate(LocalDateTime.now());
            toSave.setStake(cashFlow.getStake());
            toSave.setCurrentBalance(existedCashFlow.getCurrentBalance().add(cashFlow.getCurrentBalance()));
            toSave.setPreviousBalance(existedCashFlow.getCurrentBalance());
            return cashFlowRepository.save(toSave);
        }
    }

    @Override
    public Optional<CashFlow> getById(Long id) {
        return cashFlowRepository.findById(id);
    }
}
