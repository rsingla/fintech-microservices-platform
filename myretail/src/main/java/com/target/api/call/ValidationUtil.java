package com.target.api.call;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.target.retail.exception.ApiError;
import com.target.retail.model.Product;
import com.target.retail.model.ProductValidate;

@Component
public class ValidationUtil {

  @Autowired
  Validator validator;

  public Set<ApiError> dataValidation(Product product) {
    Set<ConstraintViolation<Product>> violations = validator.validate(product, ProductValidate.class);

    Set<ApiError> errorSet = new LinkedHashSet<>();
    if(violations == null) {
      return errorSet;
    }

    Iterator<ConstraintViolation<Product>> applicationErrorIterator = violations.iterator();
    while(applicationErrorIterator.hasNext()) {
      ConstraintViolation<Product> errors = applicationErrorIterator.next();
      ApiError error = new ApiError();
      error.setMessage(errors.getMessage());
      errorSet.add(error);
    }
    return errorSet;
  }

}
