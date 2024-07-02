package business.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Stock Calculator")
                        .description(
                                "This is the API-Documentation of the Calculator Service"));
    }

    @Bean
    public GroupedOpenApi v1Api() {
        String[] paths = {"/api/**"};
        return GroupedOpenApi.builder()
                .group("api")
                .pathsToMatch(paths)
                .build();
    }
}