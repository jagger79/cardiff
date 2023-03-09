package cz.rkr.cardiff.controllers;

import cz.rkr.cardiff.api.model.CustomerCreateRequest;
import cz.rkr.cardiff.api.model.CustomerPatchRequest;
import cz.rkr.cardiff.api.model.CustomerResource;
import cz.rkr.cardiff.controllers.mappers.CustomerMapper;
import cz.rkr.cardiff.domain.customer.CustomerCreate;
import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.customer.CustomerPatch;
import cz.rkr.cardiff.domain.entities.Customer;
import cz.rkr.cardiff.services.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("${cardiff.api.base-path}/" + CustomerController.BASE_PATH)
@RequiredArgsConstructor
@Validated
public class CustomerController {
    public static final String BASE_PATH = "customers";

    private final CustomerService service;
    private final CustomerMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    void create(@AuthenticationPrincipal Authentication auth,
                @Valid @RequestBody CustomerCreateRequest req) {
        CustomerCreate in = mapper.toCreate(req);
        service.create(in);
    }

    @GetMapping("{birthDate}")
    CustomerResource detail(@AuthenticationPrincipal Authentication auth,
                            @PathVariable String birthDate) {
        CustomerDetailFilter in = mapper.toDetail(birthDate);
        Customer resource = service.get(in);
        return mapper.toDetailResource(resource);
    }

    @PatchMapping("{birthDate}")
    @ResponseStatus(NO_CONTENT)
    void patch(@AuthenticationPrincipal Authentication auth,
               @PathVariable String birthDate,
               @Valid @RequestBody CustomerPatchRequest req) {
        CustomerPatch in = mapper.toPatch(birthDate, req);
        service.patch(in);
    }
}