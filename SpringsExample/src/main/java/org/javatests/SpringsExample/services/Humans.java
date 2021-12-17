package org.javatests.SpringsExample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import org.javatests.SpringsExample.model.entities.Human;
import org.javatests.SpringsExample.model.dao.HumansDao;

@Service
public class Humans {

    @Autowired
    private HumansDao humansDao;

    @Transactional(readOnly=true)
    public List<Human> list() {
        return humansDao.list();
    }

    @Transactional(readOnly=false)
    public void add( Human human ) {
    	humansDao.add( human );
    }

    @Transactional(readOnly=false)
    public void remove( Integer id ) {
    	humansDao.remove( id );
    }

}
