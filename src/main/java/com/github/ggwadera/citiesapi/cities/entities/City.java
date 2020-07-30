package com.github.ggwadera.citiesapi.cities.entities;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.data.geo.Point;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "City")
@Table(name = "cidade")
@TypeDefs(value = {
    @TypeDef(name = "point", typeClass = PointType.class)
})
public class City {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    private Integer uf;

    private Integer ibge;

    @Column(name = "lat_lon")
    private String geolocation;

    @Type(type = "point")
    @Column(name = "lat_lon", updatable = false, insertable = false)
    private Point location;

    public City() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getUf() {
        return uf;
    }

    public Integer getIbge() {
        return ibge;
    }

    public String getGeolocation() {
        return geolocation;
    }

    public Point getLocation() {
        return location;
    }
}
