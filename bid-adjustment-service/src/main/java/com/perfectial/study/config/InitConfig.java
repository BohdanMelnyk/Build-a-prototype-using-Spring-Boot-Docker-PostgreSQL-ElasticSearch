package com.perfectial.study.config;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by bomel on 1/24/2018.
 */
@Component
public class InitConfig {

    @Autowired
    private BidRepository bidRepository;

    @PostConstruct
    public void insertDataSample() {
//        bidRepository.deleteAll();
        bidRepository.saveAll(getRandomBids());
    }

    private List<Bid> getRandomBids() {
        List<Bid> bids = new ArrayList<>();
        for(int i=0;i<11;i++){
            Random rand = new Random();
            Bid randomBid = new Bid();
            randomBid.setUserName("User N = " +  rand.nextInt(100));
            randomBid.setStake(BigDecimal.valueOf(rand.nextDouble()));
            randomBid.setAddedDate(LocalDateTime.now());
            randomBid.setLoggedDate(LocalDateTime.now());
            bids.add(randomBid);
        }
        return bids;
    }
}
