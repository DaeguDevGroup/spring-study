package kr.dev.study.config;

import kr.dev.study.bbs.service.BbsService;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

@Configuration
public class TestConfig {

	@Bean
    public BbsService bbsService() {
        return Mockito.mock(BbsService.class);
    }
}
