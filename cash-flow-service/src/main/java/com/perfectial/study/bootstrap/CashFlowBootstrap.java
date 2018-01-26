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
import java.util.*;
import java.util.stream.Collectors;

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
            userCashFlow.setPreviousBalance(getCurrentBalance(userCashFlows,userCashFlow.getUserName()));
            userCashFlow.setCurrentBalance(userCashFlow.getPreviousBalance().add(userCashFlow.getStake()));
            userCashFlow.setUpdatedDate(LocalDateTime.now());
            userCashFlows.add(userCashFlow);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(userCashFlows.size()<100);
        return userCashFlows;
    }

    private BigDecimal getCurrentBalance(List<UserCashFlow> userCashFlows, String userName) {
        if(!userCashFlows.stream().map(UserCashFlow::getUserName).collect(Collectors.toList()).contains(userName)){
            return BigDecimal.ZERO;
        } else{
            List<UserCashFlow> alredyExistUserCashFlows = userCashFlows.stream().filter(e->e.getUserName().equals(userName)).collect(Collectors.toList());
            Collections.sort(alredyExistUserCashFlows, new Comparator<UserCashFlow>() {
                        @Override
                        public int compare(UserCashFlow o1, UserCashFlow o2)
                        {
                            return o2.getUpdatedDate().compareTo(o1.getUpdatedDate());
                        }
                    }
            );
            return alredyExistUserCashFlows.get(0).getCurrentBalance();
        }
    }
}
