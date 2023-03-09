package cz.rkr.cardiff.domain.subscription;

import cz.rkr.cardiff.domain.entities.Subscription;
import cz.rkr.cardiff.domain.quotation.QuotationDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDetail {
    private Subscription entity;

    public LocalDate getStartDate() {
        return entity.getStartDate();
    }

    public LocalDate getValidUntil() {
        return entity.getValidUntil();
    }

    public QuotationDetail getQuotation() {
        return new QuotationDetail(entity.getQuotation());
    }
}