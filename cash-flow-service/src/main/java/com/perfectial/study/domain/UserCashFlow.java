package com.perfectial.study.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by bomel on 1/26/2018.
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCashFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    @NonNull
    private String userName;

    @Column(name = "balance")
    @NonNull
    private BigDecimal balance;

    @Column(name = "stake")
    @NonNull
    private BigDecimal stake;

    @Column(name = "lastUpdate")
    @NonNull
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updatedDate;

    public UserCashFlow(String userName, BigDecimal balance, BigDecimal stake, LocalDateTime updatedDate) {
        this.userName = userName;
        this.balance = balance;
        this.stake = stake;
        this.updatedDate = updatedDate;
    }
}
