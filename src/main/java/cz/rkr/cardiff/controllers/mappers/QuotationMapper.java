package cz.rkr.cardiff.controllers.mappers;

import cz.rkr.cardiff.api.model.CustomerPatchRequest;
import cz.rkr.cardiff.api.model.QuotationCreateRequest;
import cz.rkr.cardiff.api.model.QuotationResource;
import cz.rkr.cardiff.controllers.mappers.config.MapperConfiguration;
import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.customer.CustomerPatch;
import cz.rkr.cardiff.domain.quotation.QuotationCreate;
import cz.rkr.cardiff.domain.quotation.QuotationDetail;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfiguration.class)
@Slf4j
public abstract class QuotationMapper {
    @Autowired
    private CustomerMapper custMapper;

    public abstract QuotationCreate toCreate(QuotationCreateRequest in);

    public abstract CustomerDetailFilter toDetail(String birthDate);

    // TODO mapstruct not working :-( no time now
    public QuotationResource toDetailResource(QuotationDetail in) {
        if (in == null) return null;
        return new QuotationResource()
                .beginningOfInsurance(in.getBeginningOfInsurance())
                .dateOfSigningMortgage(in.getDateOfSigningMortgage())
                .insuredAmount(in.getInsuredAmount())
                .customer(custMapper.toDetailResource(in.getCustomer()));
    }

    public abstract CustomerPatch toPatch(String birthDate,
                                          CustomerPatchRequest in);
}