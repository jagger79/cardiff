package cz.rkr.cardiff.domain.quotation;

import java.time.LocalDate;

public record QuotationCreate(
        LocalDate customerBirthDate,
        Long insuredAmount,
        LocalDate beginningOfInsurance,
        LocalDate dateOfSigningMortgage
) {
}