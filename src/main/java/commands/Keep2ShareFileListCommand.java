package commands;

import exceptions.Keep2ShareAuthenticationException;
import views.Keep2ShareFileListView;

import java.util.*;

public class Keep2ShareFileListCommand extends PostCommand<Keep2ShareFileListView> {

    private final String ENDPOINT = "https://keep2share.cc/api/v2/getFilesList";
    private final String accessToken;
    private String parentFolder = "/";
    private Integer limit = 100;
    private Integer offset = 0;
    private boolean onlyAvailable = false;
    private boolean extendedInfo = false;



    public Keep2ShareFileListCommand(String accessToken) {
        this.accessToken = accessToken;
    }

    public Keep2ShareFileListCommand setParentFolder(String parentFolder) {
        this.parentFolder = parentFolder;
        return this;
    }

    public Keep2ShareFileListCommand setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Keep2ShareFileListCommand setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public Keep2ShareFileListCommand setOnlyAvailable(boolean onlyAvailable) {
        this.onlyAvailable = onlyAvailable;
        return this;
    }

    public Keep2ShareFileListCommand setExtendedInfo(boolean extendedInfo) {
        this.extendedInfo = extendedInfo;
        return this;
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
    protected Class<Keep2ShareFileListView> getClassForMapper() {
        return Keep2ShareFileListView.class;
    }

    @Override
    protected void validate() {
        Optional.ofNullable(accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));
        Optional.ofNullable(parentFolder).orElseThrow(() -> new Keep2ShareAuthenticationException("The new folder name can not be null"));
        Optional.ofNullable(limit).orElseThrow(() -> new Keep2ShareAuthenticationException("The limit can not be null"));
        Optional.ofNullable(offset).orElseThrow(() -> new Keep2ShareAuthenticationException("The offset can not be null"));
    }


    @Override
    protected Map<String, Object> getBodyInJson() {
        return new HashMap<>(){
            {
                put("access_token", accessToken);
                put("parent", parentFolder);
                put("limit", limit);
                put("offset", offset);
                put("only_available", String.valueOf(onlyAvailable));
                put("extended_info", String.valueOf(extendedInfo));
            }
        };
    }
}
