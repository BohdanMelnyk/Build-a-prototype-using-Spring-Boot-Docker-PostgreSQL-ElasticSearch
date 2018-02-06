package com.perfectial.study.controller;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.dto.BidDTO;
import com.perfectial.study.service.BidService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class BidController {
    private final BidService bidService;
    private final ModelMapper modelMapper;

    public BidController(BidService bidService, ModelMapper modelMapper) {
        this.bidService = bidService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/rest/allBids")
    public List<BidDTO> getAllBids(){
        List<Bid> bids = bidService.findAllBids();
        List<BidDTO> bidDTOs = bids.stream().map(bid->modelMapper.map(bid,BidDTO.class)).collect(Collectors.toList());
        return bidDTOs;
    }

    @PostMapping("/rest/addBid")
    public BidDTO addBid(@ModelAttribute("bidDTO") @NonNull BidDTO bidDTO){
        bidDTO.setLoggedDate(LocalDateTime.now());
        Bid savedBid = bidService.addBid(modelMapper.map(bidDTO, Bid.class));
        return modelMapper.map(savedBid, BidDTO.class);
    }

    @PostMapping("/rest/bidAdjustment")
    public BidDTO bidAdjustment(@RequestBody BidDTO bidDTO){
        Bid bid = modelMapper.map(bidDTO, Bid.class);
        bid.setLoggedDate(LocalDateTime.now());
        Bid addedBid =  bidService.addBid(bid);
        return modelMapper.map(addedBid, BidDTO.class);
    }

    @GetMapping("/rest/addTestBid")
    public BidDTO addTestBid(){
        Bid testBid = bidService.addTestBid();
        return modelMapper.map(testBid, BidDTO.class);
    }

}
