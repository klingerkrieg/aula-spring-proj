package com.example.demo.person;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("people/{personId}/addPhone/{phoneId}")
    public Person addPhone(@PathVariable("personId") Long personId
                            ,@PathVariable("phoneId") Long phoneId) {


            return personService.addPhone(personId, phoneId);

            /*ObjectMapper obj = new ObjectMapper();
            String jsonStr = "{}";
            try {
                jsonStr = obj.writeValueAsString(person);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return jsonStr;*/
    }


    @PostMapping("/people/")
    @ResponseStatus(HttpStatus.CREATED)
    public Person save(@Valid @RequestBody Person person) {
        return personService.save(person);
    }    

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}

