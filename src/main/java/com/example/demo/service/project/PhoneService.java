package com.example.demo.service.project;

import com.example.demo.config.bean.PhoneDemo;
import com.example.demo.dto.PhoneDTO;
import com.example.demo.dto.PhonePOJO;
import com.example.demo.mapper.project.PhoneMapper;
import com.example.demo.model.Phone;
import com.example.demo.repository.project.PhoneRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;
    private final BeanFactory beanFactory;
    private final EntityManager entityManager;
    private PhoneDemo phoneDemo;

    @Autowired
    public PhoneService(PhoneRepository phoneRepository, PhoneMapper phoneMapper, BeanFactory beanFactory, EntityManager entityManager) {
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
        this.beanFactory = beanFactory;
        this.entityManager = entityManager;
    }

    public List<Phone> getListByDd(final int dd) {
        return this.phoneRepository.findByDd(dd);
    }

    public List<PhoneDTO> getListByDdDTO(int dd) {
        phoneDemo = this.beanFactory.getBean(PhoneDemo.class, phoneRepository, phoneMapper, dd);
        return phoneDemo.getPhoneDTOList();
    }

     public List<Phone> getListByDdAndOperadora(List<PhonePOJO> phonePOJOS) {
         List<Phone> collect = phonePOJOS.stream().map(p -> new Phone(p.getDd(), p.getAbc())).collect(Collectors.toList());
         return this.phoneRepository.findByDdAndOperadora(collect);
     }
    public List<Phone> abc() {
        List<Integer> list = Arrays.asList(new Integer[]{61, 1});

        return this.phoneRepository.findbyMult(list);
    }
}
