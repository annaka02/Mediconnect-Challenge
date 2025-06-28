package com.conditionapi;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.api.EncodingEnum;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.RestfulServer;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;
@Component
@WebServlet(urlPatterns = {"/fhir/*"}, displayName = "FHIR Server")
public class FhirServer extends RestfulServer {
    @Override
    protected void initialize()  {
        setFhirContext(FhirContext.forR5());

        List<IResourceProvider> providers = new ArrayList<>();
        providers.add((IResourceProvider) new ConditionResourceProvider());
        setResourceProviders(providers);

        setDefaultResponseEncoding(ca.uhn.fhir.rest.api.EncodingEnum.JSON);
        setDefaultPrettyPrint(true);
        setDefaultResponseEncoding(EncodingEnum.JSON);

    }

}
