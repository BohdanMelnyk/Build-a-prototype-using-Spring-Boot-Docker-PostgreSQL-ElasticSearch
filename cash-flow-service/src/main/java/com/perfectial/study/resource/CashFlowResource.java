package com.perfectial.study.resource;

import com.perfectial.study.service.CashFlowService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bomel on 1/23/2018.
 */
@RestController
public class CashFlowResource {
    private final CashFlowService cashFlowService;

    public CashFlowResource(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
    }

}
