package com.perfectial.study.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(indexName = "bids", type = "bids")
//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "bid")
public class Bid implements Serializable{

    private static final long serialVersionUID = -3009157732242241606L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    @NonNull
    @Field(type = FieldType.text, store = true)
    private String userName;


    @Column(name = "stake")
    @NonNull
    @Field(type = FieldType.Double, store = true)
    private BigDecimal stake;

    @Column(name = "addedDate")
    @Field(type = FieldType.Date, store = true)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NonNull
    private LocalDateTime addedDate;

    @Column(name = "loggedDate")
    @Field(type = FieldType.Date, store = true)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime loggedDate;

    public Bid(String userName, BigDecimal stake, LocalDateTime addedDate) {
        this.userName = userName;
        this.stake = stake;
        this.addedDate = addedDate;
    }
}
