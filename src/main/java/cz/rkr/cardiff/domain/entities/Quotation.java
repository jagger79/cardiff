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
@Table(name = "quotation")
@SequenceGenerator(name = "sequence_quot", sequenceName = "sq_quot_id", allocationSize = 1)
public class Quotation extends AbstractPersistable<Long> {
    @Id
    @GeneratedValue(generator = "sequence_quot", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "beginning_of_insurance")
    private LocalDate beginningOfInsurance;

    @NotNull
    @Column(name = "insured_amount")
    private Long insuredAmount;

    @NotNull
    @Column(name = "date_of_signing_mortgage")
    private LocalDate dateOfSigningMortgage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id", nullable = false)
    private Customer customer;
}