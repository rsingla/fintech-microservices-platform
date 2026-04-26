package com.target.api.validation;

import java.util.Currency;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CurrencyTypeValidationImpl implements ConstraintValidator<CurrencyTypeValidation, String> {

  public List<String> valueList = null;
  public String required = null;

  @SuppressWarnings("unused")
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {

    if(value == null) {
      return true;
    }
    else if(value != null) {
      try {
        Currency currency = Currency.getInstance(value);
        currency.getDisplayName();
        return true;
      }
      catch(Exception ex) {
        return false;
      }
    }
    return false;
  }

  @Override
  public void initialize(CurrencyTypeValidation constraintAnnotation) {

  }

}