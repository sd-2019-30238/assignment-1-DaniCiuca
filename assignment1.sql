-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Gazdă: 127.0.0.1
-- Timp de generare: mart. 28, 2019 la 09:03 PM
-- Versiune server: 10.1.38-MariaDB
-- Versiune PHP: 7.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `assignment1`
--

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(45) NOT NULL,
  `author` varchar(45) NOT NULL,
  `genre` varchar(45) NOT NULL,
  `release_date` date DEFAULT NULL,
  `available` int(11) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `books`
--

INSERT INTO `books` (`id`, `title`, `author`, `genre`, `release_date`, `available`) VALUES
(1, 'Orase de hartie', 'John Green', 'Romance', '2009-04-23', 1),
(4, 'UE', 'Pop Marius', 'History', '2010-04-26', 1),
(5, 'Orase', 'John Green', 'Romance', '2009-04-23', 1),
(6, 'info', 'nfsjkf', 'Science', '2010-08-24', 1),
(7, 'Stele', 'Gigi Becali', 'Romance', '2002-08-24', 1),
(8, 'Star Wars', 'George Lucas', 'SF', '1998-01-30', 1),
(9, 'IT', 'UTCN', 'Science', '2018-09-19', 1),
(10, 'Nesquik', 'Andrei', 'Crime', '2008-11-10', 1);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `borrow_list`
--

CREATE TABLE `borrow_list` (
  `ID` int(11) NOT NULL,
  `BookID` int(11) NOT NULL,
  `Username` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `staff`
--

CREATE TABLE `staff` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `staff`
--

INSERT INTO `staff` (`username`, `password`) VALUES
('admin', 'admin'),
('dani', 'pass');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `users`
--

CREATE TABLE `users` (
  `username` varchar(25) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `paymentMethod` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Eliminarea datelor din tabel `users`
--

INSERT INTO `users` (`username`, `password`, `paymentMethod`) VALUES
('alex', 'pass123', 'Day'),
('andrei', 'pass', 'Week'),
('bele', 'bele', 'Day'),
('cosmin', 'cosmin', 'Year'),
('dani12', '1234', 'Week'),
('mihai', 'pass', 'Month'),
('paul', '65986', 'Year'),
('pop', 'adsfafsg', 'Month');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `users_waiting`
--

CREATE TABLE `users_waiting` (
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `paymentMethod` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `waiting_list`
--

CREATE TABLE `waiting_list` (
  `ID` int(11) NOT NULL,
  `BookID` int(11) NOT NULL,
  `Username` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexuri pentru tabele eliminate
--

--
-- Indexuri pentru tabele `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Indexuri pentru tabele `borrow_list`
--
ALTER TABLE `borrow_list`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `BookID` (`BookID`),
  ADD KEY `borrow_list_ibfk_2` (`Username`);

--
-- Indexuri pentru tabele `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`username`);

--
-- Indexuri pentru tabele `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Indexuri pentru tabele `users_waiting`
--
ALTER TABLE `users_waiting`
  ADD PRIMARY KEY (`username`);

--
-- Indexuri pentru tabele `waiting_list`
--
ALTER TABLE `waiting_list`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `BookID` (`BookID`),
  ADD KEY `waiting_list_ibfk_2` (`Username`);

--
-- AUTO_INCREMENT pentru tabele eliminate
--

--
-- AUTO_INCREMENT pentru tabele `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pentru tabele `borrow_list`
--
ALTER TABLE `borrow_list`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pentru tabele `waiting_list`
--
ALTER TABLE `waiting_list`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constrângeri pentru tabele eliminate
--

--
-- Constrângeri pentru tabele `borrow_list`
--
ALTER TABLE `borrow_list`
  ADD CONSTRAINT `borrow_list_ibfk_1` FOREIGN KEY (`BookID`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `borrow_list_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `users` (`username`);

--
-- Constrângeri pentru tabele `waiting_list`
--
ALTER TABLE `waiting_list`
  ADD CONSTRAINT `waiting_list_ibfk_1` FOREIGN KEY (`BookID`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `waiting_list_ibfk_2` FOREIGN KEY (`Username`) REFERENCES `users` (`username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
