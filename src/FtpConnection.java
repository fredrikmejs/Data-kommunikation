import java.io.*;
import java.net.*;
import java.util.*;

//TODO add javadocs and translate the comments

public class FtpConnection
{
    private Socket control;
    private PrintStream out;
    private BufferedReader in;

    /**
     *
     * @return the answer from the server.
     * @throws IOException
     */
    private String readAnswer() throws IOException
    {
        while (true) {
            String s = in.readLine();
            System.out.println("received: "+s);
            if (s.length()>=3 && s.charAt(3)!='-' && Character.isDigit(s.charAt(0))
                    && Character.isDigit(s.charAt(1)) && Character.isDigit(s.charAt(2)))
                return s;   // afslut løkken og returner sidste linje med statuskode
        }
    }

    /**
     *
     * @param commando the commando we send to the server.
     * @return the answer of the message
     * @throws IOException
     */
    public String sendCommando(String commando) throws IOException
    {
        System.out.println("send: "+commando);
        out.println(commando);
        out.flush();         // makes sure the data is sended to the host before we reads the answer
        return readAnswer();
    }

    /**
     * Makes connection to the server
     * @param host the server name
     * @param user the username we are useing to log in with
     * @param password the needed password to log-in
     * @throws IOException
     */
    public void connect(String host, String user, String password)throws IOException
    {
        control = new Socket(host,21);
        out = new PrintStream(control.getOutputStream());
        in = new BufferedReader(new InputStreamReader(control.getInputStream()));
        readAnswer();                     // reads the welcome message from the host
        sendCommando("USER "+user);  // sends the usernameSend
        sendCommando("PASS "+password);    // Sends the password
    }


    /**
     * Gets an connection made to send date (files) to or from the host
     * @return the connection to the host
     * @throws IOException
     */
    private Socket getDataconnection() throws IOException
    {
        String machinePortNumber = sendCommando("PASV");
        StringTokenizer st = new StringTokenizer(machinePortNumber, "(,)");
        if (st.countTokens() < 7) throw new IOException("Not logged in");
        st.nextToken(); // Skips 5 steps before port number
        st.nextToken(); st.nextToken(); st.nextToken(); st.nextToken();
        int portNr = 256*Integer.parseInt(st.nextToken())
                + Integer.parseInt(st.nextToken());
        return new Socket(control.getInetAddress(), portNr); // connect til porten
    }

    /**
     * @param commando could be like a fil.txt
     * @param data
     * @throws IOException
     */
    public void sendText(String commando, String data) throws IOException
    {
        Socket dataCon = getDataconnection();
        PrintStream dataout = new PrintStream( dataCon.getOutputStream() );
        sendCommando(commando);        /* TODO måske formuleres anerledes */ // f.eks STOR fil.txt
        dataout.print(data);
        dataout.close();
        dataCon.close();
        readAnswer();
    }


    /**
     *
     * @param commando ask for a specific file
     * @return returns a stringbuilder
     * @throws IOException
     */
    public String recieveText(String commando) throws IOException
    {
        Socket datacon = getDataconnection();
        BufferedReader dataOut = new BufferedReader(new InputStreamReader(
                datacon.getInputStream()));
        sendCommando(commando); // Could be LIST eller RETR fil.txt,
        StringBuilder sb = new StringBuilder();
        String s = dataOut.readLine();
        while (s != null) {
            System.out.println("data: "+s);
            sb.append(s+"\n");
            s = dataOut.readLine();
        }
        dataOut.close();
        datacon.close();
        readAnswer();
        return sb.toString(); // returnér en streng med de data vi fik fra værten
    }
}