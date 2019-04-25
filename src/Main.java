import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FTP_Client client = new FTP_Client();
        client.startUp();
        FTPT_UI ui = new FTPT_UI(client);
        ui.run();


    }
}
