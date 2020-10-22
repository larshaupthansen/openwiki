package openwiki.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import openwiki.service.FilePageService;
import openwiki.service.IPageService;

@Configuration
public class Config {
 
    @Bean()
    public IPageService pageService() {
        return new FilePageService();
    }
 
}