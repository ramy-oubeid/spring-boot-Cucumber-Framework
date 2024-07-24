package com.testframework.cucumberjava.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WsdlParserRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WsdlParserRunner.class);

    @Autowired
    private com.testframework.cucumberjava.service.WsdlParserService wsdlParserService;

    @Value("${wsdl.path}")
    private String wsdlPath;

    @Value("${output.dir}")
    private String outputDir;

    @Override
    public void run(String... args) {
        logger.info("Starting WsdlParserRunner with wsdlPath: {} and outputDir: {}", wsdlPath, outputDir);
        try {
            wsdlParserService.parseWsdlAndGenerateTests(wsdlPath, outputDir);
            logger.info("WsdlParserRunner completed successfully.");
        } catch (Exception e) {
            logger.error("Error occurred in WsdlParserRunner", e);
        }
    }
}