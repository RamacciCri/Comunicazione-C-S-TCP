package c_s_comunicazioneunicast.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.BindException;
import java.net.SocketException;


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
        
    }
    
    public void leggi() {
        
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
