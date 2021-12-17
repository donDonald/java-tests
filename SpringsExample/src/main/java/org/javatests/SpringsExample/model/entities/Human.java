package org.javatests.SpringsExample.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "HUMANS")
public class Human {

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Integer id = Integer.valueOf( -1 );;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "SECONDNAME")
    private String secondName;

    @ManyToOne
    @JoinColumn(name="CITYID")
    private City city;

    public Human() {
    }

    public int getId() {
        return id;
    }

    public void setId( int id ) {
        this.id = id;
    }

    public String getFirstName() {
       return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName( String secondName ) {
        this.secondName = secondName;
    }

    public City getCity() {
        return city;
    }

    public void setCity( City city ) {
        this.city = city;
    }

}
