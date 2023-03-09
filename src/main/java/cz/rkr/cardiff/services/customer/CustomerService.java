package cz.rkr.cardiff.services.customer;

import cz.rkr.cardiff.domain.customer.CustomerCreate;
import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.customer.CustomerPatch;
import cz.rkr.cardiff.domain.entities.Customer;
import cz.rkr.cardiff.exceptions.CustomerFound;
import cz.rkr.cardiff.exceptions.CustomerNotFound;
import cz.rkr.cardiff.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
@Validated
public class CustomerService {
    private final CustomerRepository repo;

    public Customer create(@NotNull CustomerCreate in) {
        Optional<Customer> existing = detail(new CustomerDetailFilter(in.birthDate()));
        if (existing.isPresent()) {
            throw new CustomerFound("already exist");
        }

        Customer entity = new Customer();
        entity.setBirthDate(in.birthDate());
        entity.setFirstName(in.firstName());
        entity.setLastName(in.lastName());
        entity.setMiddleName(in.middleName());
        entity.setPhoneNumber(in.phoneNumber());
        entity.setEmail(in.email());
        return repo.saveAndFlush(entity);
    }

    public Customer get(@NotNull CustomerDetailFilter in) {
        Optional<Customer> entity = detail(new CustomerDetailFilter(in.birthDate()));
        if (entity.isEmpty()) {
            throw new CustomerNotFound("birthDate=" + in.birthDate());
        }
        return entity.get();
    }

    private Optional<Customer> detail(@NotNull CustomerDetailFilter in) {
        return repo.findByBirthDate(in.birthDate());
    }

    public void patch(@NotNull CustomerPatch in) {
        Customer entity = get(new CustomerDetailFilter(in.birthDate()));

        if (in.email().isPresent()) {
            entity.setEmail(in.email().get());
        }
        if (in.firstName().isPresent()) {
            entity.setFirstName(in.firstName().get());
        }
        if (in.lastName().isPresent()) {
            entity.setLastName(in.lastName().get());
        }
        if (in.middleName().isPresent()) {
            entity.setMiddleName(in.middleName().get());
        }
        if (in.phoneNumber().isPresent()) {
            entity.setPhoneNumber(in.phoneNumber().get());
        }
        repo.saveAndFlush(entity);
    }
}