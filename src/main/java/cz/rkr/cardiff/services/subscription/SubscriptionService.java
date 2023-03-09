package cz.rkr.cardiff.services.subscription;

import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.entities.Customer;
import cz.rkr.cardiff.domain.entities.Subscription;
import cz.rkr.cardiff.domain.quotation.QuotationDetail;
import cz.rkr.cardiff.domain.subscription.SubscriptionCreate;
import cz.rkr.cardiff.domain.subscription.SubscriptionDetail;
import cz.rkr.cardiff.exceptions.SubscriptionFound;
import cz.rkr.cardiff.exceptions.SubscriptionNotFound;
import cz.rkr.cardiff.exceptions.SubscriptionWrongDatesFound;
import cz.rkr.cardiff.repositories.SubscriptionRepository;
import cz.rkr.cardiff.services.quotation.QuotationService;
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
public class SubscriptionService {
    private final SubscriptionRepository repo;
    private final QuotationService quotService;

    public Subscription create(@NotNull SubscriptionCreate in) {
        QuotationDetail quot = quotService.get(new CustomerDetailFilter(in.customerBirthDate()));
        Optional<Subscription> existing = detail(quot.getCustomer());
        if (existing.isPresent()) {
            throw new SubscriptionFound("already exist");
        }

        if (!in.startDate().isBefore(in.validUntil())) {
            throw new SubscriptionWrongDatesFound("startDate is not before validUntil");
        }
        Subscription entity = new Subscription();
        entity.setQuotation(quot.getEntity());
        entity.setStartDate(in.startDate());
        entity.setValidUntil(in.validUntil());
        return repo.saveAndFlush(entity);
    }

    public SubscriptionDetail get(@NotNull CustomerDetailFilter in) {
        QuotationDetail quot = quotService.get(new CustomerDetailFilter(in.birthDate()));
        Optional<Subscription> entity = detail(quot.getCustomer());
        if (entity.isEmpty()) {
            throw new SubscriptionNotFound("birthDate=" + in.birthDate());
        }
        return new SubscriptionDetail(entity.get());
    }

    private Optional<Subscription> detail(Customer customer) {
        return repo.findByCustomer(customer);
    }
}