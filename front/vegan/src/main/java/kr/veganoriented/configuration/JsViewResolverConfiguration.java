package kr.veganoriented.configuration;

import jdk.nashorn.api.scripting.NashornScriptEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.script.ScriptTemplateConfigurer;
import org.springframework.web.servlet.view.script.ScriptTemplateViewResolver;

/**
 * Created by terrylee on 2017. 12. 2..
 */

@Configuration
public class JsViewResolverConfiguration {

    @Bean
    public ScriptTemplateViewResolver viewResolver(){
        ScriptTemplateViewResolver viewResolver = new ScriptTemplateViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");

        return viewResolver;
    }

    @Bean
    public ScriptTemplateConfigurer configurer() {
        ScriptTemplateConfigurer configurer = new ScriptTemplateConfigurer();
        configurer.setEngineName("nashorn");
        configurer.setRenderFunction("render");
        configurer.setSharedEngine(false);
        return configurer;
    }

}
