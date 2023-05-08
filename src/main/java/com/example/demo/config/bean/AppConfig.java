package com.example.demo.config.bean;

import com.example.demo.mapper.project.PhoneMapper;
import com.example.demo.repository.project.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class AppConfig {

    @Bean("beanPhotoDemo")
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
//    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @Scope(value = WebApplicationContext.SCOPE_REQUEST)
    public PhoneDemo beanPhoneDemo(@Qualifier("phoneRepository") final PhoneRepository phoneRepository,
                                   final PhoneMapper phoneMapper,
                                   @Autowired(required = false) final int dd) {
        return new PhoneDemo(phoneRepository, phoneMapper, dd);
    }
}
