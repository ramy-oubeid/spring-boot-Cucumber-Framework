package com.testframework.cucumberjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;

@Configuration
public class AppConfig {

    @Bean
    public WebServiceTemplate webServiceTemplate() {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMessageFactory(messageFactory());
        return webServiceTemplate;
    }

    @Bean
    public SaajSoapMessageFactory messageFactory() {
        try {
            SaajSoapMessageFactory messageFactory = new SaajSoapMessageFactory();
            messageFactory.afterPropertiesSet();
            return messageFactory;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SaajSoapMessageFactory", e);
        }
    }
}
