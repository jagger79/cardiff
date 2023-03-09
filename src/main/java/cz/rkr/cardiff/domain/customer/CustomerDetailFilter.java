package cz.rkr.cardiff.domain.customer;

import java.time.LocalDate;

public record CustomerDetailFilter(
        LocalDate birthDate) {
}