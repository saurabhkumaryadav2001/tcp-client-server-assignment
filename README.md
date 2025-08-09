TCP Client-Server with AES Encryption
This project implements a multi-client TCP server and client in Java with AES encryption for secure communication.

It was built as part of a socket programming assignment.

📌 Features
Multi-client support – server can handle multiple clients at once.

Predefined data lookup – requests like SetA-Two return a time series.

AES encryption – all messages between client and server are encrypted.

Configurable – easily change host, port, or dataset in code.

Time-based responses – sends current time n times at 1-second intervals.

🗂 Project Structure
bash
Copy
Edit
tcp-assignment/
 ├── Server.java       # Handles client connections and sends responses
 ├── Client.java       # Sends requests and receives responses
 ├── CryptoUtils.java  # AES encryption/decryption utility
 └── README.md         # Project documentation
⚙️ How to Run
1. Compile the code
Open a terminal in the project folder and run:

javac Server.java Client.java CryptoUtils.java
2. Start the server
arduino
Copy
Edit
java Server
You should see:

Server started. Listening on port 5000
3. Start the client (in another terminal)

java Client
Enter a request when prompted, for example:

SetA-Two
🧪 Example Output
Server terminal:

Server started. Listening on port 5000
Received: SetA-Two
Client terminal:

Enter request (e.g., SetA-Two): SetA-Two
Server: 09-08-2025 09:34:51
Server: 09-08-2025 09:34:52
❌ Invalid Request Example
If you type:

SetX-One
The client will display:

Server: EMPTY
🔒 Encryption Details
Algorithm: AES (Advanced Encryption Standard)

Key length: 128-bit (16 characters)

Data format: Base64 encoded for transmission

All requests and responses are encrypted before being sent, and decrypted upon receipt.

👨‍💻 Author
SAURABH
