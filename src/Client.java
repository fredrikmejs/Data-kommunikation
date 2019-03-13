import java.io.*;
import java.net.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client{


        public static void main(String[] argv) throws IOException {
            Socket control;
            PrintStream out;
            BufferedReader in;
            String line;
            StringBuilder fullText = new StringBuilder();

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

            out.println("SYST");
            out.flush();
            System.out.println(in.readLine());

            out.println("PWD");
            out.flush();
            System.out.println(in.readLine());

            out.println("PORT 130,225,93,166,14,178");
            out.flush();
            System.out.println(in.readLine());

            out.println("NLST");
            out.flush();

            in.lines().forEach(System.out::println);

            /*while( stream.//(line = in.readLine()) != null){
                fullText.append(line);
            }
            System.out.println(fullText);*/

           /* DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(   new InputStreamReader(clientSocket.getInputStream()));

            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);

            sentence = inFromUser.readLine();
            outToServer.writeBytes( "USER demo"+ '\n');

            System.out.println(inFromServer.readLine());
            */

            //clientSocket.close();
        }

   /* public String ReadBigStringIn(BufferedReader buffIn) throws IOException {
        StringBuilder everything = new StringBuilder();
        String line;
        while( (line = buffIn.readLine()) != null) {
            everything.append(line);
        }
        return everything.toString();
    }*/
}


