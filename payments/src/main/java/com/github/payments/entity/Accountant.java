package com.github.payments.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries(value = {
        @NamedQuery(
                name = "Accountant.findByStatus",
                query = "select a from Accountant a where a.status=:status"
        )
})
@Table(name = "accountants", schema = "public")
public class Accountant implements Serializable, Cloneable {

    private static final long serialVersionUID = -3930951047330213177L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(
            name = "firs_name",
            nullable = false
    )
    private String firsName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "patronymic",
            nullable = false
    )
    private String patronymic;

    @Column(
            name = "status",
            nullable = false
    )
    @Enumerated(value = EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    @CreationTimestamp
    @Column(
            name = "create_at",
            nullable = false,
            updatable = false
    )
    private LocalDateTime createAt;

    @UpdateTimestamp
    @Column(
            name = "update_at",
            nullable = false
    )
    private LocalDateTime updateAt;

    public Accountant(String firsName, String lastName, String patronymic) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }
}
