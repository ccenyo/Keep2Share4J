package commands;

import exceptions.Keep2ShareAuthenticationException;
import views.Keep2ShareCreateFolderView;
import views.Keep2ShareFoldersListView;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Keep2ShareCreateFolderCommand extends PostCommand<Keep2ShareCreateFolderView> {

    public enum Access {
        publicAccess("public"),
        privateAccess("private"),
        premiumAccess("premium");
        Access(String name) {
            this.name = name;
        }
        private String name;

        public String getName() {
            return name;
        }
    }
    private final String ENDPOINT = "https://keep2share.cc/api/v2/createFolder";
    private final String accessToken;
    private String folderName;
    private String parentFolderId;
    private String parentPath = "/";
    private Access access = Access.publicAccess;
    private boolean isPublic = false;


    public Keep2ShareCreateFolderCommand setParentFolderId(String parentFolderId) {
        this.parentFolderId = parentFolderId;
        return this;
    }

    public Keep2ShareCreateFolderCommand setFolderName(String folderName) {
        this.folderName = folderName;
        return this;
    }

    public Keep2ShareCreateFolderCommand setParentPath(String parentPath) {
        this.parentPath = parentPath;
        return this;
    }

    public Keep2ShareCreateFolderCommand setAccess(Access access) {
        this.access = access;
        return this;
    }

    public Keep2ShareCreateFolderCommand setPublic(boolean aPublic) {
        isPublic = aPublic;
        return this;
    }

    public Keep2ShareCreateFolderCommand(String accessToken) {
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
    protected Class<Keep2ShareCreateFolderView> getClassForMapper() {
        return Keep2ShareCreateFolderView.class;
    }

    @Override
    protected void validate() {
        Optional.ofNullable(accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));
        Optional.ofNullable(folderName).orElseThrow(() -> new Keep2ShareAuthenticationException("The new folder name can not be null"));
        Optional.ofNullable(parentPath).orElseThrow(() -> new Keep2ShareAuthenticationException("The parent folder path name can not be null"));
        Optional.ofNullable(access).orElseThrow(() -> new Keep2ShareAuthenticationException("The parent access can not be null"));
    }


    @Override
    protected Map<String, Object> getBodyInJson() {
        return new HashMap<>(){
            {
                put("access_token", accessToken);
                put("name", folderName);
                put("parent", parentFolderId != null? parentFolderId : parentPath);
                put("access", access.getName());
                put("is_public", String.valueOf(isPublic));
            }
        };
    }
}
