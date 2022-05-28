package org.github.commands;

import org.github.views.Keep2ShareGetBalanceView;

import java.util.HashMap;
import java.util.Map;

public class Keep2ShareGetBalanceCommand extends PostCommand<Keep2ShareGetBalanceView> {

    private final String ENDPOINT = "https://keep2share.cc/api/v2/getBalance";
    private final String accessToken;

    public Keep2ShareGetBalanceCommand(String accessToken) {
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
    protected Class<Keep2ShareGetBalanceView> getClassForMapper() {
        return Keep2ShareGetBalanceView.class;
    }

    @Override
    protected void validate() {

    }


    @Override
    protected Map<String, Object> getBodyInJson() {
        return new HashMap<>(){{put("access_token", accessToken);}};
    }
}
