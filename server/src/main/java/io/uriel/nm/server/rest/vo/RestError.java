package io.uriel.nm.server.rest.vo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RestError
{
    private List<String> errors;
    
    public RestError(List<String> errors) 
    {
        this.errors = errors;
    }
 
    public RestError(String error) 
    {
        this(Collections.singletonList(error));
    }
 
    public RestError(String ... errors) 
    {
        this(Arrays.asList(errors));
    }
 
    public List<String> getErrors() 
    {
        return errors;
    }
 
    public void setErrors(List<String> errors) 
    {
        this.errors = errors;
    }
}
