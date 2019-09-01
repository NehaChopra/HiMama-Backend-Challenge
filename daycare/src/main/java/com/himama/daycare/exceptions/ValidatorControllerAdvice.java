package com.himama.daycare.exceptions;
/**
 * 
 * @author nchopra
 *
 */
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.himama.daycare.domain.network.response.BaseResponse;


@RestControllerAdvice(basePackages = {"com.himama.daycare"} )
@ControllerAdvice(basePackages = {"com.himama.daycare"} )
public class ValidatorControllerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @ResponseBody
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> authExceptionHandler(AuthException ex, WebRequest request) {
        BaseResponse response = new BaseResponse();
        Map<String, String> errors = ex.getErrors();
        if(!CollectionUtils.isEmpty(errors)) {
        	for (Map.Entry<String,String> entry : errors.entrySet())  {
        		response.setError(entry.getKey());
        		response.setErrorCode(entry.getValue());
        		response.setErrorDesc(ex);
        	}
        }
        logger.error(response.toString());
        return ResponseEntity.ok(response);
    }
    
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> apiExceptionHandler(ApiException ex, WebRequest request) {
        BaseResponse response = new BaseResponse();
        Map<String, Object> errors = ex.getErrors();
        if(!CollectionUtils.isEmpty(errors)) {
        	for (Map.Entry<String,Object> entry : errors.entrySet())  {
        		response.setError(entry.getKey());
        		response.setErrorDesc(entry.getValue());
        	}
        }
        logger.error(response.toString());
        return ResponseEntity.ok(response);
    }
} 