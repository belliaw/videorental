package org.vr.videorental.data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by William on 09/07/2016.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FILM_TYPE")
public class FilmType {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "BONUS_POINTS", nullable = false)
    private int bonusPoints;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "filmType")
    private Set<Film> films;
}
