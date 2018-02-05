package com.perfectial.study.controller;

import com.perfectial.study.domain.CashFlow;
import com.perfectial.study.dto.CashFlowDTO;
import com.perfectial.study.service.CashFlowService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.awt.font.OpenType;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by bomel on 1/23/2018.
 */
@RestController
public class CashFlowController {
    private final CashFlowService cashFlowService;
    private final ModelMapper modelMapper;

    public CashFlowController(CashFlowService cashFlowService, ModelMapper modelMapper) {
        this.cashFlowService = cashFlowService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/rest/getCashFlowsByUserName")
    public List<CashFlowDTO> getCashFlowsByUserName(@RequestParam String userName){
        List<CashFlow> cashFlows = cashFlowService.findByUserNameOrderByUpdatedDateDesc(userName);
        List<CashFlowDTO> cashFlowDTOS = cashFlows.stream().map(cashFlow -> modelMapper.map(cashFlow, CashFlowDTO.class)).collect(Collectors.toList());
        return cashFlowDTOS;
    }

    @GetMapping("/rest/getCashFlowByUserName")
    public CashFlowDTO getCashFlowByUserName(@RequestParam String userName){
        CashFlow cashFlow = cashFlowService.findFirstByUserNameOrderByUpdatedDateDesc(userName).get();
        return modelMapper.map(cashFlow, CashFlowDTO.class);
    }

    @PostMapping("/rest/addCashFlow")
    public CashFlow addCashFlow(@RequestBody CashFlowDTO cashFlowDTO) {
        CashFlow cashFlow = modelMapper.map(cashFlowDTO, CashFlow.class);
        return cashFlowService.addCashFlow(cashFlow);
    }
}
