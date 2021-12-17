package org.javatests.SpringsExample.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CITIES")
public class City {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id = Integer.valueOf( -1 );

    @Column(name = "NAME")
    private String name;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

}
