package kr.veganoriented.oauth2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by terrylee on 17. 8. 1.
 */

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

  @Bean
  public RequestMappingHandlerMapping requestMappingHandlerMapping() {
    RequestMappingHandlerMapping handlerMapping = super.requestMappingHandlerMapping();
    handlerMapping.setAlwaysUseFullPath(true);
    return handlerMapping;
  }
}
