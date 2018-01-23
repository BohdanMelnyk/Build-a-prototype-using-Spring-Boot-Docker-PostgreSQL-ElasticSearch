package com.perfectial.study.domain;

/**
 * Created by bomel on 1/23/2018.
 */
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(indexName = "bid-index", type = "bid-type", shards = 1, replicas = 0, refreshInterval = "-1")
public class Bid{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private BigDecimal stake;
    @Field(type = FieldType.Date) private LocalDateTime addedDate;
    @Field(type = FieldType.Date) private LocalDateTime loggedDate;

    public Bid(String userName, BigDecimal stake, LocalDateTime addedDate) {
        this.userName = userName;
        this.stake = stake;
        this.addedDate = addedDate;
    }
}
