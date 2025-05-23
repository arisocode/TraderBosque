package co.edu.unbosque.traderbosque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.traderbosque.model.entity.Holding;
import co.edu.unbosque.traderbosque.model.entity.User;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Integer> {
    List<Holding> findByUserId(User user);
    Optional<Holding> findByUserIdAndSymbol(User user, String symbol);
}
