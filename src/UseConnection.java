import java.io.IOException;
import java.util.Scanner;

public class UseConnection {

    Scanner scan = new Scanner(System.in);
    FtpConnection con = new FtpConnection();
    String text;

    public void run() throws IOException {

        con.connect("test.rebex.net","demo","password");

        // få liste over kommandoer som tjenesten kender
        con.recieveText("list");// get a list of files from the host

        getFile();

       /* String content = "content with the file name:\ntil.txt";
        con.sendText("BIG fil.txt", content);       // saves a .txt at the host

        content = con.recieveText("RETR fil.txt");    // gets the file again
        System.out.println("File has been downloaded with the content: "+content);
        */
    }

    public void getFile() throws IOException{

        System.out.println("Please write the name of the file you want to download");
        text = scan.next();
        con.recieveText("get");
        con.recieveText(text);

    }
}
