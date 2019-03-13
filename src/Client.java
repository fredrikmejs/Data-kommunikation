import java.io.*;
import java.net.*;

public class Client {


    public static void main(String[] argv) throws IOException {
        Socket control;
        PrintStream out;
        BufferedReader in;

        control = new Socket("test.rebex.net", 21); //Opens a connection to the FTP server, in the standard port 21
        out = new PrintStream(control.getOutputStream()); //Makes sure we can send commands to the server
        in = new BufferedReader(new InputStreamReader(control.getInputStream())); //Gets response from the server

        //Input username
        System.out.println(in.readLine());
        out.println("USER demo");
        out.flush();
        System.out.println(in.readLine());

        //Input password
        out.println("PASS password");
        out.flush();
        System.out.println(in.readLine());


        // Defines the port used to receive data.
        out.println("PORT 130,225,93,166,14,178");
        out.flush();
        System.out.println(in.readLine());


        //Download readme.txt
        out.println("RETR readme.txt");
        out.flush();

        do {
            String str = in.readLine();
            System.out.println(str);
        } while (in.ready());


        //Close connection
        control.close();

    }
}


