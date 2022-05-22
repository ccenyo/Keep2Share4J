package commands;

import views.Keep2ShareFoldersListView;

import java.util.HashMap;
import java.util.Map;

public class Keep2ShareFoldersListCommand extends PostCommand<Keep2ShareFoldersListView> {

    private final String ENDPOINT = "https://keep2share.cc/api/v2/getFoldersList";
    private final String accessToken;

    public Keep2ShareFoldersListCommand(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    protected Map<String, Object> getHeaders() {
        return new HashMap<>();
    }

    @Override
    protected String getEndpoint() {
        return ENDPOINT;
    }

    @Override
    protected Class<Keep2ShareFoldersListView> getClassForMapper() {
        return Keep2ShareFoldersListView.class;
    }

    @Override
    protected void validate() {

    }


    @Override
    protected Map<String, Object> getBodyInJson() {
        return new HashMap<>(){{put("access_token", accessToken);}};
    }
}
