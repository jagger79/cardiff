package cz.rkr.cardiff.repositories;

import cz.rkr.cardiff.domain.entities.Customer;
import cz.rkr.cardiff.domain.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("from Subscription s" +
            " join s.quotation q" +
            " join q.customer c" +
            " where c = :customer")
    Optional<Subscription> findByCustomer(Customer customer);
}