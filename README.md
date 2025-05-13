# 🔐 Encryption-Decryption Program (Java GUI)

This Java GUI application uses a **Linear Congruential Generator (LCG)** to generate keys for:

- **RSA encryption** (public and private keys)  
- **ECB encryption** (same key for encryption and decryption)  
- **SHA-1 hashing** (produces a fixed-size hash that cannot be reversed)

---

## 🚀 Features

- RSA key generation using LCG  
- ECB key generation using LCG  
- SHA-1 hashing (non-reversible)  
- Java Swing GUI for easy interaction  
- Encryption and decryption support (except SHA-1)

---

## 🧾 User Manual

### 🔒 Encryption

1. Enter the plaintext you want to encrypt  
2. Select the encryption algorithm (RSA, ECB, or SHA-1) using checkboxes  
3. Click the **Encrypt** button  
4. The ciphertext will appear in the output area  

### 🔓 Decryption

1. Enter the ciphertext (**must be numbers only**)  
2. Select the decryption algorithm (RSA or ECB) using checkboxes  
3. Click the **Decrypt** button  
4. The plaintext will appear in the output area  

🛑 SHA-1 does **not** support decryption

---

## 📸 Screenshots

### Encryption Example

![Encryption](https://github.com/user-attachments/assets/d2479a73-0268-4ff6-abd6-8d19011a952a)

### Decryption Example

![Decryption](https://github.com/user-attachments/assets/519425f2-92f6-42b3-adc3-06f7042ce83c)

---

## 📌 Notes

- RSA and ECB use LCG for key generation  
- SHA-1 is a one-way hash function  
- GUI built using Java Swing  
- RSA supports secure key pair generation  

---

## 🛠️ Technologies

- Java  
- Java Swing (for GUI)  

---
