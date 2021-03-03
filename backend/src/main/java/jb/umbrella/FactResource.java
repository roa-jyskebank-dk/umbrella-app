
package jb.umbrella;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;

@Path("/api")
@RequestScoped
public class FactResource {

    private static final JsonBuilderFactory JSON = Json.createBuilderFactory(Collections.emptyMap());

    @Inject()
    CatFactProvider catFactProvider;

    @GET
    @Path("/fact")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getFact() throws Exception {
        String catFact = catFactProvider.getRandomFact();
        return createResponse(catFact);
    }

    private JsonObject createResponse(String catFact) {
        return JSON.createObjectBuilder().add("fact", catFact).build();
    }
}
