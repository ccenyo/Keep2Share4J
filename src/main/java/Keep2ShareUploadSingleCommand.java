import exceptions.Keep2ShareAuthenticationException;
import exceptions.Keep2ShareException;
import views.Keep2ShareUploadView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Keep2ShareUploadSingleCommand extends PostCommand<Keep2ShareUploadView> {

    private String endPoint;
    private final Map<String, Object> params = new HashMap<>();

    public Keep2ShareUploadSingleCommand(String accessToken, File file) {
        if(file.isDirectory()) {
            throw new Keep2ShareException(file.getName() +" is a disrectory");
        }
        Optional.ofNullable(accessToken).orElseThrow(() -> new Keep2ShareAuthenticationException("The token can not be null"));

        var responseUnmashed = new Keep2SharePreUploadCommand(accessToken).call();
        if(responseUnmashed != null) {
            endPoint = responseUnmashed.getForm_action();
            params.put("file", file);
            params.put("ajax", responseUnmashed.getForm_data().getAjax());
            params.put("signature", responseUnmashed.getForm_data().getSignature());
            params.put("params", responseUnmashed.getForm_data().getParams());
        }

    }

    @Override
    protected Map<String, Object> getHeaders() {
        return new HashMap<>();
    }

    @Override
    protected String getEndPoint() {
        return endPoint;
    }

    @Override
    protected Class<Keep2ShareUploadView> getClassForMapper() {
        return Keep2ShareUploadView.class;
    }

    @Override
    protected Map<String, Object> getParamsInBody() {
        return params;
    }
}
