package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TB_TRANSACTION", schema = "project")
public class Transaction implements Domain, Serializable {
    @Serial
    private static final long serialVersionUID = 605586854941985515L;

    @Id
    @Column(name = "SQ_TRANSACTION")
    private Long id;
    @Column(name = "NU_ID_TRANSACTION", unique = true)
    private String idTransaction;
    @Column(name = "ST_DESCRIPTION")
    private String description;
    @Column(name = "VL_AMOUNT")
    private BigDecimal amount;
}
