-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2024 at 12:52 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cis`
--

-- --------------------------------------------------------

--
-- Table structure for table `equipments`
--

CREATE TABLE `equipments` (
  `e_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `quantity` int(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `image` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `equipments`
--

INSERT INTO `equipments` (`e_id`, `name`, `brand`, `quantity`, `status`, `image`) VALUES
(1, 'atay na', 'artay na', 100, 'AVAILABLE', 'src/ProductsImage/lowcost.png');

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `l_id` int(11) NOT NULL,
  `u_id` int(11) NOT NULL,
  `actions` varchar(50) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`l_id`, `u_id`, `actions`, `date`) VALUES
(1, 27, 'Created An Account', '2024-06-23');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `contact` varchar(50) NOT NULL,
  `password` varchar(250) NOT NULL,
  `type` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL,
  `Image` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `lastname`, `firstname`, `email`, `contact`, `password`, `type`, `status`, `Image`) VALUES
(23, '', '', '1', '11111111111', '9bsMjeFGxntEurv05lhMwA==', 'USER', 'active', 'src/ImageDB/7a.png'),
(24, 'LASTNaaaaaaaaaaaaaaAMEaaaaa', 'aaaaaaaaaaaaaaaaaaaaaaa', 'EMAIL@gmail.com', '11111111111', 'Jw2XD4XihzjT/OdN5HFI5Q==', 'ADMIN', 'active', 'src/ImageDB/1p (1).png'),
(25, 'yaaw', 'yawa', 'EMAILs@gmail.com', '11111111111', 'MZ9NJuPFNrXdhxuyxS4xeA==', 'ADMIN', 'ACTIVE', 'src/ImageDB/arrownasd.png'),
(26, 'asd', 'asdasdasdasd', 'asdasd@gmail.com', '11111111111', 'MZ9NJuPFNrXdhxuyxS4xeA==', 'ADMIN', 'ACTIVE', 'src/ImageDB/answer-alt (1).png'),
(27, '1', '1', 'asdasggggggggg@gmail.com', '11111111111', 'MZ9NJuPFNrXdhxuyxS4xeA==', 'ADMIN', 'ACTIVE', 'src/ImageDB/arrowUp.png'),
(28, 'asdasdasdasfdgdssgdsdg', 'dgsgdsgdsssssssssssssssss', 'sdgsdgdg@gmail.com', '11111111111', 'MZ9NJuPFNrXdhxuyxS4xeA==', 'ADMIN', 'Pending', 'src/ImageDB/user-last.png'),
(29, 'asdasdsadads', 'asdasdasdasdsadasd', 'tay@gmail.com', '11111111111', 'MZ9NJuPFNrXdhxuyxS4xeA==', 'ADMIN', 'Pending', 'src/ImageDB/user-last (1).png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `equipments`
--
ALTER TABLE `equipments`
  ADD PRIMARY KEY (`e_id`);

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`l_id`),
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `equipments`
--
ALTER TABLE `equipments`
  MODIFY `e_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `l_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
