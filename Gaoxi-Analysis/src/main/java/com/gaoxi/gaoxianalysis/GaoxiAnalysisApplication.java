package com.gaoxi.gaoxianalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class GaoxiAnalysisApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(GaoxiAnalysisApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GaoxiAnalysisApplication.class, args);
	}
}
