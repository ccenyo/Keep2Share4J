package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import exceptions.Keep2ShareException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import views.DefaultView;
import views.ErrorResponse;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class Command<T extends DefaultView> {
    private static final Logger LOG = LoggerFactory.getLogger(Command.class.getName());

    public enum Method {
        POST,
        GET
    }


    protected abstract Map<String, Object> getParamsInLink();
    protected abstract Map<String, Object> getParamsInBody();
    protected abstract Map<String, Object> getBodyInJson();
    protected abstract Map<String, Object> getHeaders();
    protected abstract String getEndpoint();
    protected abstract Method getMethod();
    protected abstract Class<T> getClassForMapper();
    protected abstract void validate();


    public T call() {
        validate();;
        Unirest.setTimeouts(0, 0);

        switch (getMethod()) {
            case POST -> {
                var post = Unirest.post(getEndpoint());
                if(getBodyInJson() != null) {
                    post.body(new JSONObject(getBodyInJson()).toString());
                }
                if(!getParamsInBody().isEmpty()) {
                    post.fields(getParamsInBody());
                }

                if(!getParamsInLink().isEmpty()) {
                    post.queryString(getParamsInLink());
                }

                try {
                    var response = unMashJson(post.asString().getBody());
                    if(response.getStatus_code() == 200 || response.getCode() == 200) {
                        return response;
                    }
                    throw new Keep2ShareException(unMashErrorJson(post.asString().getBody()));
                } catch (UnirestException | JsonProcessingException e) {
                    throw new Keep2ShareException(e);
                }
            }
        }

        return null;
    }

    private ErrorResponse unMashErrorJson(String jsonAsString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonAsString, ErrorResponse.class);
    }


    private T unMashJson(String jsonAsString, Class<T> clss) throws JsonProcessingException {
        if(jsonAsString.isEmpty()) {
            try {
                return clss.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(jsonAsString, clss);
    }

    private T unMashJson(String jsonAsString) throws JsonProcessingException {
        return unMashJson(jsonAsString, getClassForMapper());
    }
}
