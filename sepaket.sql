-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 14 Des 2024 pada 23.25
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
-- Struktur dari tabel `kesenian`
--

CREATE TABLE `kesenian` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status` enum('Sedang Tayang','Coming Soon','Ended') DEFAULT NULL,
  `lokasi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `kesenian`
--

INSERT INTO `kesenian` (`id`, `title`, `date`, `description`, `image`, `status`, `lokasi`) VALUES
(1, 'Gendang Beleq', '2024-12-15', 'Gendang Beleq adalah kesenian tradisional khas Lombok yang menggunakan gendang besar sebagai alat utama. Pertunjukan ini biasanya diiringi alat musik seperti gong dan suling, menciptakan suasana semangat dan meriah. Awalnya digunakan untuk upacara adat, kini Gendang Beleq menjadi simbol budaya Sasak.', 'Asset/poster/GendangBeleq.jpg', 'Sedang Tayang', 'Alun-Alun Kota Mataram'),
(2, 'Malam Puncak Kesenian Daerah', '2024-12-20', 'Malam Puncak Kesenian Daerah menampilkan ragam budaya tradisional dan modern. Acara ini menghadirkan tarian, musik, dan seni teater dalam satu panggung megah. Sebuah perayaan untuk melestarikan dan membanggakan kekayaan seni lokal.', 'Asset/poster/MalamPuncak.jpg', 'Sedang Tayang', 'Lapangan Mendirat'),
(3, 'Kesenian Daerah Banjarnegara', '2025-01-01', 'Banjarnegara dikenal dengan Lengger Calung, tarian khas yang diiringi musik bambu. Kesenian ini sering diselingi guyonan untuk menghibur penonton. Tradisi lain seperti Ebeg (kuda lumping) dan batik Gumelem juga memperkaya budaya daerah ini.', 'Asset/poster/Banjarnegara.jpg', 'Coming Soon', 'Alun-Alun Banjarnegara');

-- --------------------------------------------------------

--
-- Struktur dari tabel `konser`
--

CREATE TABLE `konser` (
  `id` int(11) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` text DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status` enum('Sedang Tayang','Coming Soon','ended') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `konser`
--

INSERT INTO `konser` (`id`, `title`, `date`, `description`, `image`, `status`) VALUES
(1, 'Konser Ari Lasso: Kehidupan Ketiga', '2024-10-12', 'Konser ini adalah perayaan perjalanan hidup dan karier Ari Lasso yang luar biasa, terutama setelah melewati perjuangan melawan penyakit serius. Kehidupan Ketiga menjadi simbol babak baru dalam kehidupannya, menampilkan lagu-lagu hits seperti Hampa, Cinta Terakhir, dan Mengejar Matahari. Konser ini tidak hanya membawa nostalgia, tetapi juga menggambarkan semangat hidup dan rasa syukur Ari kepada para penggemarnya.', 'Asset/poster/AriLasso.jpeg', 'Sedang Tayang'),
(2, 'IU: Hereh Concert', '2024-12-13', 'Konser Hereh dari IU menonjolkan sisi artistiknya yang penuh warna. IU dikenal karena menggabungkan genre pop, balada, dan jazz dengan vokalnya yang emosional. Konser ini memperlihatkan produksi panggung yang megah dan dekat dengan fans, menampilkan lagu-lagu hits seperti Good Day, Blueming, dan Eight. Pesan cinta dan harapan menjadi tema besar yang menghubungkan IU dengan UAENA (sebutan untuk fans IU).', 'Asset/poster/IU.jpg', 'Sedang Tayang'),
(3, 'Ed Sheeran Concert', '2024-12-15', 'Konser Ed Sheeran selalu menghadirkan performa yang memukau dengan suasana yang intim meski di stadion besar. Dikenal dengan gaya one-man band, Ed memainkan gitar dan loop station untuk membangun setiap lagu secara live. Lagu-lagu populer seperti Perfect, Shape of You, dan Thinking Out Loud biasanya menjadi bagian dari setlist-nya. Konsernya mengutamakan cerita personal yang menghubungkan penggemar dengan kisah di balik lagunya.', 'Asset/poster/EdSheeran.jpg', 'Sedang Tayang'),
(4, 'MLTR: Journey to Your Heart', '2025-01-01', 'MLTR (Michael Learns to Rock) menghadirkan konser penuh nostalgia dengan lagu-lagu legendaris mereka seperti Paint My Love, 25 Minutes, dan Sleeping Child. Dengan suara khas soft rock dan balada romantis, konser ini menawarkan suasana santai namun penuh kenangan bagi generasi yang tumbuh bersama musik mereka.', 'Asset/poster/MLTR.jpg', 'Coming Soon'),
(5, 'Yoasobi Concert', '2025-02-12', 'Konser Yoasobi membawa pengalaman penuh energi dari duo Jepang yang terkenal dengan gaya musik pop elektronik yang dinamis. Lagu-lagu seperti Yoru ni Kakeru, Gunjou, dan Idol menghadirkan pengalaman visual spektakuler dengan pencahayaan futuristik yang menyatu dengan alur musiknya. Selain itu, Yoasobi sering menghadirkan storytelling yang terinspirasi dari literatur Jepang, memberikan makna mendalam di setiap lagu mereka.', 'Asset/poster/Yoasobi.jpg', 'Coming Soon');

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

--
-- Dumping data untuk tabel `seat`
--

INSERT INTO `seat` (`idJadwalTayang`, `noKursi`, `status`, `tempatDuduk`, `atributBantu`) VALUES
(3, 1, 0, 'A1', 'sudah'),
(3, 7, 0, 'A7', 'sudah'),
(3, 8, 0, 'A8', 'sudah'),
(3, 9, 0, 'A9', 'sudah'),
(3, 10, 0, 'A10', 'sudah'),
(4, 1, 0, 'A1', 'sudah'),
(4, 2, 0, 'A2', 'sudah'),
(4, 3, 0, 'A3', 'sudah'),
(4, 18, 0, 'B1', 'sudah'),
(4, 20, 0, 'B3', 'sudah'),
(4, 35, 0, 'C1', 'sudah'),
(4, 36, 0, 'C2', 'sudah'),
(4, 37, 0, 'C3', 'sudah'),
(5, 8, 0, 'A8', 'sudah'),
(5, 9, 0, 'A9', 'sudah'),
(6, 1, 0, 'A1', 'sudah'),
(6, 2, 0, 'A2', 'sudah'),
(9, 92, 0, 'F7', 'sudah'),
(9, 93, 0, 'F8', 'sudah'),
(9, 94, 0, 'F9', 'sudah'),
(9, 95, 0, 'F10', 'sudah'),
(9, 96, 0, 'F11', 'sudah'),
(20, 126, 0, 'H7', 'sudah'),
(27, 92, 0, 'F7', 'sudah'),
(27, 93, 0, 'F8', 'sudah'),
(27, 94, 0, 'F9', 'sudah'),
(27, 95, 0, 'F10', 'sudah'),
(27, 96, 0, 'F11', 'sudah');

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

--
-- Dumping data untuk tabel `tiketbioskop`
--

INSERT INTO `tiketbioskop` (`id`, `idAkun`, `idJadwalTayang`, `tempatDuduk`, `harga`, `status`, `Studio`) VALUES
(138, 2, 3, 'A1', 30000, 'active', 'A'),
(139, 2, 4, 'A1,A2,A3,B1,B3,C1,C2,C3', 240000, 'active', 'B'),
(140, 4, 3, 'A7,A8,A9,A10', 120000, 'active', 'A'),
(141, 4, 9, 'F7,F8,F9,F10,F11', 150000, 'active', 'D'),
(142, 4, 27, 'F7,F8,F9,F10,F11', 150000, 'active', 'A'),
(143, 4, 20, 'H7', 30000, 'active', 'A'),
(144, 4, 6, 'A1,A2', 60000, 'active', 'C'),
(145, 4, 5, 'A8,A9', 60000, 'active', 'A');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tiketkonserseni`
--

CREATE TABLE `tiketkonserseni` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `NIK` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `jenis_tiket` varchar(255) DEFAULT NULL,
  `jumlah_tiket` int(11) DEFAULT NULL,
  `id_acara` int(11) DEFAULT NULL,
  `kategori` enum('Konser','Kesenian') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tiketkonserseni`
--

INSERT INTO `tiketkonserseni` (`id`, `id_user`, `nama`, `NIK`, `email`, `jenis_tiket`, `jumlah_tiket`, `id_acara`, `kategori`) VALUES
(1, 2, 'Testing aja', '12345', 'mike@gmail.com', 'Biasa', 2, 2, 'Kesenian'),
(2, 2, 'Nabila Noor', '12091209', 'nab@gmail.com', 'raja', 4, 1, 'Konser'),
(3, 4, 'Rafli Rizani', 'F1D022088', 'zani@gmail.com', 'raja', 3, 3, 'Konser'),
(4, 4, 'Rafli RIzani', '123', '123123', 'Istimewa', 2, 2, 'Konser'),
(5, 4, 'Rafli RIzani', '123', '123', 'Istimewa', 1, 2, 'Kesenian'),
(6, 4, 'Rafli Rizani', '1231232', 'zani@gmail.com', 'raja', 3, 3, 'Konser'),
(7, 4, 'Rafli Rizani', 'F1D022088', 'zani@gmail.com', 'Istimewa', 2, 1, 'Kesenian'),
(8, 4, 'zani', '12343', '1232', 'Biasa', 1, 1, 'Konser');

--
-- Trigger `tiketkonserseni`
--
DELIMITER $$
CREATE TRIGGER `before_insert_tiketkonserseni` BEFORE INSERT ON `tiketkonserseni` FOR EACH ROW BEGIN
    IF NEW.kategori = 'Konser' THEN
        IF NOT EXISTS (SELECT 1 FROM `konser` WHERE `id` = NEW.id_acara) THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Invalid id_acara: No matching record in table konser for kategori Konser';
        END IF;
    ELSEIF NEW.kategori = 'Kesenian' THEN
        IF NOT EXISTS (SELECT 1 FROM `kesenian` WHERE `id` = NEW.id_acara) THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Invalid id_acara: No matching record in table kesenian for kategori Kesenian';
        END IF;
    END IF;
END
$$
DELIMITER ;

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
-- Indeks untuk tabel `kesenian`
--
ALTER TABLE `kesenian`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `konser`
--
ALTER TABLE `konser`
  ADD PRIMARY KEY (`id`);

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
-- Indeks untuk tabel `tiketkonserseni`
--
ALTER TABLE `tiketkonserseni`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_tiketkonserseni_kesenian` (`id_acara`),
  ADD KEY `idUser` (`id_user`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=146;

--
-- AUTO_INCREMENT untuk tabel `tiketkonserseni`
--
ALTER TABLE `tiketkonserseni`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

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

--
-- Ketidakleluasaan untuk tabel `tiketkonserseni`
--
ALTER TABLE `tiketkonserseni`
  ADD CONSTRAINT `fk_tiketkonserseni_kesenian` FOREIGN KEY (`id_acara`) REFERENCES `kesenian` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_tiketkonserseni_konser` FOREIGN KEY (`id_acara`) REFERENCES `konser` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idAcaraKesenianTiketKonserSeni` FOREIGN KEY (`id_acara`) REFERENCES `kesenian` (`id`),
  ADD CONSTRAINT `idUser` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
