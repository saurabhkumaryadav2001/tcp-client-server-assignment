# TCP Client-Server with AES Encryption

This project implements a **multi-client TCP server and client** in Java with **AES encryption** for secure communication.

It was built as part of a socket programming assignment.

---

## 📌 Features
- **Multi-client support** – server can handle multiple clients at once.
- **Predefined data lookup** – requests like `SetA-Two` return a time series.
- **AES encryption** – all messages between client and server are encrypted.
- **Configurable** – easily change host, port, or dataset in code.
- **Time-based responses** – sends current time `n` times at 1-second intervals.

---

## 🗂 Project Structure
tcp-assignment/
├── Server.java # Handles client connections and sends responses
├── Client.java # Sends requests and receives responses
├── CryptoUtils.java # AES encryption/decryption utility
└── README.md # Project documentation
