import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class Server {
    private static final int PORT = 5000;
    private static final String SECRET_KEY = "1234567890123456"; 

    
    private static final Map<String, Map<String, Integer>> collection = new HashMap<>();
    static {
        collection.put("SetA", Map.of("One", 1, "Two", 2));
        collection.put("SetB", Map.of("Three", 3, "Four", 4));
        collection.put("SetC", Map.of("Five", 5, "Six", 6));
        collection.put("SetD", Map.of("Seven", 7, "Eight", 8));
        collection.put("SetE", Map.of("Nine", 9, "Ten", 10));
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started. Listening on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            ) {
                String encryptedRequest = in.readUTF();
                String request = CryptoUtils.decrypt(encryptedRequest, SECRET_KEY);
                System.out.println("Received: " + request);

                String[] parts = request.split("-");
                if (parts.length != 2) {
                    sendEncrypted(out, "EMPTY");
                    return;
                }

                String setKey = parts[0];
                String nameKey = parts[1];

                if (collection.containsKey(setKey) && collection.get(setKey).containsKey(nameKey)) {
                    int times = collection.get(setKey).get(nameKey);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    for (int i = 0; i < times; i++) {
                        String timeNow = sdf.format(new Date());
                        sendEncrypted(out, timeNow);
                        Thread.sleep(1000);
                    }
                } else {
                    sendEncrypted(out, "EMPTY");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void sendEncrypted(DataOutputStream out, String message) throws Exception {
            String encryptedMessage = CryptoUtils.encrypt(message, SECRET_KEY);
            out.writeUTF(encryptedMessage);
        }
    }
}
