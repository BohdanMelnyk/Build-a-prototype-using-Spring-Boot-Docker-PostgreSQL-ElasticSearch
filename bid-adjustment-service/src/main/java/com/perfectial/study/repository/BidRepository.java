package com.perfectial.study.repository;

import com.perfectial.study.domain.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findAll();

}
