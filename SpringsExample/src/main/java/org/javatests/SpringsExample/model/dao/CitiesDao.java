package org.javatests.SpringsExample.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import org.javatests.SpringsExample.model.entities.City;

@Repository
public class CitiesDao {

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<City> list() {
    	return sessionFactory.getCurrentSession().createQuery("from City").list();
    }

    public void add( City city ) {
    	sessionFactory.getCurrentSession().saveOrUpdate( city );
    }

    public void remove( Integer id ) {
    	City city = (City)sessionFactory.getCurrentSession().load( City.class, id );
        if( null != city ) {
        	sessionFactory.getCurrentSession().delete( city );
        }
    }

}
