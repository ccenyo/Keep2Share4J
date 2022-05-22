package views;

public class Keep2ShareUploadView extends DefaultView{


    private boolean success;

    private String user_file_id;
    private String link;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }



    public String getUser_file_id() {
        return user_file_id;
    }

    public void setUser_file_id(String user_file_id) {
        this.user_file_id = user_file_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
