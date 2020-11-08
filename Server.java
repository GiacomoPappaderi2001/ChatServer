import java.io.*;
import java.util.*;
import java.net.*;


public class Server{


    static Vector<ClientHandler> lc = new Vector<>();

    public static int i = 0;
   
   
 
    public void main() throws UnknownHostException, IOException{

        ServerSocket sers = new ServerSocket(3300);

        while (true){
           

            Socket s = sers.accept();
            System.out.println("Ricevuta nuova richiesta del client " + s);

            BufferedReader dis = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            ClientHandler ch = new ClientHandler(s,"Client" + i, dis, dos);
            System.out.println("Client " + i + " è entrato nella chat");
           
            Thread t = new Thread(ch);
            System.out.println("Attesa");
            System.out.println("Il client è ora nella lista");
            System.out.println("");

            lc.add(ch);

            t.start();

            i++;
        }
    }
} 