package org.javatests.SpringsExample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import org.javatests.SpringsExample.model.entities.City;
import org.javatests.SpringsExample.model.dao.CitiesDao;

@Service
public class Cities {

    @Autowired
    private CitiesDao citiesDao;

    @Transactional
    public List<City> list() {
        return citiesDao.list();
    }

    @Transactional
    public void add( City city ) {
    	citiesDao.add( city );
    }

    @Transactional
    public void remove( Integer id ) {
    	citiesDao.remove( id );
    }

}
