package com.example.demo.repository.project;

import com.example.demo.model.Phone;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository
        extends PagingAndSortingRepository<Phone, Long> {
    List<Phone> findByDd(int dd);

    @Query(value = "SELECT P FROM Phone P " +
            "WHERE (P.dd, P.description) IN :ddAndOperadora ")
    List<Phone> findByDdAndOperadora(@Param("ddAndOperadora") List<Phone> pojoSet);

    @Query(nativeQuery = true,
            value = "SELECT * FROM project.TB_PHONE P WHERE (P.NUM_DD, P.ST_CHIP) IN  (VALUES :parans)"
    )
    List<Phone> findbyMult(@Param("parans") List<Integer> params);
}
