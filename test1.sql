-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2019 at 05:23 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test1`
--

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

CREATE TABLE `movie` (
  `ID` int(50) NOT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_croatian_ci NOT NULL,
  `Type` varchar(10) COLLATE utf8mb4_bin NOT NULL,
  `Price` double NOT NULL,
  `OnScreen` tinyint(1) NOT NULL,
  `Description` varchar(10000) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`ID`, `Name`, `Type`, `Price`, `OnScreen`, `Description`) VALUES
(2, 'Jumanji', '15', 18, 1, 'jumanjmanjumanji'),
(3, 'Ford v Ferrari', '10', 10, 1, 'car fighting'),
(4, 'How To Make Loves', '30+', 6.9, 1, 'ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah ah'),
(5, 'Frozen 2', '3', 4, 1, 'Freeze style										Freeze'),
(6, 'Knives Out', '6', 5.3, 1, 'I have a pen~ I have a apple');

-- --------------------------------------------------------

--
-- Table structure for table `seats`
--

CREATE TABLE `seats` (
  `Row` varchar(1) NOT NULL,
  `Column` int(2) NOT NULL,
  `Time` datetime NOT NULL,
  `MovieName` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `seats`
--

INSERT INTO `seats` (`Row`, `Column`, `Time`, `MovieName`) VALUES
('G', 10, '2019-12-11 17:20:00', 'Frozen 2'),
('G', 11, '2019-12-11 17:20:00', 'Frozen 2'),
('G', 12, '2019-12-11 17:20:00', 'Frozen 2'),
('F', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 17, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 18, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 4, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 3, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 2, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 1, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 1, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 2, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 3, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 4, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('I', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 4, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 1, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 2, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 3, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 3, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 4, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 2, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 1, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 17, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 18, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('H', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 17, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 18, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('G', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 17, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 4, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 1, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 2, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 3, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 4, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 1, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 2, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 3, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 4, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 1, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 17, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 18, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 18, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 17, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('L', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('L', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('E', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 19, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('B', 20, '2019-12-17 00:00:00', 'Frozen 2'),
('A', 18, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 18, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 13, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 9, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 8, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 6, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 4, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('J', 17, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 14, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 15, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('C', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 3, '2019-12-17 00:00:00', 'Frozen 2'),
('L', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('K', 16, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 7, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 5, '2019-12-17 00:00:00', 'Frozen 2'),
('L', 10, '2019-12-17 00:00:00', 'Frozen 2'),
('L', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('L', 12, '2019-12-17 00:00:00', 'Frozen 2'),
('D', 11, '2019-12-17 00:00:00', 'Frozen 2'),
('F', 11, '2019-12-11 17:20:00', 'Frozen1'),
('E', 8, '2019-12-16 17:45:00', 'Jumanji');

-- --------------------------------------------------------

--
-- Table structure for table `timetable`
--

CREATE TABLE `timetable` (
  `Date` datetime NOT NULL,
  `MovieName` varchar(50) COLLATE utf8mb4_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `timetable`
--

INSERT INTO `timetable` (`Date`, `MovieName`) VALUES
('2019-12-15 18:50:00', 'How To Make Loves'),
('2019-12-16 18:50:00', 'How To Make Loves'),
('2019-12-16 22:30:00', 'How To Make Loves'),
('2019-12-16 17:45:00', 'Jumanji'),
('2019-12-16 21:45:00', 'Jumanji'),
('2019-12-16 23:45:00', 'Jumanji'),
('2019-12-17 02:20:00', 'How To Make Loves'),
('2019-12-17 03:21:00', 'Jumanji'),
('2019-12-17 22:21:00', 'Jumanji');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `Username` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `Password` varchar(30) COLLATE utf8mb4_bin NOT NULL,
  `Availability` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`Username`, `Password`, `Availability`) VALUES
('Chon Yao Jun', 'chonyaojun', 0),
('Tan Zhi Zhong', 'hahahehe', 1),
('Tiang King Jeck', 'hahahehe', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `movie`
--
ALTER TABLE `movie`
  MODIFY `ID` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
