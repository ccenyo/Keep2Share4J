package commands;

import exceptions.Keep2ShareAuthenticationException;
import views.Keep2ShareFileListView;

import java.util.*;

public class Keep2ShareFilesInfoCommand extends PostCommand<Keep2ShareFileListView> {

    private final String ENDPOINT = "https://keep2share.cc/api/v2/getFilesInfo";
    private final String accessToken;
    private List<String> fileIds = new ArrayList<>();
    private boolean extendedInfo = false;


    public Keep2ShareFilesInfoCommand setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
        return this;
    }

    public Keep2ShareFilesInfoCommand addFileId(String fileId) {
        this.fileIds.add(fileId);
        return this;
    }

    public Keep2ShareFilesInfoCommand setExtendedInfo(boolean extendedInfo) {
        this.extendedInfo = extendedInfo;
        return this;
    }

    public Keep2ShareFilesInfoCommand(String accessToken) {
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
    protected Class<Keep2ShareFileListView> getClassForMapper() {
        return Keep2ShareFileListView.class;
    }

    @Override
    protected void validate() {
        Optional.ofNullable(accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));
        Optional.ofNullable(fileIds).orElseThrow(() -> new Keep2ShareAuthenticationException("The fileIds can not be null"));
    }


    @Override
    protected Map<String, Object> getBodyInJson() {
        return new HashMap<>(){
            {
                put("access_token", accessToken);
                put("ids", fileIds.toArray());
                put("extended_info", String.valueOf(extendedInfo));
            }
        };
    }
}
