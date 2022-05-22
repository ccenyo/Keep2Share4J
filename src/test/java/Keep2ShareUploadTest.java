import commands.*;
import exceptions.Keep2ShareException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class Keep2ShareUploadTest {

    Keep2ShareClient client;

    @Before
    public void initTest() {
        client = Keep2ShareClient.fromResource("env.properties");
    }

    @Test
    public void uploadFileToServerAndSuccess() {
        var command = client.upload(new File("src/test/resources/test.txt"));
        var response = command.call();
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getLink());
    }

    @Test
    public void uploadMultipleFileToServerAndSuccess() {
        var command = client.uploadMultiple(new File("src/test/resources/test"));
        var response = command.call();
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getResults());
    }

    @Test(expected = Keep2ShareException.class)
    public void uploadMultipleFileToServerAndErrorIncorrectToken() {
        client.setAccessToken("54545784");
        var command =  client.uploadMultiple(new File("src/test/resources/test"));
        command.call();
    }

    @Test
    public void getAvailableServersAndSuccess() {
        var command = client.listDomains();
        var response = command.call();
        Assert.assertNotNull(response);
    }

    @Test
    public void getFolderListsAndSuccess() {
        var command = client.listFolders();
        var response = command.call();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.getFoldersList().isEmpty());
        Assert.assertFalse(response.getFoldersIds().isEmpty());
    }

    @Test
    public void createFolderAndSuccess() {
        var command = client.createFolder();
        var response = command
                .setFolderName("test")
                .call();
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getId());
    }

    @Test
    public void getFileListAndSuccess() {
        var command = client.listFiles();
        var response = command.call();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.getFiles().isEmpty());
    }

    @Test
    public void getBalanceAndSuccess() {
        var command = client.balance();
        var response = command.call();
        Assert.assertNotNull(response);
        Assert.assertNotNull(response.getBalance());
    }

    @Test
    public void getFileinfoAndSuccess() {
        var command = client.filesInfo();
        var response = command
                .addFileId("065ecd808f43c")
                .addFileId("ebf15782ab763")
                .setExtendedInfo(true)
                .call();
        Assert.assertNotNull(response);
        Assert.assertFalse(response.getFiles().isEmpty());
    }
}
