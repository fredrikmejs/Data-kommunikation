import java.io.*;
import java.net.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client{


        public static void main(String[] argv) throws IOException {
            Socket control;
            Socket dataSocket;
            PrintStream out;
            BufferedReader in;


            control = new Socket("ftp.dlptest.com", 21);
            out = new PrintStream(control.getOutputStream());
            in = new BufferedReader(new InputStreamReader(control.getInputStream()));


            for(Object o : in.lines().toArray()){
                System.out.println(o);
            }

            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null){
                builder.append(str);
                builder.append("\n");
            }
            System.out.println(builder); // reads the welcome message from the host

            out.println("USER dlpuser@dlptest"); // sends the usernameSend
            out.flush();
            builder = new StringBuilder();
            while (true){
                str = in.readLine();
                if (str == null){
                    break;
                }
                builder.append(str);
            }
            System.out.println(builder);

            out.println("PASS 5p2tvn92R0di8FdiLCfzeeT0b");
            out.flush();
            builder = new StringBuilder();
            while (true){
                str = in.readLine();
                if (str == null){
                    break;
                }
                builder.append(str);
            }
            System.out.println(builder);

            out.println("SYST");
            out.flush();
            builder = new StringBuilder();
            while (true){
                str = in.readLine();
                if (str == null){
                    break;
                }
                builder.append(str);
            }
            System.out.println(builder);

            out.println("PWD");
            out.flush();
            builder = new StringBuilder();
            while (true){
                str = in.readLine();
                if (str == null){
                    break;
                }
                builder.append(str);
            }
            System.out.println(builder);

            out.println("PORT 130,225,93,166,14,178");
            out.flush();
            builder = new StringBuilder();
            while (true){
                str = in.readLine();
                if (str == null){
                    break;
                }
                builder.append(str);
            }
            System.out.println(builder);

            out.println("NLST");
            out.flush();
            builder = new StringBuilder();
            while (true){
                str = in.readLine();
                if (str == null){
                    break;
                }
                builder.append(str);
            }
            System.out.println(builder);

        }



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


            clientSocket.close();
        }

        public String ReadBigStringIn(BufferedReader buffIn) throws IOException {
        StringBuilder everything = new StringBuilder();
        String line;
        while( (line = buffIn.readLine()) != null) {
            everything.append(line);
        }
        return everything.toString();
    }*/
}


