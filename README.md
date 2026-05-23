# 🍜 Kantin Binus — Food Court Ordering System

Sistem pemesanan makanan online untuk food court **Universitas Binus**. Pesan makanan dari tenant favoritmu, pilih waktu pengambilan, dan langsung ambil tanpa perlu antri lama di kantin!

---

## 👥 Anggota Kelompok

| Nama | NIM | Jurusan |
|------|-----|---------|
| Nico Ferdy Hutajulu | 2902712064 | Computer Science |
| Vincelli Choandra | 2902683184 | Computer Science |
| Owen Santosa | 2902708804 | Computer Science |

---

## 📋 Daftar Tenant

| Tenant | Menu |
|--------|------|
| 🍚 **DMas** | Rice Bowl Ayam Katsu, Rice Bowl Ayam Teriyaki |
| 🍗 **Lalapanku** | Nasi Ayam Bakar, Nasi Ayam Kremes, Nasi Sayur |
| 🍲 **Bakso Urat Mending** | Bakso, Mie Ayam, Mie Campur |

---

## ✨ Fitur Utama

- 🏠 **Halaman utama** — Pilih tenant dengan tampilan card interaktif
- 📝 **Form pemesanan** — Isi nama, pilih menu, dan tentukan waktu pengambilan (07:00 — 19:00)
- 📋 **Lihat antrian** — Cek daftar pesanan yang sudah masuk per tenant
- ✅ **Validasi input** — Notifikasi error jika nama belum diisi
- 🎨 **Dark theme modern** — Desain gelap yang nyaman dengan aksen warna berbeda tiap tenant
- ✨ **Animasi halus** — Efek fade-in, hover pada tombol dan card

---

## 🛠️ Tech Stack

| Teknologi | Keterangan |
|-----------|------------|
| **Java** | JDK 17+ |
| **Swing** | GUI Framework |
| **MySQL** | Database |
| **Apache Ant** | Build tool |
| **VS Code / NetBeans** | IDE |

---

## 📦 Prasyarat

Sebelum menjalankan proyek ini, pastikan sudah terinstall:

- **JDK 17+**
- **MySQL** (database `byos_network_javaers` dengan tabel `dmas`, `lalapanku`, `baksouratmending`)
- **Apache Ant**

---

## ⚙️ Instalasi Apache Ant (macOS)

```bash
brew install ant

Cek apakah Ant sudah terinstall:

```bash
ant -version