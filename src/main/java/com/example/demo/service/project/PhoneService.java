package com.example.demo.service.project;

import com.example.demo.config.bean.PhoneDemo;
import com.example.demo.dto.PhoneDTO;
import com.example.demo.mapper.project.PhoneMapper;
import com.example.demo.model.Phone;
import com.example.demo.repository.project.PhoneRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PreDestroy;
import java.util.List;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;
    private final BeanFactory beanFactory;
    private PhoneDemo phoneDemo;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository, PhoneMapper phoneMapper, BeanFactory beanFactory) {
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
        this.beanFactory = beanFactory;
    }

    public List<Phone> getListByDd(final int dd) {
        return this.phoneRepository.findByDd(dd);
    }

    public List<PhoneDTO> getListByDdDTO(int dd) {
        phoneDemo = this.beanFactory.getBean(PhoneDemo.class, phoneRepository, phoneMapper, dd);
        return phoneDemo.getPhoneDTOList();
    }

    @PreDestroy
    public void destroy() {
        this.phoneDemo = null;
    }
}
