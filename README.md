# Kantin Binus — Food Court Ordering System

Sistem pemesanan makanan online untuk food court **Universitas Bina Nusantara (BINUS)**.  
Mahasiswa dapat memesan makanan dari tenant favorit, memilih waktu pengambilan, dan langsung mengambil pesanan tanpa perlu mengantri lama di kantin.

---

# Tentang Project

Project ini dibuat menggunakan **Java Swing** dan **MySQL** sebagai implementasi aplikasi desktop pemesanan makanan untuk lingkungan kampus.

Aplikasi memiliki tampilan modern dengan tema gelap (*dark mode UI*) serta pengalaman pengguna yang sederhana dan interaktif.

---

# Anggota Kelompok

| Nama | NIM | Jurusan |
|------|------|----------|
| **Nico Ferdy Hutajulu** | 2902712064 | Computer Science |
| **Vincelli Choandra** | 2902683184 | Computer Science |
| **Owen Santosa** | 2902708804 | Computer Science |

---

# Daftar Tenant

## 🍚 DMas
- Rice Bowl Ayam Katsu
- Rice Bowl Ayam Teriyaki

## 🍗 Lalapanku
- Nasi Ayam Bakar
- Nasi Ayam Kremes
- Nasi Sayur

## 🍲 Bakso Urat Mending
- Bakso
- Mie Ayam
- Mie Campur

---

# Fitur Utama

-  **Halaman utama interaktif**
-  **Form pemesanan makanan**
-  **Sistem antrian pesanan**
-  **Validasi input**
-  **Dark theme modern**
-  **Animasi dan hover effect**

---

# Tech Stack

| Teknologi | Deskripsi |
|-----------|------------|
| **Java (JDK 17+)** | Bahasa pemrograman utama |
| **Java Swing** | GUI Framework |
| **MySQL** | Database |
| **Apache Ant** | Build tool |
| **VS Code / NetBeans** | IDE Development |

---

# 📦 Prasyarat

Sebelum menjalankan project, pastikan sudah menginstall:

- Java JDK 17+
- MySQL Server
- Apache Ant

---

# Setup Database

Buat database:

```sql
CREATE DATABASE byos_network_javaers;
```

Gunakan database tersebut:

```sql
USE byos_network_javaers;
```

Buat tabel tenant:

```sql
CREATE TABLE dmas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    menu VARCHAR(100),
    pickup_time VARCHAR(20)
);

CREATE TABLE lalapanku (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    menu VARCHAR(100),
    pickup_time VARCHAR(20)
);

CREATE TABLE baksouratmending (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100),
    menu VARCHAR(100),
    pickup_time VARCHAR(20)
);
```

---

# Instalasi Apache Ant (macOS)

Install Apache Ant menggunakan Homebrew:

```bash
brew install ant
```

Cek apakah Apache Ant sudah terinstall:

```bash
ant -version
```

---

# Cara Menjalankan Project

Clone repository:

```bash
git clone https://github.com/NewX-Team/finpro-BYOS-kantin/tree/master
```

Masuk ke folder project:

```bash
cd FinalProject
```

Build dan jalankan project:

```bash
ant clean run
```

---

# Struktur Project

```bash
FinalProject/
│
├── src/
│   ├── database/
│   ├── gui/
│   ├── models/
│   └── main/
│
├── build.xml
├── manifest.mf
└── README.md
```

---

# Pengembangan Selanjutnya

- 💳 Sistem pembayaran digital
- 📱 Mobile version
- 🔔 Notifikasi pesanan siap diambil
- 📊 Dashboard admin tenant
- 🧾 Riwayat pemesanan pengguna

---

# Lisensi

Project ini dibuat untuk kebutuhan pembelajaran dan tugas akademik di **Universitas Bina Nusantara (BINUS)**.

