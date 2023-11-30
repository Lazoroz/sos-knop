-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 30, 2023 at 11:21 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sos-knop`
--

-- --------------------------------------------------------

--
-- Table structure for table `contactpersoon`
--

CREATE TABLE `contactpersoon` (
  `contactpersoon_id` int(8) NOT NULL,
  `voornaam` varchar(255) DEFAULT NULL,
  `tussenvoegsels` varchar(255) DEFAULT NULL,
  `achternaam` varchar(255) DEFAULT NULL,
  `telefoonnummer` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `contactpersoon`
--

INSERT INTO `contactpersoon` (`contactpersoon_id`, `voornaam`, `tussenvoegsels`, `achternaam`, `telefoonnummer`) VALUES
(2, 'Christian', 'B', 'Vaughan', 112386652),
(3, 'Rigel', 'U', 'Richard', 782785457),
(4, 'Jena', 'G', 'Daugherty', 487204583),
(5, 'Macey', 'P', 'Houston', 201783515),
(6, 'Neil', 'B', 'Sandoval', 925196176),
(7, 'Yardley', 'Y', 'Buck', 251357399),
(8, 'Curran', 'X', 'Kennedy', 622157938),
(9, 'Damian', 'R', 'Murphy', 255331311),
(10, 'Bryar', 'S', 'Delgado', 829857836);

-- --------------------------------------------------------

--
-- Table structure for table `klant`
--

CREATE TABLE `klant` (
  `klant_id` int(11) NOT NULL,
  `voornaam` varchar(50) NOT NULL,
  `tussenvoegsels` varchar(50) DEFAULT NULL,
  `achternaam` varchar(50) NOT NULL,
  `telefoonnummer` varchar(50) NOT NULL,
  `blockeduser` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `klant`
--

INSERT INTO `klant` (`klant_id`, `voornaam`, `tussenvoegsels`, `achternaam`, `telefoonnummer`, `blockeduser`) VALUES
(1, 'Zane', 'Z', 'Duncan', '486', 'No'),
(2, 'Cullen', 'D', 'Payne', '415', 'No'),
(3, 'Barclay', 'S', 'Mcknight', '1', 'No'),
(4, 'Hashim', 'D', 'Foreman', '909', 'No'),
(5, 'Murphy', 'C', 'Landry', '1', 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `pushedbtn`
--

CREATE TABLE `pushedbtn` (
  `id` int(8) NOT NULL,
  `locatie` varchar(225) DEFAULT NULL,
  `sos-knop` int(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pushedbtn`
--

INSERT INTO `pushedbtn` (`id`, `locatie`, `sos-knop`) VALUES
(1, '88.9267295232, 11.2880467968', 1),
(2, '0.0171252736, -27.8228430848', 2),
(3, '-74.4133917696, 152.2398022656', 1),
(4, '-3.8800212992, -158.239417344', 3),
(5, '-13.280231936, 160.995621376', 1);

-- --------------------------------------------------------

--
-- Table structure for table `registreer`
--

CREATE TABLE `registreer` (
  `klant_id` int(8) NOT NULL,
  `contactpersoon_id` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `registreer`
--

INSERT INTO `registreer` (`klant_id`, `contactpersoon_id`) VALUES
(1, 4);

-- --------------------------------------------------------

--
-- Table structure for table `sos-knop`
--

CREATE TABLE `sos-knop` (
  `sos-knop_id` int(8) NOT NULL,
  `numberrange` varchar(255) DEFAULT NULL,
  `klant_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sos-knop`
--

INSERT INTO `sos-knop` (`sos-knop_id`, `numberrange`, `klant_id`) VALUES
(1, '54P7333745', 2),
(2, '15M3753844', 2),
(3, '58Y5758554', 2),
(4, '75S1082587', 5),
(5, '06G0136272', 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(8) NOT NULL,
  `email` varchar(255) NOT NULL,
  `wachtwoord` varchar(255) NOT NULL,
  `klant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `email`, `wachtwoord`, `klant`) VALUES
(6, 'imperdiet.ullamcorper@google.couk', 'ac', 1),
(7, 'fusce@icloud.com', 'tellus', 3),
(8, 'non.massa.non@icloud.couk', 'nec', 5),
(9, 'cursus.integer@icloud.couk', 'ipsum.', 4),
(10, 'sit@outlook.net', 'velit', 3),
(12, 'cbcvbcvb', '827ccb0eea8a706c4c34a16891f84e7b', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contactpersoon`
--
ALTER TABLE `contactpersoon`
  ADD PRIMARY KEY (`contactpersoon_id`);

--
-- Indexes for table `klant`
--
ALTER TABLE `klant`
  ADD PRIMARY KEY (`klant_id`);

--
-- Indexes for table `pushedbtn`
--
ALTER TABLE `pushedbtn`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sos-knop` (`sos-knop`);

--
-- Indexes for table `registreer`
--
ALTER TABLE `registreer`
  ADD PRIMARY KEY (`klant_id`,`contactpersoon_id`),
  ADD KEY `contactpersoon_id` (`contactpersoon_id`);

--
-- Indexes for table `sos-knop`
--
ALTER TABLE `sos-knop`
  ADD PRIMARY KEY (`sos-knop_id`),
  ADD KEY `klant_id` (`klant_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `klant` (`klant`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contactpersoon`
--
ALTER TABLE `contactpersoon`
  MODIFY `contactpersoon_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `klant`
--
ALTER TABLE `klant`
  MODIFY `klant_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `pushedbtn`
--
ALTER TABLE `pushedbtn`
  MODIFY `id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `sos-knop`
--
ALTER TABLE `sos-knop`
  MODIFY `sos-knop_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pushedbtn`
--
ALTER TABLE `pushedbtn`
  ADD CONSTRAINT `pushedbtn_ibfk_1` FOREIGN KEY (`sos-knop`) REFERENCES `sosknop` (`sos-knop_id`),
  ADD CONSTRAINT `pushedbtn_ibfk_2` FOREIGN KEY (`sos-knop`) REFERENCES `sos-knop` (`sos-knop_id`);

--
-- Constraints for table `registreer`
--
ALTER TABLE `registreer`
  ADD CONSTRAINT `registreer_ibfk_1` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`),
  ADD CONSTRAINT `registreer_ibfk_2` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`),
  ADD CONSTRAINT `registreer_ibfk_3` FOREIGN KEY (`contactpersoon_id`) REFERENCES `contactpersoon` (`contactpersoon_id`);

--
-- Constraints for table `sos-knop`
--
ALTER TABLE `sos-knop`
  ADD CONSTRAINT `sos-knop_ibfk_1` FOREIGN KEY (`klant_id`) REFERENCES `klant` (`klant_id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`klant`) REFERENCES `klant` (`klant_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
