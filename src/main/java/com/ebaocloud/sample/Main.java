package com.ebaocloud.sample;

import com.ebaocloud.sample.comparequotes.CompareMotor;
import com.ebaocloud.sample.comparequotes.CompareTravel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Guo Rui on 5/3/17.
 */
@SpringBootApplication
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Value("${testcase:}")
    private String testcase;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private CompareMotor compareMotor;

    @Autowired
    private CompareTravel compareTravel;

    public Main() {
    }

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            if (args == null || args.length == 0) {
                printUsage();
            } else if (args[0].trim().equalsIgnoreCase("--help")) {
                printUsage();
            } else if (args[0].trim().toLowerCase().startsWith("--testcase=")) {
                if (testcase == null || testcase.trim().equals("")) {
                    logger.info("ERROR: test case must be identified");
                } else {
                    testcase = testcase.trim().toLowerCase();
                    if (testcase.equalsIgnoreCase("compare_travel")) {
                        logger.info("running test case: {}", testcase);
                        compareTravel.compare();
                    } else if (testcase.equalsIgnoreCase("compare_motor")) {
                        logger.info("running test case: {}", testcase);
                        compareMotor.compare();
                    } else {
                        logger.info("ERROR: invalid test case: {}", testcase);
                    }
                }
            }
        };
    }

    private void printUsage() throws Exception {
        BufferedReader reader = null;
        try {
            Resource usage = resourceLoader.getResource("classpath:usage.txt");
            reader = new BufferedReader(new InputStreamReader(usage.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = reader.readLine();
            }
            String everything = sb.toString();
            logger.info(everything);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}