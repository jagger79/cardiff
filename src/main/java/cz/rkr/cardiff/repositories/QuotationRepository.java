package cz.rkr.cardiff.repositories;

import cz.rkr.cardiff.domain.entities.Customer;
import cz.rkr.cardiff.domain.entities.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    Optional<Quotation> findByCustomer(Customer customer);
}