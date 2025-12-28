module br.dev.webit.jakart.application {
    // webit
    requires br.dev.webit.jakartapoc.utils;
    requires br.dev.webit.jakartapoc.domain;

    // 3rd parties
    requires static org.slf4j;

    // jakarta
    requires jakarta.persistence;
    requires jakarta.cdi;
    requires jakarta.transaction;
    requires jakarta.ws.rs;
    requires jakarta.json.bind;

    provides br.dev.webit.domain.AgregadorRepository with br.dev.webit.application.jaxrs.AgregadorJPARepository;
}
