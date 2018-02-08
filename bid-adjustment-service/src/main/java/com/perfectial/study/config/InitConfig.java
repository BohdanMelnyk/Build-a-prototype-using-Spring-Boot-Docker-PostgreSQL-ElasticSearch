package com.perfectial.study.config;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.repository.BidRepository;
import com.perfectial.study.service.BidService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitConfig {

//    private final BidRepository bidRepository;
    private final BidService bidService;

    public InitConfig(BidService bidService) {
//        this.bidRepository = bidRepository;
        this.bidService = bidService;
    }

    @PostConstruct
    public void insertDataSample() {
        bidService.add(getRandomBids());
    }

    private List<Bid> getRandomBids() {
        List<Bid> bids = new ArrayList<>();
        for(int i=0;i<10;i++){
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
