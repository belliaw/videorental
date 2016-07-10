package org.vr.videorental.data.entities;

import javax.persistence.*;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for the RentalType model
 *
 * @author: william.bellia
 * @since: 07/07/2016 17:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RENTAL_TYPE")
public class RentalType
{
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 30)
    private String name;

    @Column(name = "FEE", nullable = false)
    private double fee;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rentalType")
    private Set<Film> films;
}
