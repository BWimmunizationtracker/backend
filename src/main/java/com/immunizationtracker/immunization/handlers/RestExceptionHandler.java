package com.immunizationtracker.immunization.handlers;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;

import java.util.Date;

// bean shared across controller classes
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler
{
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ResourceNotFoundException.class, EntityNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTitle(ex.getPropertyName());
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(request.getDescription(true));

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle(ex.getRequestURL());
        errorDetail.setDetail(request.getDescription(true));
        errorDetail.setDeveloperMessage("Rest Handler Not Found (check for valid URI)");

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTitle(ex.getMethod());
        errorDetail.setDetail(request.getDescription(true));
        errorDetail.setDeveloperMessage("HTTP Method Not Valid for Endpoint (check for valid URI and proper HTTP Method)");

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }
}
