package cz.rkr.cardiff.controllers;

import cz.rkr.cardiff.api.model.SubscriptionCreateRequest;
import cz.rkr.cardiff.api.model.SubscriptionResource;
import cz.rkr.cardiff.controllers.mappers.SubscriptionMapper;
import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.subscription.SubscriptionCreate;
import cz.rkr.cardiff.domain.subscription.SubscriptionDetail;
import cz.rkr.cardiff.services.subscription.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${cardiff.api.base-path}/" + SubscriptionController.BASE_PATH)
@RequiredArgsConstructor
@Validated
public class SubscriptionController {
    public static final String BASE_PATH = "subscriptions";

    private final SubscriptionService service;
    private final SubscriptionMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    void create(@AuthenticationPrincipal Authentication auth,
                @Valid @RequestBody SubscriptionCreateRequest req) {
        SubscriptionCreate in = mapper.toCreate(req);
        service.create(in);
    }

    @GetMapping("{birthDate}")
    SubscriptionResource detail(@AuthenticationPrincipal Authentication auth,
                                @PathVariable String birthDate) {
        CustomerDetailFilter in = mapper.toDetail(birthDate);
        SubscriptionDetail resource = service.get(in);
        return mapper.toDetailResource(resource);
    }
}