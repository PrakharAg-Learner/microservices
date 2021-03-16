package com.learning.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.api.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
