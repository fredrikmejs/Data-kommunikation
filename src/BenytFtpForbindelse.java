import java.util.Scanner;

public class BenytFtpForbindelse {
    public static void main(String[] a) throws Exception
    {
        Scanner scan = new Scanner(System.in);
        FtpForbindelse f = new FtpForbindelse();
        // bemærk - vær altid MEGET FORSIGTIG med at angive adgangskoder i en fil!!
        f.forbind("test.rebex.net","demo","password");

        // få liste over kommandoer som tjenesten kender
        f.modtagTekst("LIST");     // få liste over filer på værten
        f.sendKommando(scan.next());
        String indhold = "Indhold af en lille fil med navnet:\ntil.txt";
        f.sendTekst("STOR fil.txt", indhold);       // gem en tekstfil på værten

        indhold = f.modtagTekst("RETR fil.txt");    // hent filen igen
        System.out.println("Fil hentet med indholdet: "+indhold);
    }
}