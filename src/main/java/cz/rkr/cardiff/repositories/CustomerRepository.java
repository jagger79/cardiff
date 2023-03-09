package cz.rkr.cardiff.repositories;

import cz.rkr.cardiff.domain.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //    @Query("from Customer f" +
//            // left outer since there may no be any attributes
//            " left join fetch f.attributes" +
//            " where f.extId = :extId")
//    Optional<Form> findByExtIdFetched(String extId);
    Optional<Customer> findByBirthDate(LocalDate birthDate);
}