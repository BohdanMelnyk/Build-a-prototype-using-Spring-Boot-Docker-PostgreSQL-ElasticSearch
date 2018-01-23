package com.perfectial.study.resource;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.service.BidService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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

//    @GetMapping("/bids")
//    public Iterable<Bid> getBids(){
//        return bidService.findAllBids();
//    }
    @GetMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        log.debug("Getting Index page");

        model.addAttribute("bids", bidService.findAllBids());

        return "index";
    }

    @PostMapping("addBid")
    public Bid addBid(@ModelAttribute("bid") Bid bid){
        if (bid != null){
            bid.setLoggedDate(LocalDateTime.now());
            Bid savedBid = bidService.addBid(bid);
            return savedBid;
        } else{
            return null;
        }
    }

}
