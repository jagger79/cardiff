package cz.rkr.cardiff.domain.quotation;

import cz.rkr.cardiff.domain.entities.Customer;
import cz.rkr.cardiff.domain.entities.Quotation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuotationDetail {
    private Quotation entity;

    public LocalDate getBeginningOfInsurance() {
        return entity.getBeginningOfInsurance();
    }

    public LocalDate getDateOfSigningMortgage() {
        return entity.getDateOfSigningMortgage();
    }

    public Long getInsuredAmount() {
        return entity.getInsuredAmount();
    }

    public Customer getCustomer() {
        return entity.getCustomer();
    }
}