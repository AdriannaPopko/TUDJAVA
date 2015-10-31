
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

CREATE TABLE wlasciciel (
  wlasciciel_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  wlasciciel_imie varchar(255) NOT NULL,
  wlasciciel_nazwisko varchar(255) NOT NULL,
  wlasciciel_nr_tel int(9) NOT NULL,
  adres_id int(11) NOT NULL,
  FOREIGN KEY (adres_id) REFERENCES adres(adres_id)
)

-- --------------------------------------------------------

--
-- Table structure for table `zwierze`
--

CREATE TABLE zwierze (
  zwierze_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  zwierze_imie varchar(255) NOT NULL,
  zwierze_gatunek varchar(255) NOT NULL,
  ulubione_jedzenie_id int(11) NOT NULL,
  opiekun_id int(11) NOT NULL,
  wlasciciel_id int(11) NOT NULL,
  zwierze_data_przyjecia date NOT NULL,
  FOREIGN KEY (opiekun_id) REFERENCES customers(opiekun_id),
  FOREIGN KEY (wlasciciel_id) REFERENCES customers(wlasciciel_id),
  FOREIGN KEY (ulubione_jedzenie_id) REFERENCES customers(ulubione_jedzenie_id)
)

-- --------------------------------------------------------

--
-- Table structure for table `ulubione_jedzenie`
--

CREATE TABLE ulubione_jedzenie (
  ulubione_jedzenie_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ulubione_jedzenie_nazwa varchar(255) NOT NULL,
  ulubione_jedzenie_producent varchar(255) NOT NULL
)

-- --------------------------------------------------------


--
-- Table structure for table `adres`
--

CREATE TABLE adres (
  adres_id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  adres_miasto varchar(50) NOT NULL,
  adres_kod varchar(50) NOT NULL,
  adres_ulica varchar(225) NOT NULL,
  adres_nr_domu int(11) NOT NULL,
  adres_nr_mieszkania int(11) NOT NULL
) 

-- --------------------------------------------------------