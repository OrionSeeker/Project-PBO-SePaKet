-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 14 Des 2024 pada 09.36
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sepaket`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `jadwaltayang`
--

CREATE TABLE `jadwaltayang` (
  `id` int(11) NOT NULL,
  `idMovie` int(11) NOT NULL,
  `jamTanggal` datetime NOT NULL,
  `hari` enum('Senin','Selasa','Rabu','Kamis','Jumat','Sabtu','Minggu') NOT NULL,
  `studio` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `jadwaltayang`
--

INSERT INTO `jadwaltayang` (`id`, `idMovie`, `jamTanggal`, `hari`, `studio`) VALUES
(3, 1, '2024-12-13 16:00:00', 'Jumat', 'A'),
(4, 1, '2024-12-13 18:00:00', 'Jumat', 'B'),
(5, 1, '2024-12-10 14:00:00', 'Sabtu', 'A'),
(6, 3, '2024-12-13 14:00:00', 'Jumat', 'C'),
(8, 3, '2024-12-13 16:00:00', 'Jumat', 'B'),
(9, 4, '2024-12-13 14:00:00', 'Jumat', 'D'),
(19, 6, '2024-12-13 18:00:00', 'Jumat', 'C'),
(20, 6, '2024-12-13 20:00:00', 'Jumat', 'A'),
(21, 5, '2024-12-13 16:00:00', 'Jumat', 'D'),
(22, 5, '2024-12-13 18:00:00', 'Jumat', 'A'),
(23, 5, '2024-12-14 12:00:00', 'Sabtu', 'B'),
(24, 5, '2024-12-14 14:00:00', 'Sabtu', 'D'),
(25, 5, '2024-12-14 16:00:00', 'Sabtu', 'A'),
(26, 5, '2024-12-15 12:00:00', 'Minggu', 'B'),
(27, 5, '2024-12-15 14:00:00', 'Minggu', 'A'),
(28, 5, '2024-12-15 16:00:00', 'Minggu', 'B');

-- --------------------------------------------------------

--
-- Struktur dari tabel `movie`
--

CREATE TABLE `movie` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `direction` varchar(255) NOT NULL,
  `year` int(11) NOT NULL,
  `genre` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `image` varchar(255) NOT NULL,
  `urlMovie` varchar(255) NOT NULL,
  `status` enum('Sedang Tayang','Coming Soon','ended') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `movie`
--

INSERT INTO `movie` (`id`, `title`, `direction`, `year`, `genre`, `description`, `image`, `urlMovie`, `status`) VALUES
(1, 'Moana 2', '\r\nDavid G. Derrick Jr.', 2024, 'Adventure, Comedy, Family', 'Setelah menerima panggilan tak terduga dari nenek moyangnya, Moana harus melakukan perjalanan ke lautan jauh di Oseania dan memasuki perairan yang berbahaya dan telah lama hilang untuk sebuah petualangan yang belum pernah ia alami.', 'Asset/poster/moana2.jpg', 'https://www.youtube.com/watch?v=hDZ7y8RP5HE', 'Sedang Tayang'),
(2, 'A Minecraft Movie', '\r\nJared Hess', 2025, 'Action, Adventure, Fantasy', 'Empat orang yang tidak cocok tiba-tiba ditarik melalui portal misterius ke dalam negeri ajaib yang aneh dan penuh dengan imajinasi. Untuk kembali ke rumah, mereka harus menguasai dunia ini sambil memulai petualangan bersama seorang perajin ahli yang tak terduga.', 'Asset/poster/minecraft.jpg', 'https://www.youtube.com/watch?v=wJO_vIDZn-I', 'Coming Soon'),
(3, 'Bila Esok Ibu Tiada', '\r\nRudy Soedjarwo', 2024, 'Drama, Family', 'Sebuah keluarga dengan empat orang anak yang sangat bergantung pada ibunya. Sang ibu selalu memberikan yang terbaik dan mendedikasikan dirinya untuk merawat anak-anaknya.', 'Asset/poster/BilaEsokIbuTiada.jpg', 'https://www.youtube.com/watch?v=UQURtWvSt9o', 'Sedang Tayang'),
(4, 'Wicked: Part I', '\r\nJon M. Chu', 2024, 'Fantasy, Romance', 'Elphaba, seorang wanita muda yang disalahpahami karena kulit hijaunya, dan Glinda, seorang gadis yang populer, menjadi teman di Universitas Shiz di Negeri Oz. Setelah pertemuan dengan Wizard of Oz yang luar biasa, persahabatan mereka mencapai persimpangan jalan.', 'Asset/poster/wicked.jpg', 'https://www.youtube.com/watch?v=6COmYeLsz4c', 'Sedang Tayang'),
(5, 'Werewolves', '\r\nSteven C. Miller', 2024, 'Action, Horror, Thriller', 'Dua ilmuwan mencoba menghentikan mutasi yang mengubah manusia menjadi manusia serigala setelah disentuh oleh bulan super tahun sebelumnya.', 'Asset/poster/werewolves.jpg', 'https://www.youtube.com/watch?v=AzdnfFLNP0o', 'Sedang Tayang'),
(6, 'Devils Stay', '\r\nMoon-Sub Hyun', 2024, 'Horor', 'Seorang spesialis jantung meragukan kematian putrinya setelah pengusiran setan, karena ia yakin jantungnya masih berdetak. Di tengah pemakamannya selama tiga hari, dia dan seorang pendeta berselisih tentang kebenaran, masing-masing berusaha membuktikan pendirian mereka dan berpotensi menyelamatkan nyawanya.', 'Asset/poster/devilsstay.jpg', 'https://www.youtube.com/watch?v=FKka9JzLc4k', 'Sedang Tayang'),
(7, 'Kimetsu no Yaiba - Infinity Castle', 'Haruo Sotozaki', 2025, 'Action, Animation', 'Korps Pembunuh Iblis terjun ke Kastil Tanpa Batas untuk mengalahkan Muzan. Namun, para Hashira dan Pembunuh Iblis yang tersisa yang selamat dari Seleksi Akhir Tanjiro diadu dengan anggota Dua Belas Kizuki yang tersisa terlebih dahulu.', 'Asset/poster/kimetsunoyaiba-infinitycastel.jpg', 'https://www.youtube.com/watch?v=wyiZWYMilgk', 'Coming Soon'),
(8, '2nd Miracle in Cell No. 7', '\r\nHerwin Novianto', 2024, 'Comedy, Drama, Family', 'Seorang pria dengan keterbelakangan mental yang dipenjara secara tidak adil atas pembunuhan seorang gadis. Dalam perjuangannya untuk bertemu kembali dengan putrinya yang masih kecil, dia meminta bantuan sesama narapidana untuk menyelundupkan putrinya ke dalam penjara.', 'Asset/poster/miracleno7.jpg', 'https://www.youtube.com/watch?v=EnQntzbV62E', 'Coming Soon');

-- --------------------------------------------------------

--
-- Struktur dari tabel `seat`
--

CREATE TABLE `seat` (
  `idJadwalTayang` int(11) NOT NULL,
  `noKursi` int(11) NOT NULL,
  `status` tinyint(1) DEFAULT 0,
  `tempatDuduk` varchar(255) NOT NULL,
  `atributBantu` enum('sudah','belum') NOT NULL DEFAULT 'belum'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `tiketbioskop`
--

CREATE TABLE `tiketbioskop` (
  `id` int(11) NOT NULL,
  `idAkun` int(11) NOT NULL,
  `idJadwalTayang` int(11) NOT NULL,
  `tempatDuduk` varchar(255) NOT NULL,
  `harga` int(11) NOT NULL,
  `status` enum('active','nonActive') NOT NULL DEFAULT 'active',
  `Studio` varchar(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(1, 'test', 'test1'),
(2, 'mike', 'mikea'),
(3, 'nab', 'nab'),
(4, 'zani', '123'),
(5, 'akbar', 'akbar1245');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `jadwaltayang`
--
ALTER TABLE `jadwaltayang`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idMovieJadwalTayang` (`idMovie`);

--
-- Indeks untuk tabel `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`idJadwalTayang`,`noKursi`),
  ADD UNIQUE KEY `unique_seat` (`idJadwalTayang`,`noKursi`);

--
-- Indeks untuk tabel `tiketbioskop`
--
ALTER TABLE `tiketbioskop`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idAkunTiketBioskop` (`idAkun`),
  ADD KEY `idJamTayangTiketBioskop` (`idJadwalTayang`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `jadwaltayang`
--
ALTER TABLE `jadwaltayang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT untuk tabel `movie`
--
ALTER TABLE `movie`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `tiketbioskop`
--
ALTER TABLE `tiketbioskop`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `jadwaltayang`
--
ALTER TABLE `jadwaltayang`
  ADD CONSTRAINT `idMovieJadwalTayang` FOREIGN KEY (`idMovie`) REFERENCES `movie` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ketidakleluasaan untuk tabel `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `fk_idJadwalTayang` FOREIGN KEY (`idJadwalTayang`) REFERENCES `jadwaltayang` (`id`);

--
-- Ketidakleluasaan untuk tabel `tiketbioskop`
--
ALTER TABLE `tiketbioskop`
  ADD CONSTRAINT `idAkunTiketBioskop` FOREIGN KEY (`idAkun`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idJamTayangTiketBioskop` FOREIGN KEY (`idJadwalTayang`) REFERENCES `jadwaltayang` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
