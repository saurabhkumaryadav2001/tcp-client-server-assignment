import java.io.*;
import java.net.*;
import javax.crypto.*;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;
    private static final String SECRET_KEY = "1234567890123456"; 

    public static void main(String[] args) {
        try (
            Socket socket = new Socket(HOST, PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.print("Enter request (e.g., SetA-Two): ");
            String request = reader.readLine();
            String encryptedRequest = CryptoUtils.encrypt(request, SECRET_KEY);
            out.writeUTF(encryptedRequest);

            while (true) {
                try {
                    String encryptedResponse = in.readUTF();
                    String response = CryptoUtils.decrypt(encryptedResponse, SECRET_KEY);
                    System.out.println("Server: " + response);
                } catch (EOFException e) {
                    break; 
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
