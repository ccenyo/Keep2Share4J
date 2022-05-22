import exceptions.Keep2ShareAuthenticationException;
import views.Keep2SharePreUploadView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Keep2SharePreUploadCommand extends PostCommand<Keep2SharePreUploadView>{

    private final String ENDPOINT = "https://keep2share.cc/api/v2/getUploadFormData";
    private final String accessToken;

    public Keep2SharePreUploadCommand(String accessToken) {
        this.accessToken = accessToken;
        Optional.ofNullable(this.accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));
    }

    @Override
    protected Map<String, Object> getHeaders() {
        return new HashMap<>(){{put("Content-type", "application/json");}};
    }

    @Override
    protected String getEndPoint() {
        return ENDPOINT;
    }

    @Override
    protected Class<Keep2SharePreUploadView> getClassForMapper() {
        return Keep2SharePreUploadView.class;
    }

    @Override
    protected String getBodyInJson() {
        return String.format("{\"access_token\":\"%s\"}", this.accessToken);
    }
}
