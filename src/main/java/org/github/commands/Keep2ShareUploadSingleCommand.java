package org.github.commands;

import org.github.exceptions.Keep2ShareAuthenticationException;
import org.github.exceptions.Keep2ShareException;
import org.github.views.Keep2ShareUploadView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Keep2ShareUploadSingleCommand extends PostCommand<Keep2ShareUploadView> {

    private String endPoint;
    private String parentId = "/";
    private final File file;
    private final String accessToken;

    private final Map<String, Object> params = new HashMap<>();

    public Keep2ShareUploadSingleCommand(String accessToken, File file) {
        this.accessToken = accessToken;
        this.file = file;
    }

    @Override
    public Keep2ShareUploadView call() {
        var responseUnmashed = new Keep2SharePreUploadCommand(accessToken)
                .setParentId(this.parentId)
                .call();
        if(responseUnmashed != null) {
            endPoint = responseUnmashed.getForm_action();
            params.put("file", file);
            params.put("ajax", responseUnmashed.getForm_data().getAjax());
            params.put("signature", responseUnmashed.getForm_data().getSignature());
            params.put("params", responseUnmashed.getForm_data().getParams());
        }

        return super.call();
    }

    public Keep2ShareUploadSingleCommand setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @Override
    protected Map<String, Object> getHeaders() {
        return new HashMap<>();
    }

    @Override
    protected String getEndpoint() {
        return endPoint;
    }

    @Override
    protected Class<Keep2ShareUploadView> getClassForMapper() {
        return Keep2ShareUploadView.class;
    }

    @Override
    protected void validate() {
        if(file.isDirectory()) {
            throw new Keep2ShareException(file.getName() +" is a disrectory");
        }
        if (!file.exists()) {
            throw new Keep2ShareException(file.getName() +" does not exists");
        }
        Optional.ofNullable(accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));
    }

    @Override
    protected Map<String, Object> getParamsInBody() {
        return params;
    }
}
