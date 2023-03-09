package cz.rkr.cardiff.services.quotation;

import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.entities.Customer;
import cz.rkr.cardiff.domain.entities.Quotation;
import cz.rkr.cardiff.domain.quotation.QuotationCreate;
import cz.rkr.cardiff.domain.quotation.QuotationDetail;
import cz.rkr.cardiff.exceptions.QuotationFound;
import cz.rkr.cardiff.exceptions.QuotationNotFound;
import cz.rkr.cardiff.repositories.QuotationRepository;
import cz.rkr.cardiff.services.customer.CustomerService;
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
public class QuotationService {
    private final QuotationRepository repo;
    private final CustomerService customerService;

    public Quotation create(@NotNull QuotationCreate in) {
        Customer customer = customerService.get(new CustomerDetailFilter(in.customerBirthDate()));
        Optional<Quotation> existing = detail(customer);
        if (existing.isPresent()) {
            throw new QuotationFound("already exist");
        }

        Quotation entity = new Quotation();
        entity.setCustomer(customer);
        entity.setInsuredAmount(in.insuredAmount());
        entity.setBeginningOfInsurance(in.beginningOfInsurance());
        entity.setDateOfSigningMortgage(in.dateOfSigningMortgage());
        return repo.saveAndFlush(entity);
    }

    public QuotationDetail get(@NotNull CustomerDetailFilter in) {
        Customer customer = customerService.get(new CustomerDetailFilter(in.birthDate()));
        Optional<Quotation> entity = detail(customer);
        if (entity.isEmpty()) {
            throw new QuotationNotFound("customer birthDate=" + in.birthDate());
        }
        return new QuotationDetail(entity.get());
    }

    private Optional<Quotation> detail(Customer customer) {
        return repo.findByCustomer(customer);
    }
}