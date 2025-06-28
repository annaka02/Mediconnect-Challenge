package com.patientapi;

import ca.uhn.fhir.rest.annotation.*;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.IdType;
import org.hl7.fhir.r5.model.Patient;
import org.springframework.stereotype.Component;

import java.util.*;
@Component
public class PatientResourceProvider implements IResourceProvider {

    private Map<String, Patient> patientDB = new HashMap<>();

    @Override
    public Class<Patient> getResourceType() {
        return Patient.class;
    }

    @Create
    public MethodOutcome createPatient(@ResourceParam Patient patient) {
        String id = UUID.randomUUID().toString();
        patient.setId(id);
        patientDB.put(id, patient);

        MethodOutcome outcome = new MethodOutcome();
        outcome.setId(new IdType("Patient", id));
        return outcome;
    }

    @Read
    public Patient readPatient(@IdParam IdType id) {
        return patientDB.get(id.getIdPart());
    }

    @Search
    public List<Patient> searchByName(@RequiredParam(name = Patient.SP_NAME) StringParam name) {
        List<Patient> result = new ArrayList<>();

        for (Patient patient : patientDB.values()) {
            if (patient.hasName()) {
                String fullName = patient.getNameFirstRep().getNameAsSingleString().toLowerCase();
                if (fullName.contains(name.getValue().toLowerCase())) {
                    result.add(patient);
                }
            }
        }

        return result;
    }
    @Search
    public List<Patient> searchByIdentifier(@RequiredParam(name = Patient.SP_IDENTIFIER) TokenParam identifier) {
        List<Patient> result = new ArrayList<>();

        for (Patient patient : patientDB.values()) {
            if (patient.hasIdentifier()) {
                patient.getIdentifier().forEach(id -> {
                    if (id.getValue().equalsIgnoreCase(identifier.getValue())) {
                        result.add(patient);
                    }
                });
            }
        }

        return result;
    }



    @Update
    public MethodOutcome updatePatient(@IdParam IdType id, @ResourceParam Patient patient) {
        patient.setId(id.getIdPart());
        patientDB.put(id.getIdPart(), patient);

        MethodOutcome outcome = new MethodOutcome();
        outcome.setId(id);
        return outcome;
    }
    @Search
    public Bundle getAllPatients() {
        Bundle bundle = new Bundle();
        bundle.setType(Bundle.BundleType.SEARCHSET);
        bundle.setId(UUID.randomUUID().toString());
        bundle.setTimestamp(new Date());

        for (Patient patient : patientDB.values()) {
            Bundle.BundleEntryComponent entry = new Bundle.BundleEntryComponent();
            entry.setResource(patient);
            bundle.addEntry(entry);
        }

        bundle.setTotal(bundle.getEntry().size());
        return bundle;
    }

}