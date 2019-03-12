import java.io.IOException;
import java.util.Scanner;

public class UseConnection {

    Scanner scan = new Scanner(System.in);
    FtpConnection f = new FtpConnection();
    String text;

    public void run() throws IOException {

        f.connect("test.rebex.net","demo","password");

        // få liste over kommandoer som tjenesten kender
        f.recieveText("list");// get a list of files from the host

        getFile();

       /* String content = "content with the file name:\ntil.txt";
        f.sendText("BIG fil.txt", content);       // saves a .txt at the host

        content = f.recieveText("RETR fil.txt");    // gets the file again
        System.out.println("File has been downloaded with the content: "+content);
        */
    }

    public void getFile() throws IOException{

        System.out.println("Please write the name of the file you want to download");
        text = scan.next();
        f.recieveText("get");
        f.recieveText(text);

    }
}
