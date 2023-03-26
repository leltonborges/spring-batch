package com.example.demo.model.pks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientTransactionKey implements Serializable {
    @Column(name = "SQ_CLIENT")
    private Long client;
    @Column(name = "SQ_TRANSACTION")
    private Long transaction;
}
