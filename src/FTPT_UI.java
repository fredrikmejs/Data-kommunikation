import java.io.IOException;
import java.util.Scanner;

public class FTPT_UI {
    FTP_Client client;

    public FTPT_UI (FTP_Client client){
        this.client = client;
    }

    public void run() throws IOException {
        System.out.println("You can now navigate the directory of the server and upload/download files.\n");
        Scanner scanInt = new Scanner(System.in);
        Scanner scanln = new Scanner(System.in);
        int selection;

        while (true) {
            System.out.println("Choose an action:\n" +
                    "\t\t0\tquit\n" +
                    "\t\t1\tget directory\n" +
                    "\t\t2\tchange folder\n" +
                    "\t\t3\tdownload file\n" +
                    "\t\t4\tupload file\n");

            selection = scanInt.nextInt();
            switch (selection){
                case 0:
                    System.out.println("Closing...");
                    scanInt.close();
                    client.close();
                    System.exit(0);
                    break;
                case 1:
                    client.getDir();
                    break;
                case 2:
                    System.out.println("Which folder do you want to go to? (write \"..\" to go back)");
                    client.changeDir(scanln.nextLine());
                    break;
                case 3:
                    //TODO insert download method
                    break;
                case 4:
                    //TODO insert upload method
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
