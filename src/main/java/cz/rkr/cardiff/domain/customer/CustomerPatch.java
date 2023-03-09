package cz.rkr.cardiff.domain.customer;

import cz.rkr.cardiff.domain.common.Nullable;

import java.time.LocalDate;

public record CustomerPatch(
        LocalDate birthDate,
        Nullable<String> firstName,
        Nullable<String> middleName,
        Nullable<String> lastName,
        Nullable<String> email,
        Nullable<String> phoneNumber
) {
}