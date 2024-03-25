
package c_s_comunicazioneunicast.Client;


public class MainClient {

    
    public static void main(String[] args) {
        Client client = new Client("Cristian", "rosso");
        client.connetti("127.0.0.1", 1789);
        client.chiudi();
    }
    
}
