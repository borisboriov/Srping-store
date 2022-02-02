package com.soskin.store.springstore.exceptions;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FieldsValidatorError {

    private List<String> errorFieldsMessages;

    public FieldsValidatorError(List<String> errorFieldsMessages) {
        this.errorFieldsMessages = errorFieldsMessages;
    }
}
