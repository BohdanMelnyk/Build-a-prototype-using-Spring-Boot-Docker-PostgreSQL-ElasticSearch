package com.perfectial.study.resource;

import com.perfectial.study.service.CashFlowService;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bomel on 1/23/2018.
 */
@RestController
public class CashFlowController {
    private final CashFlowService cashFlowService;

    public CashFlowController(CashFlowService cashFlowService) {
        this.cashFlowService = cashFlowService;
    }

}
