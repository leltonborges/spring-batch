package com.example.demo.config.bean;

import com.example.demo.dto.PhoneDTO;
import com.example.demo.mapper.project.PhoneMapper;
import com.example.demo.repository.project.PhoneRepository;
import java.util.List;

public class PhoneDemo {

    private final PhoneRepository phoneRepository;
    private final PhoneMapper phoneMapper;

    private final List<PhoneDTO> phoneDTOList;

    public PhoneDemo(final PhoneRepository phoneRepository,
                     final PhoneMapper phoneMapper,
                     final int dd) {
        this.phoneRepository = phoneRepository;
        this.phoneMapper = phoneMapper;
        this.phoneDTOList = getPhoneDTOList(dd);
    }

    private List<PhoneDTO> getPhoneDTOList(final int dd) {
        return this.phoneRepository.findByDd(dd)
                                   .stream()
                                   .map(this.phoneMapper::toDTO)
                                   .toList();
    }

    public List<PhoneDTO> getPhoneDTOList() {
        return phoneDTOList;
    }

}
