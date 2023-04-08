package com.example.demo.model;

import lombok.*;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(name = "findAll",
        query = "select c from Client c")

@NamedNativeQuery(
        name = "findAllSort",
        query = "select * from project.TB_CLIENT c ",
        resultClass = Client.class)
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CLIENT", schema = "project")
public class Client
        implements Domain, Serializable {
    @Serial
    private static final long serialVersionUID = -1164082920613427864L;

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "TB_CLIENT_TRANSACTION",
            schema = "project",
            indexes = {
                    @Index(name = "id_client_transaction", columnList = "SQ_CLIENT, SQ_TRANSACTION", unique = true)
            },
            joinColumns = @JoinColumn(name = "SQ_CLIENT"),
            inverseJoinColumns = @JoinColumn(name = "SQ_TRANSACTION"))
    private List<Transaction> transactions = new ArrayList<>();

//    @OneToMany(mappedBy = "client")
//    private List<ClientTransaction> clientTransaction;
}
