package br.com.urbainski.backend.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import br.com.urbainski.backend.annotation.NumeroDocumento;

/**
 * 
 * @author Cristian Urbainski
 * @since 28/09/2019
 *
 */
public class NumeroDocumentoValidator implements ConstraintValidator<NumeroDocumento, String>{

private CPFValidator cpfValidator;
	
	private CNPJValidator cnpjValidator;
	
	@Override
	public void initialize(NumeroDocumento constraintAnnotation) {
		cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);
		
		cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return true;			
		}
		
		boolean isCpf = value.length() == 11;
		boolean isCnpj = value.length() == 14;
		
		if (isCpf) {
			return cpfValidator.isValid(value, context);
		} else if (isCnpj) {
			return cnpjValidator.isValid(value, context);
		}
		return false;
	}
	
}