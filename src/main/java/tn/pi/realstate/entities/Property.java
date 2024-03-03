package tn.pi.realstate.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Property {



    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_sequence")
    @SequenceGenerator(name = "property_sequence", sequenceName = "property_sequence", allocationSize = 1)
    @Column(name = "property_id")
    private long id;


    private String location;

    private float price;

    private String title;
    private Status status;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
