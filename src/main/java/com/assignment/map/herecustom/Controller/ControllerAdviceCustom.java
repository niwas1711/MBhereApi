package com.assignment.map.herecustom.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.map.herecustom.Exceptions.ParkingNotfound;
import com.assignment.map.herecustom.Exceptions.PetrolStationNotfound;
import com.assignment.map.herecustom.Exceptions.ResturantNotfound;

@ControllerAdvice
public class ControllerAdviceCustom extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(ResturantNotfound.class)
    public ResponseEntity<Object> handleresturantNotFoundException(
    		ResturantNotfound ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.Message);

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParkingNotfound.class)
    public ResponseEntity<Object> handleparkingnotFoundException(
    		ParkingNotfound ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "NearBy parking not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(PetrolStationNotfound.class)
    public ResponseEntity<Object> handlePertrolStationFoundException(
    		PetrolStationNotfound ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "NearBy PetrolStation not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    
    
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, 
        HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("Something went wrong", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
	
	
	
}
