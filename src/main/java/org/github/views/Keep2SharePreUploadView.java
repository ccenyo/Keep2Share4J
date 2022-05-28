package org.github.views;

public class Keep2SharePreUploadView extends DefaultView{

    private String form_action;
    private String file_field;
    private FormData form_data;

    public String getForm_action() {
        return form_action;
    }

    public void setForm_action(String form_action) {
        this.form_action = form_action;
    }

    public String getFile_field() {
        return file_field;
    }

    public void setFile_field(String file_field) {
        this.file_field = file_field;
    }

    public FormData getForm_data() {
        return form_data;
    }

    public void setForm_data(FormData form_data) {
        this.form_data = form_data;
    }

    public static class  FormData {
        private String ajax;
        private String params;
        private String signature;

        public String getAjax() {
            return ajax;
        }

        public void setAjax(String ajax) {
            this.ajax = ajax;
        }

        public String getParams() {
            return params;
        }

        public void setParams(String params) {
            this.params = params;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }
    }
}
