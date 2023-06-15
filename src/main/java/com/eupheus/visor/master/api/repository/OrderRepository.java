package com.eupheus.visor.master.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.accelerate.visor.school.model.Order;




@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}