
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
  opiekun_nr_tel int(9) NOT NULL
)

-- --------------------------------------------------------

--
-- Table structure for table `wlasciciel`
--

CREATE TABLE wlasciciel (
  wlasciciel_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  wlasciciel_imie varchar(255) NOT NULL,
  wlasciciel_nazwisko varchar(255) NOT NULL,
  wlasciciel_nr_tel int(9) NOT NULL
)

-- --------------------------------------------------------

--
-- Table structure for table `zwierze`
--

CREATE TABLE zwierze (
  zwierze_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  zwierze_imie varchar(255) NOT NULL,
  zwierze_gatunek varchar(255) NOT NULL,
  opiekun_id int(11) NOT NULL,
  wlasciciel_id int(11) NOT NULL,
  zwierze_data_przyjecia date NOT NULL,
  FOREIGN KEY (opiekun_id) REFERENCES opiekun(opiekun_id),
  FOREIGN KEY (wlasciciel_id) REFERENCES wlasciciel(wlasciciel_id)
)

-- --------------------------------------------------------
