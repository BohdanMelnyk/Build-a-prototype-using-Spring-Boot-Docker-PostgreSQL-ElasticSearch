package com.perfectial.study.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by bomel on 2/5/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CashFlowDTO {
    
    private Long id;
    private String userName;
    private BigDecimal previousBalance;
    private BigDecimal currentBalance;
    private BigDecimal stake;
    private LocalDateTime updatedDate;
}
