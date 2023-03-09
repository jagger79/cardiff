package cz.rkr.cardiff.domain.subscription;

import java.time.LocalDate;

public record SubscriptionCreate(
        LocalDate customerBirthDate,
        LocalDate startDate,
        LocalDate validUntil
) {
}