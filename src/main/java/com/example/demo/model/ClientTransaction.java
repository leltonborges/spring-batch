package com.example.demo.model;

import com.example.demo.model.pks.ClientTransactionKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TB_CLIENT_TRANSACTION",
        schema = "project",
        indexes = {
                @Index(name = "id_client_transaction", columnList = "SQ_CLIENT, SQ_TRANSACTION", unique = true)
        }
)
public class ClientTransaction implements Serializable {
    @Serial
    private static final long serialVersionUID = 7054098359506288499L;

    @EmbeddedId
    private ClientTransactionKey id;
    @ManyToOne
    @JoinColumn(name = "SQ_CLIENT")
    @MapsId("client")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "SQ_TRANSACTION")
    @MapsId("transaction")
    private Transaction transaction;

    @Column(name = "DF_CREATED")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
