package cz.rkr.cardiff.controllers.mappers;

import cz.rkr.cardiff.api.model.CustomerCreateRequest;
import cz.rkr.cardiff.api.model.CustomerPatchRequest;
import cz.rkr.cardiff.api.model.CustomerResource;
import cz.rkr.cardiff.controllers.mappers.config.MapperConfiguration;
import cz.rkr.cardiff.domain.customer.CustomerCreate;
import cz.rkr.cardiff.domain.customer.CustomerDetailFilter;
import cz.rkr.cardiff.domain.customer.CustomerPatch;
import cz.rkr.cardiff.domain.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfiguration.class)
@Slf4j
public abstract class CustomerMapper {
    public abstract CustomerCreate toCreate(CustomerCreateRequest in);

    public abstract CustomerDetailFilter toDetail(String birthDate);

    // TODO mapstruct not working :-( no time now
    public CustomerResource toDetailResource(Customer in) {
        if (in == null) return null;
        return new CustomerResource()
                .birthDate(in.getBirthDate())
                .firstName(in.getFirstName())
                .lastName(in.getLastName())
                .middleName(in.getMiddleName())
                .email(in.getEmail())
                .phoneNumber(in.getPhoneNumber());
    }

    public abstract CustomerPatch toPatch(String birthDate,
                                          CustomerPatchRequest in);
}