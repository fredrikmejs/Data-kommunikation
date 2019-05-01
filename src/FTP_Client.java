import java.io.*;
import java.net.*;
import java.util.Objects;

public class FTP_Client {
    private Socket controlSocket; //sends commands to the server and receives the response
    private PrintStream conOut;
    private BufferedReader conIn;

    private Socket dataSocket; //Uploads and downloads files
    private PrintStream dataOut;
    private BufferedReader dataIn;

    private File localDir;

    public void startUp() throws IOException {

        connect("ftp.dlptest.com", 21); //alt: test.rebex.net
        logIn("dlpuser@dlptest.com", "5p2tvn92R0di8FdiLCfzeeT0b"); //alt: "demo","password"
        getSystem();
        //setupDataTransfer();
        localDir = new File(System.getProperty("user.home"));
        System.out.println("local directory: " + localDir);
        System.out.println("\n\n---------------------------------------\n\n");
    }





    private void connect(String host, int port) throws IOException {

        try {
            controlSocket = new Socket(host, port);
            conOut = new PrintStream(controlSocket.getOutputStream());
            conIn = new BufferedReader(new InputStreamReader(controlSocket.getInputStream()));
            readConAnswer();
        } catch (IOException e) {
            System.out.println("An error occurred while connecting to the server");
            e.printStackTrace();
        }

    }

    private void logIn(String username, String password) throws IOException {
        conOut.println("USER " + username); // sends the usernameSend
        conOut.flush();
        readConAnswer();

        conOut.println("PASS " + password);
        conOut.flush();
        readConAnswer();
    }

    private void getSystem() throws IOException {
        conOut.println("SYST"); //gets the remote system type
        conOut.flush();
        readConAnswer();
    }

    private void setupDataTransfer() throws IOException {
        int port;

        conOut.println("PASV");
        String[] reply = readConAnswer().replace(")", "").replace(".", "").split("," );
        port = 256 * Integer.parseInt(reply[4]) + Integer.parseInt(reply[5]);

        dataSocket = new Socket(controlSocket.getInetAddress(), port); //TODO change ths to passive
        dataOut = new PrintStream(dataSocket.getOutputStream());
        dataIn = new BufferedReader(new InputStreamReader(dataSocket.getInputStream()));
        conOut.flush();
    }

    public void getDir() throws IOException {
        setupDataTransfer();
        conOut.println("PWD"); //Print the working directory on the remote machine
        conOut.flush();
        readConAnswer();

        conOut.println("LIST"); //gets content of remote directory
        conOut.flush();
        readConAnswer();

        String s;
        while ((s = dataIn.readLine()) != null) {
            System.out.println(s);
        }
        readConAnswer();
        System.out.println("\n");
    }

    public void getLocalDir() {
        System.out.println("\"" + localDir + "\"" + " is current local directory.");
        File[] dirList = localDir.listFiles();
        if (dirList != null) {
            for (File f : dirList) {
                if (f.isDirectory()){
                    System.out.println("<DIR>\t" + f.getName());
                } else if (f.isFile()) {
                    System.out.println("\t" + f.length() + " " + f.getName());
                }
            }
        }
        System.out.println("\n\n");
    }

    /**
     * This is from our example project TODO remember to add the source to avoid plagiarism
     *
     * @return the answer from the server.
     * @throws IOException
     */
    @SuppressWarnings("Duplicates")
    private String readConAnswer() throws IOException {
        while (true) {
            String s = conIn.readLine();
            System.out.println("received: " + s);
            if (s.length() >= 3 && s.charAt(3) != '-' && Character.isDigit(s.charAt(0))
                    && Character.isDigit(s.charAt(1)) && Character.isDigit(s.charAt(2)))
                return s;   // afslut løkken og returner sidste linje med statuskode
        }
    }

    public void close() throws IOException {
        conOut.println("quit");
        conOut.flush();
        readConAnswer();

        controlSocket.close();
        conIn.close();
        conOut.close();

        dataSocket.close();
        dataIn.close();
        dataOut.close();
    }

    public void changeDir(String directory) throws IOException {
        StringBuilder command = new StringBuilder();
        command.append("CWD ");

        if (directory.equalsIgnoreCase("..")){
            conOut.println("PWD");
            conOut.flush();
            String temp = conIn.readLine();
            String currentDir = temp.split("[\"]")[1];
            String[] adress = currentDir.split("/");
            currentDir = "";
            for (int i = 0; i < adress.length - 1; i++) {
                currentDir = currentDir.concat("/" + adress[i]);
            }
            command.append(currentDir);
        } else {
            command.append(directory);
        }

        conOut.println(command);
        conOut.flush();
        readConAnswer();
        getDir();
    }

    public void changeLocalDir (String directory) {
        if (directory.equalsIgnoreCase("..")) {
            localDir = localDir.getParentFile();
        } else {
            File temp = new File(localDir + "/" + directory);
            if (temp.exists() && temp.isDirectory()) {
                localDir = temp;
                getLocalDir();
            } else {
                System.out.println("Directory not found");
            }
        }
    }

    public void downloadFile (String fileName) throws IOException { //can't download the png's properly. why?
        conOut.println("SIZE " + fileName);
        conOut.flush();
        String sizeString = readConAnswer();
        int size;
        try {
            size = Integer.parseInt(sizeString.split(" ")[1]);
        } catch (NumberFormatException e) {
            return;
        }
        setupDataTransfer();
        conOut.println("RETR " + fileName);
        conOut.flush();
        readConAnswer();

        if (size < 1024) {//todo make it 1024 again
            String s;
            while ((s = dataIn.readLine()) != null) {
                System.out.println(s);
            }
        } else {
            File newFile = new File(localDir + "/" + fileName);
            if (newFile.exists()) {
                System.out.println("Aborted! A file would have been overwritten.");
            } else {
                if (!newFile.createNewFile()) {
                    System.out.println("Error: Couldn't create file.");
                    return;
                }
                PrintWriter writer = new PrintWriter(new FileWriter(newFile));

                String s;
                while ((s = dataIn.readLine()) != null) {
                    writer.println(s);
                }
                writer.close();
                System.out.println("Downloaded file to: " + newFile);
            }

        }
        readConAnswer();
    }

    public void uploadFile (String fileName) throws IOException {
        File file = new File(localDir + "/" + fileName);
        if (!file.exists() && file.isFile()) {
            System.out.println("Error: File [" + file + "] doesn't exist");
            return;
        }
        BufferedReader reader =  new BufferedReader(new FileReader(file));

        setupDataTransfer();
        conOut.println("STOR " + fileName);
        conOut.flush();
        readConAnswer();

        String s;
        while ((s = reader.readLine()) != null) {
            dataOut.println(s);
        }
        dataSocket.close();
        readConAnswer();

    }

}
