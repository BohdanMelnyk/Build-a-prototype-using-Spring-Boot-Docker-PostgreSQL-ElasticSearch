package com.perfectial.study.service;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.domain.BidES;
import com.perfectial.study.repository.BidRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BidServiceImpl implements BidService {

    private final BidRepository bidRepository;
    private final SendBidService sendBidService;
    private final ESService esService;
    private final ModelMapper modelMapper;

    public BidServiceImpl(BidRepository bidRepository, SendBidService sendBidService, ESService esService, ModelMapper modelMapper) {
        this.bidRepository = bidRepository;
        this.sendBidService = sendBidService;
        this.esService = esService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Bid> findAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public Bid add(Bid bid) {
        Bid savedBid = save(bid);
        sendBidService.sendBidToQueue(savedBid);
        BidES bidES = modelMapper.map(savedBid,BidES.class);
        esService.add(bidES);
        return savedBid;
    }

    @Override
    public List<Bid> add(List<Bid> bids) {
        return bids.stream().map(bid -> add(bid)).collect(Collectors.toList());
    }

    @Override
    public Bid addTestBid() {
        Bid bid = new Bid();
        bid.setId(12L);
        bid.setUserName("Test User");
        bid.setStake(BigDecimal.valueOf(0.123d));
        bid.setAddedDate(LocalDateTime.now());
        bid.setLoggedDate(LocalDateTime. now());
        return add(bid);
    }

    @Transactional
    private Bid save(Bid bid){
        return bidRepository.save(bid);
    }

}
