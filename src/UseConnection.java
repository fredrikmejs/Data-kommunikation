import java.util.Scanner;

public class UseConnection {
    public static void main(String[] a) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        FtpConnection f = new FtpConnection();

        f.connect("speedtest.tele2.net ","anonymous","anonymous");

        // få liste over kommandoer som tjenesten kender
        f.recieveText("LIST");     // get a list of files from the host
        f.sendCommando(scan.next());
        String content = "content with the file name:\ntil.txt";
        f.sendText("BIG fil.txt", content);       // saves a .txt at the host

        content = f.recieveText("RETR fil.txt");    // gets the file again
        System.out.println("File has been downloaded with the content: "+content);
    }
}