package com.conditionapi;

import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.r5.model.OperationOutcome;
import org.hl7.fhir.r5.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.r5.model.OperationOutcome.IssueType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<OperationOutcome> handleResourceNotFound(ResourceNotFoundException ex) {
        OperationOutcome outcome = new OperationOutcome();
        outcome.addIssue()
                .setSeverity(IssueSeverity.ERROR)
                .setCode(IssueType.NOTFOUND)
                .setDiagnostics(ex.getMessage());

        return new ResponseEntity<>(outcome, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<OperationOutcome> handleGenericException(Exception ex) {
        OperationOutcome outcome = new OperationOutcome();
        outcome.addIssue()
                .setSeverity(IssueSeverity.FATAL)
                .setCode(IssueType.EXCEPTION)
                .setDiagnostics(ex.getMessage());

        return new ResponseEntity<>(outcome, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
