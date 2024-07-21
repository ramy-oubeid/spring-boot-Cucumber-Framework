package com.testframework.cucumberjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WsdlParserRunner implements CommandLineRunner {

    @Autowired
    private com.testframework.cucumberjava.service.WsdlParserService wsdlParserService;

    @Value("${wsdl.path}")
    private String wsdlPath;

    @Value("${output.dir}")
    private String outputDir;

    @Override
    public void run(String... args) throws Exception {
        wsdlParserService.parseWsdlAndGenerateTests(wsdlPath, outputDir);
    }
}

