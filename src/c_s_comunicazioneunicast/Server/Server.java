package c_s_comunicazioneunicast.Server;

import java.io.*;
import java.net.*;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;
    
    public Server(int porta) {
        this.porta = porta;
        try {
            this.serverSocket = new ServerSocket(this.porta);
            System.out.println("Il server e in ascolto sulla porta: " + this.porta);
        } 
        catch(BindException ex){
            System.err.println("Porta gia utilizzata");
            System.err.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.err.println("Impossibile connettersi con il client");
            System.err.println(ex.getMessage());
        }
    }
    
    public Socket attendi() {
        try{
            if(serverSocket != null) {
                this.clientSocket = serverSocket.accept();
                System.out.println("Connessione con il client effettuata");
            }
        } 
        catch(NullPointerException ex) {
            System.err.println(ex.getMessage());
        }
        catch(SocketException ex) {
            System.err.println(ex.getMessage());
        }
        catch(IOException ex) {
            System.err.println(ex.getMessage());
        }
        return clientSocket;
    }
    
    public void scrivi() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String messaggio = in.readLine();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            String messaggioMaiuscolo = messaggio.toUpperCase();
            out.println(messaggioMaiuscolo); // Invia la risposta al client
            out.flush();
        } catch (IOException ex) {
            System.err.println("Errore durante l'invio del messaggio: " + ex.getMessage());
        }
    }
    
    public void leggi() {
        try{
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String messaggio = in.readLine(); // Legge il messaggio dal client
        System.out.println("Messaggio ricevuto dal client: " + messaggio);
        } catch (IOException ex) {
            System.err.println("Errore durante l'invio del messaggio: " + ex.getMessage());
        }
        
    }
    
    public void chiudi() {
        try{
            if(serverSocket != null) {
                this.clientSocket.close();
                System.out.println("Connessione con il client chiusa");
            }
        } 
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void termina() {
        try {
            if(serverSocket != null && clientSocket.isClosed()) {
                serverSocket.close();
            }
        } 
        catch (IOException ex) {
            System.err.println(ex.getMessage());     
        }
    }
}
