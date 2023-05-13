package com.example.demo.model;

import lombok.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PHONE", schema = "project")
public class Phone
        implements Domain, Serializable {
    @Serial
    private static final long serialVersionUID = -1164082920613427864L;

    @Id
    @Column(name = "SQ_PHONE")
    private Long seqPhone;
    @Column(name = "NUM_DD")
    private int dd;
    @Column(name = "NUM_PHONE")
    private int phoneNumber;
    @Column(name = "CHIP")
    private int chip;
    @Column(name = "DS_DESCRIPTION")
    private String description;
    @Column(name = "ST_CHIP")
    private int stChip;

    public Phone(int dd, int stChip) {
        this.dd = dd;
        this.stChip = stChip;
    }
}
