package com.conditionapi;

import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;

import org.hl7.fhir.r5.model.Condition;
import org.hl7.fhir.r5.model.IdType;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@CrossOrigin(origins = "http://localhost:4200")
@Component
public class ConditionResourceProvider implements IResourceProvider {

    @Autowired
    private WebClient webClient;

    // 🔹 Méthode utilitaire privée
    private boolean patientExists(String patientId) {
        try {
            webClient.get()
                    .uri("/Patient/" + patientId)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public Class<Condition> getResourceType() {
        return Condition.class;
    }

    private final Map<String, Condition> conditionStore = new ConcurrentHashMap<>();

    @Create
    public MethodOutcome createCondition(@ResourceParam Condition condition) {
        String patientId = condition.getSubject().getReferenceElement().getIdPart();

        if (!patientExists(patientId)) {
            throw new ResourceNotFoundException("Patient not found: " + patientId);
        }

        String id = UUID.randomUUID().toString();
        condition.setId(new IdType("Condition", id));
        conditionStore.put(id, condition);

        MethodOutcome outcome = new MethodOutcome();
        outcome.setId(condition.getIdElement());
        outcome.setResource(condition);
        return outcome;
    }

    @Read
    public Condition readCondition(@IdParam IdType id) {
        Condition condition = conditionStore.get(id.getIdPart());
        if (condition == null) {
            throw new ResourceNotFoundException("Condition not found for ID: " + id.getIdPart());
        }
        return condition;
    }

    @Search
    public List<Condition> searchByPatient(@RequiredParam(name = Condition.SP_PATIENT) StringParam patientId) {
        List<Condition> result = new ArrayList<>();
        for (Condition condition : conditionStore.values()) {
            if (condition.getSubject().getReferenceElement().getIdPart().equals(patientId.getValue())) {
                result.add(condition);
            }
        }
        return result;
    }

    @Delete
    public void deleteCondition(@IdParam IdType id) {
        conditionStore.remove(id.getIdPart());
    }

    @Update
    public MethodOutcome updateCondition(@IdParam IdType id, @ResourceParam Condition condition) {
        condition.setId(id);
        conditionStore.put(id.getIdPart(), condition);
        MethodOutcome outcome = new MethodOutcome();
        outcome.setId(condition.getIdElement());
        return outcome;
    }
}
