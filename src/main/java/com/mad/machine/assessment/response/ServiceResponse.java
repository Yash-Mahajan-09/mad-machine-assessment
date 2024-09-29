package com.mad.machine.assessment.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Data
public class ServiceResponse<T>{

    private T body;
    private HttpStatus statusCode;
    private String error;

}
