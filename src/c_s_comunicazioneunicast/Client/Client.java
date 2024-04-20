package c_s_comunicazioneunicast.Client;

import java.io.*;
import java.net.*;

public class Client {
    private String nome; 
    private String colore;
    private Socket socket;
    private BufferedReader inputTastiera;
    

    public Client(String nomeDefault, String coloreDefault) {
        this.nome = nomeDefault;
        this.colore = coloreDefault;
        this.inputTastiera = new BufferedReader(new InputStreamReader(System.in)); //per leggere input da tastiera
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
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            System.out.print("Inserisci il messaggio da inviare al server: ");
            String messaggio = inputTastiera.readLine();
            out.println(messaggio); //invia il messaggio al server
            out.flush();
        } catch (IOException ex) {
            System.err.println("Errore durante l'invio del messaggio: " + ex.getMessage());
        }
    }
    
    public void leggi() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String risposta = in.readLine(); //legge la risposta del server
            System.out.println("Risposta dal server: " + risposta);
        } catch (IOException ex) {
            System.err.println("Errore durante la lettura del messaggio: " + ex.getMessage());
        }
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
