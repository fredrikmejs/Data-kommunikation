import java.io.*;
import java.net.*;

public class Client{


        public static void main(String[] argv) throws IOException {
            Socket control;
            PrintStream out;
            BufferedReader in;
            String sentence;
            String modifiedSentence;

            control = new Socket("test.rebex.net",21);
            out = new PrintStream(control.getOutputStream());
            in = new BufferedReader(new InputStreamReader(control.getInputStream()));
            System.out.println(in.readLine());                     // reads the welcome message from the host
            out.println("USER demo");// sends the usernameSend
            out.flush();
            System.out.println(in.readLine());

            out.println("PASS password");
            out.flush();
            System.out.println(in.readLine());

            out.println("list");
            out.flush();
            System.out.println(in.readLine());
           /* DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);

            sentence = inFromUser.readLine();
            outToServer.writeBytes( "USER demo"+ '\n');

            System.out.println(inFromServer.readLine());
            */

            //clientSocket.close();
        }
}


