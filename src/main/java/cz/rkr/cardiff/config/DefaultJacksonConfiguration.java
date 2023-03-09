package cz.rkr.cardiff.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class DefaultJacksonConfiguration implements Jackson2ObjectMapperBuilderCustomizer {
    private static final DateTimeFormatter ISO_OFFSET_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx");

    @Override
    public void customize(Jackson2ObjectMapperBuilder mapperBuilder) {
        mapperBuilder.modulesToInstall(new JavaTimeModule());
        mapperBuilder.modulesToInstall(new JsonNullableModule());
        mapperBuilder.serializerByType(ZonedDateTime.class, new ZonedDateTimeSerializer());
//        mapperBuilder.deserializerByType(ZonedDateTime.class, new ZonedDateTimeDeserializer());
        mapperBuilder.serializerByType(OffsetDateTime.class, new OffsetDateTimeSerializer());
//        mapperBuilder.deserializerByType(OffsetDateTime.class, new OffsetDateTimeDeserializer());
    }

    public static class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

        @Override
        public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String str = ISO_OFFSET_DATE_TIME.format(value);
            gen.writeString(str);
        }
    }

    public static class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
        @Override
        public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return ZonedDateTime.parse(p.getValueAsString(), ISO_OFFSET_DATE_TIME);
        }
    }

    public static class OffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

        @Override
        public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String str = ISO_OFFSET_DATE_TIME.format(value);
            gen.writeString(str);
        }
    }

    public static class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

        @Override
        public OffsetDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return OffsetDateTime.parse(p.getValueAsString(), ISO_OFFSET_DATE_TIME);
        }
    }
}
