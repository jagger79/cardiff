package cz.rkr.cardiff.domain.customer;

import java.time.LocalDate;

public record CustomerCreate(
        String firstName,
        String middleName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate birthDate
) {
}