package com.example.demo.repository.project;

import com.example.demo.model.Phone;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PhoneRepository
        extends PagingAndSortingRepository<Phone, Long> {
    List<Phone> findByDd(int dd);
}
