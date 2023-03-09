package cz.rkr.cardiff.controllers.mappers.config;

import cz.rkr.cardiff.controllers.mappers.common.CommonMapper;
import cz.rkr.cardiff.controllers.mappers.common.OptionalMapper;
import cz.rkr.cardiff.controllers.mappers.common.UrlMapper;
import org.mapstruct.*;

@MapperConfig(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {OptionalMapper.class,
                UrlMapper.class,
                CommonMapper.class}
)
public interface MapperConfiguration {
}