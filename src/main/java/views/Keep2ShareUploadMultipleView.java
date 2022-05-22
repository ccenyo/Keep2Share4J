package views;

import java.util.HashMap;
import java.util.Map;

public class Keep2ShareUploadMultipleView extends DefaultView{

    Map<String, Keep2ShareUploadView> results = new HashMap<>();

    public Map<String, Keep2ShareUploadView> getResults() {
        return results;
    }

    public void setResults(Map<String, Keep2ShareUploadView> results) {
        this.results = results;
    }
}
