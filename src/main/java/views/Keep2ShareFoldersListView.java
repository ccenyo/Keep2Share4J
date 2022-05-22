package views;

import java.util.List;

public class Keep2ShareFoldersListView extends DefaultView{

    private List<String> foldersList;
    private List<String> foldersIds;

    public List<String> getFoldersList() {
        return foldersList;
    }

    public void setFoldersList(List<String> foldersList) {
        this.foldersList = foldersList;
    }

    public List<String> getFoldersIds() {
        return foldersIds;
    }

    public void setFoldersIds(List<String> foldersIds) {
        this.foldersIds = foldersIds;
    }
}
