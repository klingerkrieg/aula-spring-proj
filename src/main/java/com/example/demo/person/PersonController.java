package com.example.demo.person;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.Validator;

import com.example.demo.validations.ControllerWithValidation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;

@RestController
public class PersonController extends ControllerWithValidation {

    @Autowired
    private PersonService personService;

    @PostMapping("people/{personId}/addPhone/{phoneId}")
    public Person addPhone(@PathVariable("personId") Long personId
                            ,@PathVariable("phoneId") Long phoneId) {


            return personService.addPhone(personId, phoneId);
    }

    @PostMapping("/people/")
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@Valid @RequestBody Person person) {
        return personService.save(person);
    }


    @Autowired
    jakarta.validation.Validator validator;

    @PatchMapping("/people/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Person update(@PathVariable("id") Long id, @RequestBody Person person) throws MethodArgumentNotValidException {
        Optional<Person> obj = personService.findById(id);
        Person objGet = obj.get();

        if (objGet == null){
            //throw new MethodArgumentNotValidException("Person not found: " + id);
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        objGet.setEmail(person.getEmail());

        
        Set<ConstraintViolation<Person>> violations = validator.validate(objGet);
        if (!violations.isEmpty()) {
            throw new MethodArgumentNotValidException(null, result);
        }

        return personService.save(objGet);
    }

}

