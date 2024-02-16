package tn.pi.realstate.entities;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Property {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_sequence")
    @SequenceGenerator(name = "property_sequence", sequenceName = "property_sequence", allocationSize = 1)
    @Column(name = "property_id")
    private long id;
    private String Location;

    private float price ;

    private Status status;

    private String Description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
