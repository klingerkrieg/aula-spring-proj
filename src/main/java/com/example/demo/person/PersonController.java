package com.example.demo.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PutMapping("people/{personId}/addPhone/{phoneId}")
    public Person addPhone(@PathVariable("personId") Long personId
                            ,@PathVariable("phoneId") Long phoneId) {
        return personService.addPhone(personId, phoneId);
    }

}

