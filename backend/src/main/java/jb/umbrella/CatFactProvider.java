
package jb.umbrella;

import io.helidon.media.jsonp.JsonpSupport;
import io.helidon.webclient.WebClient;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class CatFactProvider {

    private WebClient webClient;

    public CatFactProvider() {
        this.webClient = WebClient.builder()
                .addReader(JsonpSupport.reader())
                .addWriter(JsonpSupport.writer())
                .addMediaSupport(JsonpSupport.create())
                .build();
    }

    public String getRandomFact() throws Exception {
        JsonArray result = webClient.get().uri("https://cat-fact.herokuapp.com/facts")
                .request(JsonArray.class)
                .get();

        if (result.size() > 0) {
            int factIndex = (int) (Math.random() * (result.size() - 1));
            JsonObject entry = result.get(factIndex).asJsonObject();
            return entry.getString("text");
        } else {
            throw new Exception("No cat facts could be found");
        }
    }
}
