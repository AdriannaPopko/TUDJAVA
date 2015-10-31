
--
-- Database: `hotel-dla-zwierzat`
--

-- --------------------------------------------------------

--
-- Table structure for table `opiekun`
--

CREATE TABLE opiekun (
  opiekun_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  opiekun_imie varchar(255) NOT NULL,
  opiekun_nazwisko varchar(255) NOT NULL,
  opiekun_nr_tel int(9) NOT NULL,
  adres_id int(11) NOT NULL,
  FOREIGN KEY (adres_id) REFERENCES adres(adres_id)
)

-- --------------------------------------------------------

--
-- Table structure for table `wlasciciel`
--

DROP TABLE IF EXISTS `wlasciciel`;
CREATE TABLE IF NOT EXISTS `wlasciciel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nazwisko` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nr_tel` int(9) NOT NULL,
  `adres_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `adres_id` (`adres_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `zwierze`
--

DROP TABLE IF EXISTS `zwierze`;
CREATE TABLE IF NOT EXISTS `zwierze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `gatunek` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ulubione_jedzenie_id` int(11) NOT NULL,
  `opiekun_id` int(11) NOT NULL,
  `wlasciciel_id` int(11) NOT NULL,
  `data_przyjecia` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `ulubione_jedzenie_id` (`ulubione_jedzenie_id`),
  KEY `opieun_id` (`opiekun_id`),
  KEY `wlasciciel_id` (`wlasciciel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ulubione_jedzenie`
--

DROP TABLE IF EXISTS `ulubione_jedzenie`;
CREATE TABLE IF NOT EXISTS `ulubione_jedzenie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `producent` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `data_waznosci` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------


--
-- Table structure for table `adres`
--

DROP TABLE IF EXISTS `adres`;
CREATE TABLE IF NOT EXISTS `adres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `miasto` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `kod` varchar(50) NOT NULL,
  `ulica` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `nr_domu` int(11) NOT NULL,
  `nr_mieszkania` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Constraints for dumped tables
--

--
-- Constraints for table `opiekun`
--
ALTER TABLE `opiekun`
  ADD CONSTRAINT `opiekun_ibfk_1` FOREIGN KEY (`adres_id`) REFERENCES `adres` (`id`);

--
-- Constraints for table `wlasciciel`
--
ALTER TABLE `wlasciciel`
  ADD CONSTRAINT `wlasciciel_ibfk_1` FOREIGN KEY (`adres_id`) REFERENCES `adres` (`id`);

--
-- Constraints for table `zwierze`
--
ALTER TABLE `zwierze`
  ADD CONSTRAINT `zwierze_ibfk_1` FOREIGN KEY (`ulubione_jedzenie_id`) REFERENCES `ulubione_jedzenie` (`id`);
  ADD CONSTRAINT `zwierze_ibfk_2` FOREIGN KEY (`opiekun_id`) REFERENCES `opiekun` (`id`),
  ADD CONSTRAINT `zwierze_ibfk_3` FOREIGN KEY (`wlasciciel_id`) REFERENCES `wlasciciel` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;