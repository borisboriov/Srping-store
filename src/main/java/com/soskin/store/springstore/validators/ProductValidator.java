package com.soskin.store.springstore.validators;


import com.soskin.store.springstore.dto.ProductDto;
import com.soskin.store.springstore.exceptions.ValidationException;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {

    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if (productDto.getCost() < 1){
            errors.add("Price less then 1! ");
        }
        if (productDto.getTitle().isBlank()){
             errors.add("Title input cant be  empty");
        }
        if (!errors.isEmpty()){
            throw  new ValidationException(errors);
        }
    }


}
