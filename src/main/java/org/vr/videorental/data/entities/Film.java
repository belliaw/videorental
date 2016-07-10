package org.vr.videorental.data.entities;

import javax.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for the Film model
 *
 * @author: william.bellia
 * @since: 07/07/2016 17:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FILM")
public class Film
{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;
    
    @Column(name = "NAME", unique = true, nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name="FK_RENTALTYPE_ID",nullable = false)
    private RentalType rentalType;

    @ManyToOne
    @JoinColumn(name="FK_FILMTYPE_ID",nullable = false)
    private FilmType filmType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "film", cascade=CascadeType.ALL)
    private Set<FilmCustomer> filmCustomers;

}
