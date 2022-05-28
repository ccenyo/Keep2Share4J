package org.github.commands;

import org.github.exceptions.Keep2ShareAuthenticationException;
import org.github.views.Keep2ShareAvailableDomainsView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Keep2ShareAvailableDomainsCommand extends PostCommand<Keep2ShareAvailableDomainsView> {

    private final String ENDPOINT = "https://keep2share.cc/api/v2/getDomainsList";
    private String accessToken;

    public Keep2ShareAvailableDomainsCommand(String accessToken) {
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
    protected Class<Keep2ShareAvailableDomainsView> getClassForMapper() {
        return Keep2ShareAvailableDomainsView.class;
    }

    @Override
    protected void validate() {
        Optional.ofNullable(accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));
    }

    @Override
    protected Map<String, Object> getBodyInJson() {
        return new HashMap<>(){{put("access_token", accessToken);}};
    }
}
