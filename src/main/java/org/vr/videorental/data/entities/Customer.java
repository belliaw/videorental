package org.vr.videorental.data.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for the Customer model
 *
 * @author: william.bellia
 * @since: 07/07/2016 17:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", unique = true, nullable = false)
    private long id;

    @Column(name = "NAME", nullable = false, length = 20)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<FilmCustomer> filmCustomers;

}
