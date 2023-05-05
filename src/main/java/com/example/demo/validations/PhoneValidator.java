package com.example.demo.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneValidator implements
    ConstraintValidator<PhoneNumber, String> {
 
        @Override
        public void initialize(PhoneNumber phone) {
        }
    
        @Override
        public boolean isValid(String phone,
            ConstraintValidatorContext cxt) {
            
            if (phone == null){
                return true;
            }
    
            return phone != null && phone.matches("\\([0-9]{2}\\) [0-9]{4,5}\\-[0-9]{4}");
        }
    
}
