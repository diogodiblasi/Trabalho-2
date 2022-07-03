-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 03-Jul-2022 às 03:50
-- Versão do servidor: 10.4.24-MariaDB
-- versão do PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `diogodiblasi`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `farmacia`
--

CREATE TABLE `farmacia` (
  `produtoid` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `preco` double NOT NULL,
  `quantidade` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Extraindo dados da tabela `farmacia`
--

INSERT INTO `farmacia` (`produtoid`, `nome`, `preco`, `quantidade`) VALUES
(1, 'Tylenol', 7.9, 10),
(2, 'Doralgina', 8.8, 10),
(3, 'Vitamina C', 9.9, 12),
(5, 'Rivotril', 42.2, 30),
(10, 'Viagra', 4.2, 40),
(12, 'Teste Covid', 80, 188),
(15, 'Fralda', 29.9, 1),
(34, 'Complexo B', 32.3, 10),
(40, 'Advil', 8.29, 294),
(111, 'Bepantol', 79.9, 100);

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `farmacia`
--
ALTER TABLE `farmacia`
  ADD PRIMARY KEY (`produtoid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
