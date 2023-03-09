package cz.rkr.cardiff.controllers.mappers;

import cz.rkr.cardiff.api.model.CustomerPatchRequest;
import cz.rkr.cardiff.api.model.SubscriptionCreateRequest;
import cz.rkr.cardiff.api.model.SubscriptionResource;
import cz.rkr.cardiff.controllers.mappers.config.MapperConfiguration;
import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.customer.CustomerPatch;
import cz.rkr.cardiff.domain.subscription.SubscriptionCreate;
import cz.rkr.cardiff.domain.subscription.SubscriptionDetail;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = MapperConfiguration.class)
@Slf4j
public abstract class SubscriptionMapper {
    @Autowired
    private QuotationMapper quotMapper;

    public abstract SubscriptionCreate toCreate(SubscriptionCreateRequest in);

    public abstract CustomerDetailFilter toDetail(String birthDate);

    // TODO mapstruct not working :-( no time now
    public SubscriptionResource toDetailResource(SubscriptionDetail in) {
        if (in == null) return null;
        return new SubscriptionResource()
                .startDate(in.getStartDate())
                .validUntil(in.getValidUntil())
                .quotation(quotMapper.toDetailResource(in.getQuotation()))
                ;
    }

    public abstract CustomerPatch toPatch(String birthDate,
                                          CustomerPatchRequest in);
}