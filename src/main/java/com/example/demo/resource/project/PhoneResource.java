package com.example.demo.resource.project;

import com.example.demo.dto.PhoneDTO;
import com.example.demo.service.project.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/phone")
@RestController
public class PhoneResource {

    private final PhoneService phoneService;

    @Autowired
    public PhoneResource(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping("/dd/{dd}")
    public ResponseEntity<List<PhoneDTO>> getAllPhotoByDD(@PathVariable("dd") int dd) {
        return ResponseEntity.ok(this.phoneService.getListByDdDTO(dd));
    }
}
