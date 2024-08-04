package br.dev.webit.application.jsonb;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.config.BinaryDataStrategy;
import jakarta.ws.rs.ext.ContextResolver;
import jakarta.ws.rs.ext.Provider;

@Provider
public class JsonbContextResolver implements ContextResolver<Jsonb> {

    private Jsonb jsonb;

    public JsonbContextResolver() {
        JsonbConfig config = new JsonbConfig()
                // .withAdapters(new UUIDAdapter())
                .withAdapters(new AgregadorIdAdapter())
                .withAdapters(new EntidadeIdAdapter())
                .withBinaryDataStrategy(BinaryDataStrategy.BASE_64_URL);
        this.jsonb = JsonbBuilder.create(config);
    }

    @Override
    public Jsonb getContext(Class<?> type) {
        return jsonb;
    }
}
