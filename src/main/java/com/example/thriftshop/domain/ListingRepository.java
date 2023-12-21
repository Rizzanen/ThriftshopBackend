package com.example.thriftshop.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing, Long> {
    List<Listing> findByAppUserUserId(Long userId);
}
