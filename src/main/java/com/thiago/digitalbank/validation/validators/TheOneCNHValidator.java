//package com.thiago.digitalbank.validation.validators;
//
//import com.thiago.digitalbank.validation.annotations.TheOneCNH;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//public class TheOneCNHValidator  implements ConstraintValidator<TheOneCNH, String> {
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Override
//    public boolean isValid(String cnh, ConstraintValidatorContext constraintValidatorContext) {
//        return !clienteRepository.findByCnh(cnh).isPresent();
//    }
//}
