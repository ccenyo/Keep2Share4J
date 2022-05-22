package commands;

import exceptions.Keep2ShareAuthenticationException;
import views.Keep2SharePreUploadView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Keep2SharePreUploadCommand extends PostCommand<Keep2SharePreUploadView>{

    private final String ENDPOINT = "https://keep2share.cc/api/v2/getUploadFormData";
    private final String accessToken;
    private  String parentId = "/";

    public Keep2SharePreUploadCommand(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    protected Map<String, Object> getHeaders() {
        return new HashMap<>(){{put("Content-type", "application/json");}};
    }

    @Override
    protected String getEndpoint() {
        return ENDPOINT;
    }

    public Keep2SharePreUploadCommand setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @Override
    protected Class<Keep2SharePreUploadView> getClassForMapper() {
        return Keep2SharePreUploadView.class;
    }

    @Override
    protected void validate() {
        Optional.ofNullable(this.accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));
        Optional.ofNullable(this.parentId).orElseThrow(() -> new Keep2ShareAuthenticationException("The folderId can not be null"));
    }


    @Override
    protected Map<String, Object> getBodyInJson() {
        return new HashMap<>(){{put("access_token", accessToken);put("parent_id", parentId);}};
    }
}
