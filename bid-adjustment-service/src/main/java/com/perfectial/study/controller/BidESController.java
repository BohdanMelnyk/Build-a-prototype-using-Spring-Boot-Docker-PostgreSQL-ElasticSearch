package com.perfectial.study.controller;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.dto.BidDTO;
import com.perfectial.study.service.ESService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/ba/rest/elasticsearch")
public class BidESController {
    private final ModelMapper modelMapper;
    private final ESService esService;

    public BidESController(ModelMapper modelMapper, ESService esService) {
        this.modelMapper = modelMapper;
        this.esService = esService;
    }
    @GetMapping("/findByUserName")
    public List<BidDTO> findByUserName(@RequestParam String userName){
        List<Bid> bids = esService.findByUserName(userName);
        return bids.stream().map(bid ->modelMapper.map(bid,BidDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/findByUserNameES")
    public Page<Bid> findByUserNameES(@RequestParam String userName){
        return esService.findByUserName(userName, PageRequest.of(0,10));
    }

    @GetMapping("/findByStake")
    public List<BidDTO> findByStake(@RequestParam BigDecimal stake){
        List<Bid> bids = esService.findByStake(stake);
        return bids.stream().map(bid ->modelMapper.map(bid,BidDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/findByStakeES")
    public Page<Bid> findByStakeES(@RequestParam BigDecimal stake){
        return esService.findByStake(stake, PageRequest.of(0,10));
    }

    @GetMapping("/findByAddedDate")
    public List<BidDTO> findAddedDate(@RequestParam LocalDateTime localDateTime){
        List<Bid> bids = esService.findByAddedDate(localDateTime);
        return bids.stream().map(bid ->modelMapper.map(bid,BidDTO.class)).collect(Collectors.toList());
    }

    @GetMapping("/findByAddedDateES")
    public Page<Bid> findByAddedDateES(@RequestParam LocalDateTime localDateTime){
        return esService.findByAddedDate(localDateTime, PageRequest.of(0,10));
    }
}
