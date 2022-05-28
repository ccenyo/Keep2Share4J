package org.github.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.github.exceptions.Keep2ShareAuthenticationException;
import org.github.exceptions.Keep2ShareException;
import org.github.views.Keep2ShareUploadMultipleView;
import org.github.views.Keep2ShareUploadView;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

public class Keep2ShareUploadMultipleCommand extends PostCommand<Keep2ShareUploadMultipleView>{

    private final String accessToken;
    private final List<File> files;
    private String parentId = "/";

    public Keep2ShareUploadMultipleCommand(String accessToken, List<File> files) {
        this.accessToken = accessToken;
        this.files = files;
    }

    public Keep2ShareUploadMultipleCommand(String accessToken, File folder) {
        this.accessToken = accessToken;
        if(!folder.isDirectory()) {
            throw new Keep2ShareException(folder.getName() +" is not a disrectory");
        }
        this.files = Utils.listFileTree(folder);

    }


    public Keep2ShareUploadMultipleCommand setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    @Override
    protected String getEndpoint() {
        return null;
    }

    @Override
    protected Class<Keep2ShareUploadMultipleView> getClassForMapper() {
        return null;
    }

    @Override
    protected void validate() {
        Optional.ofNullable(this.accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));
        Optional.ofNullable(this.files).orElseThrow(() -> new Keep2ShareAuthenticationException("The files can not be null"));
        Optional.ofNullable(this.parentId).orElseThrow(() -> new Keep2ShareAuthenticationException("The parentId can not be null"));
    }

    public Keep2ShareUploadMultipleView call() {

        files.forEach(file -> System.out.println(file.getAbsolutePath()));
        var result = new Keep2ShareUploadMultipleView();
        for(var file : files) {
            var responseunmashed = new Keep2SharePreUploadCommand(this.accessToken).setParentId(parentId).call();
            if (responseunmashed != null) {
                Unirest.setTimeouts(0, 0);
                HttpResponse<String> response;
                try {
                    response = Unirest.post(responseunmashed.getForm_action())
                            .field("file", file)
                            .field("ajax", responseunmashed.getForm_data().getAjax())
                            .field("signature", responseunmashed.getForm_data().getSignature())
                            .field("params", responseunmashed.getForm_data().getParams())
                            .asString();
                    var singleResult = unMashJson(response.getBody(), Keep2ShareUploadView.class);
                    result.getResults().put(file.getAbsolutePath(), singleResult);
                } catch (UnirestException | JsonProcessingException e) {
                    e.printStackTrace();
                }

            }
        }

        return result;
    }

    private Keep2ShareUploadView unMashJson(String jsonAsString, Class<Keep2ShareUploadView> clss) throws JsonProcessingException {
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
}
