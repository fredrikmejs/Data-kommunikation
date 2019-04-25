import java.io.IOException;
import java.util.Scanner;

public class FTPT_UI {
    FTP_Client client;

    public FTPT_UI (FTP_Client client){
        this.client = client;
    }

    public void run() throws IOException {
        System.out.println("You can now navigate the directory of the server and upload/download files.\n");
        Scanner scan = new Scanner(System.in);
        int selection;

        while (true) {
            client.getDir();
            System.out.println("Choose an action:\n" +
                    "\t\t0)\tquit\n" +
                    "\t\t1)\tchange folder\n" +
                    "\t\t2)\tdownload file\n" +
                    "\t\t3)\tupload file\n");

            selection = scan.nextInt();
            switch (selection){
                case 0:
                    scan.close();
                    client.close();
                    System.exit(0);
                    break;
                case 1:
            }
        }
    }
}
