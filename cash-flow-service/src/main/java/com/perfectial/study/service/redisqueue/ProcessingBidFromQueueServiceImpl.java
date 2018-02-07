package com.perfectial.study.service.redisqueue;

import com.perfectial.study.domain.CashFlow;
import com.perfectial.study.dto.BidDTO;
import com.perfectial.study.repository.CashFlowRepository;
import com.perfectial.study.service.CashFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

@Slf4j
@Service
public class ProcessingBidFromQueueServiceImpl implements ProcessingBidFromQueueService {

    private final SubscribeRedisQueueService subscribeRedisQueueService;
    private final CashFlowService cashFlowService;

    public ProcessingBidFromQueueServiceImpl(SubscribeRedisQueueService subscribeRedisQueueService, CashFlowService cashFlowService) {
        this.subscribeRedisQueueService = subscribeRedisQueueService;
        this.cashFlowService = cashFlowService;
    }

    @Override
    public void processAllBidsFromQueue() {
        Collection<BidDTO> bidDTOs = subscribeRedisQueueService.leftPopAllQueue();
        process(bidDTOs);
    }

    @Override
    public void processBidFromQueue() {
        BidDTO  bidDTO = subscribeRedisQueueService.leftPop();
        process(bidDTO);
    }

    @Override
    public void process(Collection<BidDTO> bidDTOs) {
        bidDTOs.forEach(bidDTO -> process(bidDTO));
    }

    @Override
    public void process(BidDTO bidDTO) {
        CashFlow cashFlow = new CashFlow();
        cashFlow.setUserName(bidDTO.getUserName());
        cashFlow.setStake(bidDTO.getStake());
        cashFlow.setCurrentBalance(bidDTO.getStake());
        cashFlow.setPreviousBalance(bidDTO.getStake());
        cashFlow.setUpdatedDate(LocalDateTime.now());
        cashFlowService.addCashFlow(cashFlow);
    }

}
