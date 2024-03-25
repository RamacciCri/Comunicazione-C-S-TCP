package c_s_comunicazioneunicast.Client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private String nome; 
    private String colore;
    private Socket socket;

    public Client(String nomeDefault, String coloreDefault) {
        this.nome = nomeDefault;
        this.colore = coloreDefault;
    }
    
    public void connetti(String nomeServer, int portaServer) {
        try {
            System.out.println("Client " + nome + " in esecuzione ");
            this.socket = new Socket(nomeServer, portaServer);
            System.out.println("Connessione con il server "+ nomeServer +"avvenuta");
        } 
        catch (ConnectException ex) {
            //server non in ascolto
            System.err.println(ex.getMessage());
        } 
        catch (UnknownHostException ex) {
            //host sconosciuto
            System.err.println(ex.getMessage());
        } 
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void scrivi() {
        
    }
    
    public void leggi() {
        
    }
    
    public void chiudi() {
        try {
            if(socket != null) {
                socket.close();
                System.out.println("Connessione con il server chiusa");
            }
        } 
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
