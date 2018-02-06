package com.perfectial.study.service.redisqueue;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.domain.CashFlow;
import com.perfectial.study.repository.CashFlowRepository;
import com.perfectial.study.service.CashFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by bomel on 1/25/2018.
 */
@Slf4j
@Service
public class ProcessingBidFromQueueServiceImpl implements ProcessingBidFromQueueService {

    private final SubscribeRedisQueueService subscribeRedisQueueService;
    private final CashFlowService cashFlowService;
    private final CashFlowRepository userCashFlowRepository;

    public ProcessingBidFromQueueServiceImpl(SubscribeRedisQueueService subscribeRedisQueueService, CashFlowService cashFlowService, CashFlowRepository userCashFlowRepository) {
        this.subscribeRedisQueueService = subscribeRedisQueueService;
        this.cashFlowService = cashFlowService;
        this.userCashFlowRepository = userCashFlowRepository;
    }

    @Override
    public void processAllBidsFromQueue() {
        Collection<Bid> bids = subscribeRedisQueueService.leftPopAllQueue();
        process(bids);
    }

    @Override
    public void processBidFromQueue() {
        Bid  bid = subscribeRedisQueueService.leftPop();
        process(bid);
    }

    @Override
    public void process(Collection<Bid> bids) {
        bids.stream().forEach(bid -> process(bid));
    }

    @Override
    public void process(Bid bid) {
        Optional<CashFlow> userBalanceOptional = cashFlowService.findFirstByUserNameOrderByUpdatedDateDesc(bid.getUserName());
        if (userBalanceOptional.isPresent()) {
            CashFlow userCashFlow = userBalanceOptional.get();
            userCashFlowRepository.save(updatedUserCashFlow(userCashFlow, bid));
        } else {
            userCashFlowRepository.save(getNewUserCashFlow(bid));
            log.info("New CashFlow was created: user name is " + bid.getUserName());
        }
    }

    private CashFlow getNewUserCashFlow(Bid bid) {
        return new CashFlow(bid.getUserName(), BigDecimal.ZERO, bid.getStake(), bid.getStake(), LocalDateTime.now());
    }

    private CashFlow updatedUserCashFlow(CashFlow userCashFlow, Bid bid) {
        CashFlow newUserCashFlow = new CashFlow();
        newUserCashFlow.setUserName(userCashFlow.getUserName());
        newUserCashFlow.setPreviousBalance(userCashFlow.getCurrentBalance());
        newUserCashFlow.setCurrentBalance(userCashFlow.getCurrentBalance().add(bid.getStake()));
        newUserCashFlow.setStake(bid.getStake());
        newUserCashFlow.setUpdatedDate(LocalDateTime.now());
        return newUserCashFlow;
    }
}
