package com.perfectial.study.domain;

/**
 * Created by bomel on 1/23/2018.
 */
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Document(indexName = "bid-index", type = "bid-type", shards = 1, replicas = 0, refreshInterval = "-1")
@Table(name = "bid")
public class Bid implements Serializable{

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    @NonNull
    private String userName;

    @Column(name = "stake")
    @NonNull
    private BigDecimal stake;

    @Column(name = "addedDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NonNull
    private LocalDateTime addedDate;

    @Column(name = "loggedDate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime loggedDate;

    public Bid(String userName, BigDecimal stake, LocalDateTime addedDate) {
        this.userName = userName;
        this.stake = stake;
        this.addedDate = addedDate;
    }
}