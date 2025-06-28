package com.patientapi;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import javax.servlet.annotation.WebServlet;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
@WebServlet(urlPatterns = {"/fhir/*"}, displayName = "FHIR Server")
public class FHIRServer extends RestfulServer {
    @Override
    protected void initialize()  {
        setFhirContext(FhirContext.forR5());

        List<IResourceProvider> providers = new ArrayList<>();
        providers.add(new PatientResourceProvider());
        setResourceProviders(providers);

        setDefaultResponseEncoding(ca.uhn.fhir.rest.api.EncodingEnum.JSON);
        setDefaultPrettyPrint(true);
        setDefaultResponseEncoding(EncodingEnum.JSON);

    }

}
