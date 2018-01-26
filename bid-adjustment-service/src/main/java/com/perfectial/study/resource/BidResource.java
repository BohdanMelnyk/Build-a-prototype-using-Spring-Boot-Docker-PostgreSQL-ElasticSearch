package com.perfectial.study.resource;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.service.BidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * Created by bomel on 1/23/2018.
 */
@Slf4j
@RestController
public class BidResource {
    private final BidService bidService;

    public BidResource(BidService bidService) {
        this.bidService = bidService;
    }

//    @GetMapping({"", "/", "/index"})
//    public String getIndexPage(Model model) {
//        log.debug("Getting Index page");
//
//        model.addAttribute("bids", bidService.findAllBids());
//
//        return "index";
//    }
    @GetMapping("/rest/allBids")
    public Iterable<Bid> getAllBids(){
        return bidService.findAllBids();
    }

    @PostMapping("/rest/addBid")
    public Bid addBid(@ModelAttribute("bid") Bid bid){
        if (bid != null){
            bid.setLoggedDate(LocalDateTime.now());
            Bid savedBid = bidService.addBid(bid);
            return savedBid;
        } else{
            return null;
        }
    }
    @GetMapping("/rest/addSomeTestBid")
    public Bid addSomeTestBid(){
        Bid bid = new Bid();
            bid.setId(12l);
            bid.setUserName("Test User");
            bid.setStake(BigDecimal.valueOf(0.123d));
            bid.setAddedDate(LocalDateTime.now());
            bid.setLoggedDate(LocalDateTime. now());
            return bidService.addBid(bid);
    }

    @PostMapping("/rest/bidAdjustment")
    public Bid bidAdjustment(@RequestBody Bid bid){
        bid.setLoggedDate(LocalDateTime.now());
        return bidService.addBid(bid);
    }
}
