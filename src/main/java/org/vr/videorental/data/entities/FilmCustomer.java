package org.vr.videorental.data.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for the Film-Customer mapping model
 *
 * @author: william.bellia
 * @since: 07/07/2016 17:45
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "FILM_CUSTOMER")
public class FilmCustomer {
       
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_ISSUED", nullable = false, length = 15)
    private Date dateIssued;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_RETURNED", nullable = true, length = 15)
    private Date dateReturned;

    @Column(name = "DAYS_RENTED", nullable = false)
    private int daysRented;
}
