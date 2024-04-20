package c_s_comunicazioneunicast.Server;


public class MainServer {

    
    public static void main(String[] args) {
        Server server = new Server(1789);
        server.attendi(); //attende la connessione del client
        server.scrivi();
        server.leggi();
        server.chiudi(); //chiusura del server
    }
    
}
