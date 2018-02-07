package com.perfectial.study.bootstrap;

import com.perfectial.study.domain.CashFlow;
import com.perfectial.study.repository.CashFlowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CashFlowBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CashFlowRepository cashFlowRepository;

    public CashFlowBootstrap(CashFlowRepository cashFlowRepository){
        this.cashFlowRepository = cashFlowRepository;
    }
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        cashFlowRepository.saveAll(getRandomUserCashFlow());
        log.info("Loading Bootstrap Data");
    }

    private List<CashFlow> getRandomUserCashFlow() {
        List<CashFlow> cashFlows = new ArrayList<>();
        do{
            Random rand = new Random();
            CashFlow cashFlow = new CashFlow();
            cashFlow.setUserName("User N = " +  rand.nextInt(10));
            cashFlow.setStake(BigDecimal.valueOf(rand.nextDouble()));
            cashFlow.setPreviousBalance(getCurrentBalance(cashFlows,cashFlow.getUserName()));
            cashFlow.setCurrentBalance(cashFlow.getPreviousBalance().add(cashFlow.getStake()));
            cashFlow.setUpdatedDate(LocalDateTime.now());
            cashFlows.add(cashFlow);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(cashFlows.size()<100);
        return cashFlows;
    }

    private BigDecimal getCurrentBalance(List<CashFlow> userCashFlows, String userName) {
        if(!userCashFlows.stream().map(CashFlow::getUserName).collect(Collectors.toList()).contains(userName)){
            return BigDecimal.ZERO;
        } else{
            List<CashFlow> alredyExistCashFlows = userCashFlows.stream().filter(e->e.getUserName().equals(userName)).collect(Collectors.toList());
            Collections.sort(alredyExistCashFlows, (o1, o2) -> o2.getUpdatedDate().compareTo(o1.getUpdatedDate())
            );
            return alredyExistCashFlows.get(0).getCurrentBalance();
        }
    }
}
