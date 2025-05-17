package co.edu.unbosque.traderbosque.repository;

import co.edu.unbosque.traderbosque.model.entity.SubscriptionPersonalized;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<SubscriptionPersonalized, Integer> {
    Optional<SubscriptionPersonalized> findBySubId(String subId);
}
