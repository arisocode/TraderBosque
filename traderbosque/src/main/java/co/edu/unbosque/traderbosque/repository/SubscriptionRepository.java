package co.edu.unbosque.traderbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.unbosque.traderbosque.model.entity.SubscriptionPersonalized;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionPersonalized, Integer> {
    Optional<SubscriptionPersonalized> findBySubId(String subId);
}
