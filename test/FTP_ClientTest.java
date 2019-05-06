import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FTP_ClientTest {
    @Test
    void uploadTest () throws IOException {
        File file = new File(this.getClass().getResource("testDocSmall.txt").getFile().replace("%20", " "));
        FTP_Client client = new FTP_Client();
        client.startUp();
        client.uploadFile(file.getPath());
        client.getDir();
    }

    @Test
    void downloadTest () throws IOException {
        File file1 = new File(this.getClass().getResource("testDocSmall.txt").getFile().replace("%20", " "));
        File file2 = new File(this.getClass().getResource("testDocBig.txt").getFile().replace("%20", " "));

        FTP_Client client = new FTP_Client();
        client.startUp();
        client.uploadFile(file1.getPath());
        client.uploadFile(file2.getPath());

        client.downloadFile(file1.getName());
        client.downloadFile(file2.getName());
    }
}