package com.bluebook.restservices.bluebook.repository;

import com.bluebook.restservices.bluebook.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
