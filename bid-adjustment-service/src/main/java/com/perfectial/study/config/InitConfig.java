package com.perfectial.study.config;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.repository.BidRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitConfig {

    private final BidRepository bidRepository;

    public InitConfig(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @PostConstruct
    public void insertDataSample() {
        bidRepository.saveAll(getRandomBids());
    }

    private List<Bid> getRandomBids() {
        List<Bid> bids = new ArrayList<>();
        for(int i=0;i<1000;i++){
            Random rand = new Random();
            Bid randomBid = new Bid();
            randomBid.setUserName("user " +  rand.nextInt(10));
            randomBid.setStake(BigDecimal.valueOf(rand.nextDouble()));
            randomBid.setAddedDate(LocalDateTime.now());
            randomBid.setLoggedDate(LocalDateTime.now());
            bids.add(randomBid);
        }
        return bids;
    }
}
