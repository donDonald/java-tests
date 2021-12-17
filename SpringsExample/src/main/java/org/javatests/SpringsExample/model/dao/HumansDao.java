package org.javatests.SpringsExample.model.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.javatests.SpringsExample.model.entities.Human;

import java.util.List;
import org.apache.log4j.Logger;

@Repository
public class HumansDao {

	@Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public List<Human> list() {
    	return sessionFactory.getCurrentSession().createQuery("from Human").list();
    }

    public void add( Human human ) {
    	sessionFactory.getCurrentSession().saveOrUpdate( human );
    }

    public void remove( Integer id ) {
    	Human human = (Human)sessionFactory.getCurrentSession().load( Human.class, id );
        if( null != human ) {
        	sessionFactory.getCurrentSession().delete( human );
        }
    }

}
