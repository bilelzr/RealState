package tn.pi.realstate.entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    @SequenceGenerator(name = "transaction_sequence", sequenceName = "transaction_sequence", allocationSize = 1)
    @Column(name = "transaction_id")
    private long id ;


    @ManyToOne
    @JoinColumn(name = "buyer_id")  // Use a different name for the buyer join column
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "publisher_id")  // Use a different name for the publisher join column
    private User publisher;

    private TransactionType transactionType;

    private LocalDate transactionDate;

    private float amount ;

}
