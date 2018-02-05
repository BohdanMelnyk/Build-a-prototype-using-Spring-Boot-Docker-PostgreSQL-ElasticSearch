package com.perfectial.study.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;
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

        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @NonNull
        private LocalDateTime addedDate;

        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        private LocalDateTime loggedDate;

        public BidDTO(String userName, BigDecimal stake, LocalDateTime addedDate) {
            this.userName = userName;
            this.stake = stake;
            this.addedDate = addedDate;
        }
}
