package cz.rkr.cardiff.controllers.mappers.common;

import cz.rkr.cardiff.controllers.mappers.config.MapperCommonConfiguration;
import org.mapstruct.Mapper;

import java.net.MalformedURLException;
import java.net.URL;

@Mapper(config = MapperCommonConfiguration.class)
public interface UrlMapper {
    default String from(URL url) {
        if (url == null) return null;
        return url.toString();
    }

    default URL to(String url) {
        if (url == null) return null;
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new WrongUrlException(e.getMessage());
        }
    }

    class WrongUrlException extends RuntimeException {
        public WrongUrlException(String msg) {
            super(msg);
        }
    }
}