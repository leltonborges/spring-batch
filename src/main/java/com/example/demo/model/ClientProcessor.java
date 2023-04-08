package com.example.demo.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_CLIENT", schema = "project")
public class ClientProcessor implements Serializable {
    @Serial
    private static final long serialVersionUID = -4853153523495537886L;
    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NAME_REGEX = "^[\\p{L} .'-]+$";

    @Id
    @Column(name = "SQ_CLIENT")
    private Long id;

    @NotNull
    @Size(min = 3, max = 100)
    @Pattern(regexp = NAME_REGEX, message = "Name invalid")
    private String name;
    @NotNull
    @Range(min = 18, max = 150)
    private Integer age;
    @NotNull
    @Size(min = 3, max = 50)
    @Pattern(regexp = EMAIL_PATTERN, message = "Email invalid")
    private String email;
}
