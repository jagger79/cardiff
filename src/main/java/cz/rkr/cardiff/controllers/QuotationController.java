package cz.rkr.cardiff.controllers;

import cz.rkr.cardiff.api.model.QuotationCreateRequest;
import cz.rkr.cardiff.api.model.QuotationResource;
import cz.rkr.cardiff.controllers.mappers.QuotationMapper;
import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.quotation.QuotationCreate;
import cz.rkr.cardiff.domain.quotation.QuotationDetail;
import cz.rkr.cardiff.services.quotation.QuotationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("${cardiff.api.base-path}/" + QuotationController.BASE_PATH)
@RequiredArgsConstructor
@Validated
public class QuotationController {
    public static final String BASE_PATH = "quotations";

    private final QuotationService service;
    private final QuotationMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    void create(@AuthenticationPrincipal Authentication auth,
                @Valid @RequestBody QuotationCreateRequest req) {
        QuotationCreate in = mapper.toCreate(req);
        service.create(in);
    }

    @GetMapping("{birthDate}")
    QuotationResource detail(@AuthenticationPrincipal Authentication auth,
                             @PathVariable String birthDate) {
        CustomerDetailFilter in = mapper.toDetail(birthDate);
        QuotationDetail resource = service.get(in);
        return mapper.toDetailResource(resource);
    }
}