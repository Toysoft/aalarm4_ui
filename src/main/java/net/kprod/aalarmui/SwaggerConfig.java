package net.kprod.aalarmui;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("AAlarm4")
				.description("Rest services")
				.version("2.0")
				.build();
	}

	@Bean
	public Docket eventsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("events")
				.apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/event/.*"))
				.build();
	}

	@Bean
	public Docket motionsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("motions")
				.apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/motion/.*"))
				.build();
	}

	@Bean
	public Docket remoteApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("remote")
				.apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/remote/.*"))
				.build();
	}

	@Bean
	public Docket keysApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("keys")
				.apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/keys/.*"))
				.build();
	}

	@Bean
	public Docket utilsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("utils")
				.apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/utils/.*"))
				.build();
	}

}