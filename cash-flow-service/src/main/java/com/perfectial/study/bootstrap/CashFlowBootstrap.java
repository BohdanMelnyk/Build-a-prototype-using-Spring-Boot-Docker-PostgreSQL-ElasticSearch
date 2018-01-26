package com.perfectial.study.bootstrap;

import com.perfectial.study.domain.UserCashFlow;
import com.perfectial.study.repository.UserCashFlowRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bomel on 1/23/2018.
 */
@Slf4j
@Component
public class CashFlowBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserCashFlowRepository userCashFlowRepository;

    public CashFlowBootstrap(UserCashFlowRepository userCashFlowRepository){
        this.userCashFlowRepository = userCashFlowRepository;
    }
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        userCashFlowRepository.saveAll(getRandomUserCashFlow());
        log.info("Loading Bootstrap Data");
    }

    private List<UserCashFlow> getRandomUserCashFlow() {
        List<UserCashFlow> userCashFlows = new ArrayList<>();
        do{
            Random rand = new Random();
            UserCashFlow userCashFlow = new UserCashFlow();
            userCashFlow.setUserName("User N = " +  rand.nextInt(10));
            userCashFlow.setStake(BigDecimal.valueOf(rand.nextDouble()));
            userCashFlow.setBalance(BigDecimal.valueOf(rand.nextDouble()));
            userCashFlow.setUpdatedDate(LocalDateTime.now());
            userCashFlows.add(userCashFlow);
        } while(userCashFlows.size()<100);
        return userCashFlows;
    }
}
