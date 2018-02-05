package com.perfectial.study.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by bomel on 2/5/2018.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BidDTO implements Serializable {
    private static final long serialVersionUID = -3009157732242241606L;

    private Long id;
    private String userName;
    private BigDecimal stake;
    private LocalDateTime addedDate;
    private LocalDateTime loggedDate;
}