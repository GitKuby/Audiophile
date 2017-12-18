package pl.tau.restdemo.web;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tau.restdemo.domain.Person;
import pl.tau.restdemo.service.PersonManager;

import java.util.List;
import org.slf4j.Logger;
import pl.tau.restdemo.logging.Log;

/**
 * Simple web api demo -- try implementning post method
 *
 * Created by tp on 24.04.17.
 */
@RestController
public class PersonApi {

    @Autowired
    private PersonManager personManager;

    @Log
    private Logger LOG;

    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }

    @RequestMapping(value = "/persons/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Person getPerson(@PathVariable("id") Long id) {
        return personManager.getPerson(new Person(id));
    }

    @RequestMapping(value = "/persons",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Person> listPersons(@RequestParam(value = "filter", defaultValue = "") String filter) {
        LOG.debug("In listPersons");
        return personManager.getAllPersons();
    }

    @RequestMapping(value = "/persons",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST)
    public void addPerson(@RequestBody Person person) {
        personManager.addPerson(person);
    }

    @RequestMapping(value = "/persons/{id}",
            method = RequestMethod.DELETE)
    public void deletePerson(@PathVariable("id") Long id) {

        try {
            personManager.deletePerson(new Person(id));
        } catch (SQLException ex) {
            LOG.debug(String.format("SQL Exception: %s", ex.getMessage()));
        }
    }

}
