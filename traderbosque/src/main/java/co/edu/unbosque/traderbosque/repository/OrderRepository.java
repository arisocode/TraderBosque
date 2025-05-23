package co.edu.unbosque.traderbosque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.traderbosque.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
