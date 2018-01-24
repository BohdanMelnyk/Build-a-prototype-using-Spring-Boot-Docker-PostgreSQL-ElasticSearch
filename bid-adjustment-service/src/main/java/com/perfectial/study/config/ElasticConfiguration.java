package com.perfectial.study.config;

import com.perfectial.study.domain.Bid;
import com.perfectial.study.repository.BidRepository;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.NodeClientFactoryBean;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.perfectial.study.repository.")
public class ElasticConfiguration {

    @Autowired
    private ElasticsearchOperations operations;
    @Autowired
    private BidRepository bidRepository;

//    public ElasticConfiguration(ElasticsearchOperations operations, BidRepository bidRepository) {
//        this.operations = operations;
//        this.bidRepository = bidRepository;
//    }

    @Bean
    public NodeClientFactoryBean client() {

        NodeClientFactoryBean bean = new NodeClientFactoryBean(true);
        bean.setClusterName(UUID.randomUUID().toString());
        bean.setEnableHttp(false);
        bean.setPathData("target/elasticsearchTestData");
        bean.setPathHome("src/test/resources/test-home-dir");

        return bean;
    }

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) throws Exception {
        return new ElasticsearchTemplate(client);
    }

    @PreDestroy
    public void deleteIndex() {
        operations.deleteIndex(Bid.class);
    }
}