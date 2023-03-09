package cz.rkr.cardiff.controllers.mappers.common;

import cz.rkr.cardiff.controllers.mappers.config.MapperCommonConfiguration;
import cz.rkr.cardiff.domain.common.Nullable;
import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Mapper(config = MapperCommonConfiguration.class)
public interface CommonMapper {
    default Collection<String> mapToCollection(String value) {
        if (value == null || isBlank(value)) return null;
        return List.of(value);
    }

    default Set<Long> mapToCollection(Long value) {
        if (value == null) return null;
        return Set.of(value);
    }

    default <T> Nullable<T> mapToNullable(JsonNullable<T> value) {
        if (value == null) return Nullable.undefined();
        return value.isPresent() ? Nullable.of(value.get()) : Nullable.undefined();
    }

    default <T> Nullable<Collection<T>> mapToNullableCollectionList(JsonNullable<List<T>> value) {
        if (value == null) return Nullable.undefined();
        return value.isPresent() ? Nullable.of(value.get()) : Nullable.undefined();
    }
}