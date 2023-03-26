package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TB_CLIENT", schema = "project")
@Where(clause = "DF_DELETE = true")
public class Client implements Domain {
    @Id
    @Column(name = "SQ_CLIENT")
    private Long id;
    @Column(name = "ST_NAME")
    private String name;
    @Column(name = "ST_LAST_NAME")
    private String lastName;
    @Column(name = "NU_AGE")
    private Integer age;
    @Column(name = "ST_EMAIL")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TB_CLIENTE_TRANSACTION",
    joinColumns = @JoinColumn(name = "SQ_CLIENT"),
    inverseJoinColumns = @JoinColumn(name = "SQ_TRANSACTION"))
    private List<Transaction> transactions = new ArrayList<>();
}
