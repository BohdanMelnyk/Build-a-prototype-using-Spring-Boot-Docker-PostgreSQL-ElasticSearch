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
    private String userName;
    @Column(name = "stake")
    private BigDecimal stake;

    @Column(name = "addedDate")
    @Field(type = FieldType.Date)
    private LocalDateTime addedDate;

    @Column(name = "loggedDate")
    @Field(type = FieldType.Date)
    private LocalDateTime loggedDate;

    public Bid(String userName, BigDecimal stake, LocalDateTime addedDate) {
        this.userName = userName;
        this.stake = stake;
        this.addedDate = addedDate;
    }
}
