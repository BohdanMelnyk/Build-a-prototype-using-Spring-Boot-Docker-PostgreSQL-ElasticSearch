package com.perfectial.study.service.redisqueue;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.domain.UserCashFlow;
import com.perfectial.study.repository.UserCashFlowRepository;
import com.perfectial.study.service.CashFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final UserCashFlowRepository userCashFlowRepository;

    public ProcessingBidFromQueueServiceImpl(SubscribeRedisQueueService subscribeRedisQueueService, CashFlowService cashFlowService, UserCashFlowRepository userCashFlowRepository) {
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
        Optional<UserCashFlow> userBalanceOptional = cashFlowService.findFirstByUserNameOrderByUpdatedDateDesc(bid.getUserName());
        if (userBalanceOptional.isPresent()) {
            UserCashFlow userCashFlow = userBalanceOptional.get();
            userCashFlow.setBalance(userCashFlow.getBalance().add(bid.getStake()));
            userCashFlow.setStake(bid.getStake());
            userCashFlow.setUpdatedDate(LocalDateTime.now());
            userCashFlowRepository.save(userCashFlow);
        } else {
            userCashFlowRepository.save(new UserCashFlow(bid.getUserName(), bid.getStake(), bid.getStake(), LocalDateTime.now()));
            log.info("New UserCashFlow was created: user name is " + bid.getUserName());

        }
    }
}
