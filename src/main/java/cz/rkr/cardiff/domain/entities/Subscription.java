package cz.rkr.cardiff.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@javax.persistence.Entity
//@jakarta.persistence.Entity
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "subscription")
@SequenceGenerator(name = "sequence_subscr", sequenceName = "sq_subscr_id", allocationSize = 1)
public class Subscription extends AbstractPersistable<Long> {
    @Id
    @GeneratedValue(generator = "sequence_subscr", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "startDate")
    private LocalDate startDate;

    @NotNull
    @Column(name = "validUntil")
    private LocalDate validUntil;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quot_id", nullable = false)
    private Quotation quotation;
}