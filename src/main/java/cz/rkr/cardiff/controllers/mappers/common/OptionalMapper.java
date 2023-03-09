package cz.rkr.cardiff.controllers.mappers.common;

import cz.rkr.cardiff.controllers.mappers.config.MapperCommonConfiguration;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(config = MapperCommonConfiguration.class)
public abstract class OptionalMapper {
    public <T> T unwrapOptional(Optional<T> opt) {
        return opt.orElse(null);
    }
}