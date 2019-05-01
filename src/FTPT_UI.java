import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
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
                    "\t\t2\tget local directory\n" +
                    "\t\t3\tchange folder\n" +
                    "\t\t4\tchange local folder\n" +
                    "\t\t5\tdownload file\n" +
                    "\t\t6\tupload file\n");

            selection = scanInt.nextInt();
            switch (selection) {
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
                    client.getLocalDir();
                    break;
                case 3:
                    System.out.println("Which folder do you want to go to? (write \"..\" to go back)");
                    client.changeDir(scanln.nextLine());
                    break;
                case 4:
                    System.out.println("Which folder do you want to go to? (write \"..\" to go back)");
                    client.changeLocalDir(scanln.nextLine());
                    break;
                case 5:
                    System.out.println("Enter file name:");
                    client.downloadFile(scanln.nextLine());
                    break;
                case 6:
                    System.out.println("Enter file name:");
                    client.uploadFile(scanln.nextLine());
                    break;
                default:
                    System.out.println("Invalid command");
            }
        }
    }
}
