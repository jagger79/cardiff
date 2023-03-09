package cz.rkr.cardiff;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * This configuration properties exposed as a bean to be used in SpEL injections.
 */
@Configuration
@Data
@ConfigurationProperties("cardiff")
@Validated
public class CardiffProperties {
    /**
     * Configuration for lead's gdpr sms
     */
    @NestedConfigurationProperty
    @Valid
    private final SmsTemplate smsTemplate = new SmsTemplate();

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${cardiff.api.base-path}")
    private String apiPath;

    /**
     * Specify if running on test environment
     */
    @NotNull
    private EnvType environment = EnvType.DEV;

    public enum EnvType {
        DEV,
        INT,
        TEST,
        PROD;
    }

    @Data
    public static class SmsTemplate {
        private String smsGatewayNumber;
        private String moreInfoUrl = "https://tam.je/privacy";
    }
}