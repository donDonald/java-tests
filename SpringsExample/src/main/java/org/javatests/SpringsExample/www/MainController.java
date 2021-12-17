package org.javatests.SpringsExample.www;

import org.apache.log4j.Logger;
import org.javatests.SpringsExample.model.dao.HumansDao;
import org.javatests.SpringsExample.model.entities.City;
import org.javatests.SpringsExample.model.entities.Human;
import org.javatests.SpringsExample.services.Cities;
import org.javatests.SpringsExample.services.Humans;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	static Logger log = Logger.getLogger( MainController.class.getName() );

    @Autowired
    private Cities citiesService;

    @Autowired
    private Humans humansService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping("/testproject")
    public String testproject( Map<String, Object> map ) {
        return listHumans( map );
    }

    @RequestMapping("/index")
    public String index( Map<String, Object> map ) {
        return listHumans( map );
    }

    @RequestMapping("/humans")
    public String listHumans( Map<String, Object> map ) {
        map.put( "human", new Human() );
        map.put( "humanList", humansService.list() );
        map.put( "city", new City() );
        map.put( "cityList", citiesService.list() );
        return "humans";
    }

    @RequestMapping("/cities")
    public String listCities( Map<String, Object> map ) {
        map.put( "city", new City() );
        map.put( "cityList", citiesService.list() );
        return "cities";
    }

    @RequestMapping(value = "/addCity", method = RequestMethod.POST)
    public String addCity(
    		@ModelAttribute("city") City city,
            BindingResult result) {
        citiesService.add( city );
        return "redirect:/cities";
    }

    @RequestMapping("/deleteCity/{cityId}")
    public String deleteCity(@PathVariable("cityId") Integer cityId) {
        citiesService.remove( cityId );
        return "redirect:/cities";
    }

    @RequestMapping(value = "/addHuman", method = RequestMethod.POST)
    public String addHuman(
    		@ModelAttribute("human") Human human,
            BindingResult result) {
    	if( human.getCity() == null ) {
            log.info( "MainController.addHuman: city is not set, name=" + human.getFirstName() );
    	} else {
            log.info( "MainController.addHuman: city.Id=" + human.getCity().getId() + ", city.Name=" + human.getCity().getName() );
    	}
        humansService.add( human );
        return "redirect:/humans";
    }

    @RequestMapping("/deleteHuman/{humanId}")
    public String deleteHuman(@PathVariable("humanId") Integer contactId) {
        humansService.remove(contactId);
        return "redirect:/humans";
    }

}
