package com.patientapi;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.server.RestfulServer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.List;

@Configuration
public class FHIRServerConfig {
    @Bean
    public ServletRegistrationBean<RestfulServer> fhirServlet() {
        RestfulServer server = new RestfulServer(FhirContext.forR5());
        server.setResourceProviders(List.of(new PatientResourceProvider()));
        return new ServletRegistrationBean<>(server, "/fhir/*");
    }

}
