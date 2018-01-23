package com.perfectial.study.bootstrap;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.repository.CashFlowRepository;
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

    private final CashFlowRepository cashFlowRepository;

    public CashFlowBootstrap(CashFlowRepository cashFlowRepository){
        this.cashFlowRepository = cashFlowRepository;
    }
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        cashFlowRepository.saveAll(getRandomBids());
        log.info("Loading Bootstrap Data");
    }

    private List<Bid> getRandomBids() {
        List<Bid> bids = new ArrayList<>();
//        do{
//            Random rand = new Random();
//            Bid randomBid = new Bid();
//            randomBid.setUserName("User N = " +  rand.nextInt(100));
//            randomBid.setStake(BigDecimal.valueOf(rand.nextDouble()));
//            randomBid.setAddedDate(LocalDateTime.now());
//            randomBid.setLoggedDate(LocalDateTime.now());
//            bids.add(randomBid);
//        } while(bids.size()<11);
        return bids;
    }
}
