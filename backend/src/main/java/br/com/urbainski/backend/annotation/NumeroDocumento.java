package br.com.urbainski.backend.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.urbainski.backend.annotation.validator.NumeroDocumentoValidator;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
@Documented
@Constraint(validatedBy = NumeroDocumentoValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NumeroDocumento {

	String message() default "{javax.validation.constraints.NumeroDocumento.message}";
	
	Class<?>[] groups() default {};
	
    Class<? extends Payload>[] payload() default {};
	
}