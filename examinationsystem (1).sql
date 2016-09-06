-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 09, 2016 at 11:35 PM
-- Server version: 5.5.32
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `examinationsystem`
--
CREATE DATABASE IF NOT EXISTS `examinationsystem` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `examinationsystem`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `name` varchar(20) NOT NULL DEFAULT 'bisho silwal',
  `password` varchar(10) NOT NULL DEFAULT 'root',
  `year` int(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`name`, `password`, `year`) VALUES
('bisho silwal', 'root', 2016);

-- --------------------------------------------------------

--
-- Table structure for table `chemistryquestion`
--

CREATE TABLE IF NOT EXISTS `chemistryquestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` text NOT NULL,
  `rightAns` text NOT NULL,
  `wrongAns1` text NOT NULL,
  `wrongAns2` text NOT NULL,
  `wrongAns3` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `chemistryquestion`
--

INSERT INTO `chemistryquestion` (`id`, `question`, `rightAns`, `wrongAns1`, `wrongAns2`, `wrongAns3`) VALUES
(17, 'The most electronegative element among the following is', 'fluorine', 'sodium', 'bromine', 'oxygen'),
(16, 'The number of moles of solute present in 1 kg of a solvent is called its', 'molality', 'molarity', 'normality', 'formality'),
(15, 'The nucleus of an atom consists of', 'protons and neutrons', 'electrons and neutrons', 'protons and neutrons', 'All of the above'),
(18, 'The metal used to recover copper from a solution of copper sulphate is', 'Fe', 'Ag', 'Na', 'Hg'),
(19, 'The number of d-electrons in Fe2+ (Z = 26) is not equal to that of', 'p-electrons in CI(Z = 17)', 'd-electrons in Fe(Z = 26)', 's-electrons in Mg(Z = 12)', 'p-electrons in Ne(Z = 10)'),
(20, 'The metallurgical process in which a metal is obtained in a fused state is called', 'smelting', 'roasting', 'calcinations', 'froth floatation'),
(21, 'The molecules of which gas have highest speed', 'H2 at -73oC', 'CH4 at 300 K\r\n', 'N2 at 1,027oC', 'O2 at 0oC'),
(22, 'The law which states that the amount of gas dissolved in a liquid is proportional to its partial pressure is', 'Henry''s law', 'Dalton''s law', 'Gay Lussac''s law', 'Raoult''s law'),
(23, 'The main buffer system of the human blood is', 'H2CO3 - HCO3\r\n', 'H2CO3 - CO32-', 'CH3COOH - CH3COO-', 'NH2CONH2 - NH2CONH+'),
(24, 'The most commonly used bleaching agent is', 'chlorine', 'alcohol', 'carbon dioxide', 'sodium chlorine');

-- --------------------------------------------------------

--
-- Table structure for table `englishquestion`
--

CREATE TABLE IF NOT EXISTS `englishquestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` text NOT NULL,
  `rightAns` text NOT NULL,
  `wrongAns1` text NOT NULL,
  `wrongAns2` text NOT NULL,
  `wrongAns3` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Dumping data for table `englishquestion`
--

INSERT INTO `englishquestion` (`id`, `question`, `rightAns`, `wrongAns1`, `wrongAns2`, `wrongAns3`) VALUES
(14, ' Choose the word which is most nearly the SAME in meaning as the word given,FORMULATE', 'Frame', ' Apply', 'Contemplate', 'Regularize'),
(13, 'Choose the word which is most nearly the SAME in meaning as the word given,ARDUOUS', 'Difficult', 'Different', ' Hazardous', 'Pleasurable'),
(15, 'In the following questions choose the word which is the exact OPPOSITE of the given words,abet', ' Prevent', 'Aid', 'Pacify', 'Risk'),
(16, 'In the following questions choose the word which is the exact OPPOSITE of the given words,amused', 'saddened', 'jolted', 'frightened', 'astonished'),
(17, 'In the following the questions choose the word which best expresses the meaning of the given word,Ponder', 'Think', 'Increase', 'Anticipate', 'Evaluate'),
(18, 'In the following the questions choose the word which best expresses the meaning of the given word,Wary', 'Vigilant', 'Tired', 'Distorted', 'Sad'),
(19, 'In the following the questions choose the word which best expresses the meaning of the given word,Tenacity', 'perseverance', 'ingratitude', 'tendency', 'splendour'),
(20, 'West of Newport -----, one of the many mansions surrounded by acres of gardens', 'where the Aston stately home stands', 'the stately home stands of Aston', 'the stately home of Aston stands', 'stands the stately Aston home'),
(21, 'Putrefaction ----- by bacteria and not by a chemical process.', 'causing', 'to be caused', 'caused', 'is caused'),
(22, 'In the Sonora desert, the daytime temperatures ----- to 50 degrees Celsius.', 'risen', 'to rise', 'rising', 'rise');

-- --------------------------------------------------------

--
-- Table structure for table `mathquestion`
--

CREATE TABLE IF NOT EXISTS `mathquestion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` text NOT NULL,
  `rightAns` text NOT NULL,
  `wrongAns1` text NOT NULL,
  `wrongAns2` text NOT NULL,
  `wrongAns3` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `mathquestion`
--

INSERT INTO `mathquestion` (`id`, `question`, `rightAns`, `wrongAns1`, `wrongAns2`, `wrongAns3`) VALUES
(16, 'Choose the correct alternative that will continue the same pattern and fill in the blank spaces,4, 6, 12, 14, 28, 30, ? ', ' 60', ' 32', '64', '62'),
(15, 'Choose the correct alternative that will continue the same pattern and fill in the blank spaces,2, 7, 14, 23, ?, 47 ', '34', '31', '28', '38'),
(17, ' A man walks at 5kmph for 6hr and at 4km/h for 12hr. His average speed is \r\n', ' 4 1/3 km/h', ' 7 2/3 km/h', '9 ½ km/h', '8 km/h'),
(18, 'A train 140m long is running at 60kmph. In how much time will it pass a platform 260m long', '28 seconds', '15 seconds', '24 seconds', '30 seconds'),
(19, 'A train is moving at a speed of 132 km/hr. If the length of the train is 110 metres, how long will it take to cross a railway platform 165 metres long', ' 7½ sec', '10 sec', '12 ½ sec', '15 sec'),
(20, 'A train 150 m long is running at a speed of 68 kmph. How long does it take to pass a man who is running at 8 kmph in the same direction as the train', '9 sec', '5 sec', '12 sec', '15 sec'),
(21, ' If 5 women or 8 girls can do a work in 84 days. In how many days can 10 women and 5 girls can do the same work', '32 days', '48 days', '52 days', ' 38 days'),
(22, ' If 9 men working 6 hours a day can do a work in 88 days. Then 6 men working 8 hours a day can do it in how many days', '99 days', '89 days', '90 days', '85 days'),
(23, 'If 30% of a number is 12.6, find the number', ' 42', '45', '38', '40'),
(24, 'What number must be added to 6, 16 and 8 to get an average of 13', '22', '25', '20', '18');

-- --------------------------------------------------------

--
-- Table structure for table `physicsquestion`
--

CREATE TABLE IF NOT EXISTS `physicsquestion` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `question` text NOT NULL,
  `rightAns` text NOT NULL,
  `wrongAns1` text NOT NULL,
  `wrongAns2` text NOT NULL,
  `wrongAns3` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `physicsquestion`
--

INSERT INTO `physicsquestion` (`id`, `question`, `rightAns`, `wrongAns1`, `wrongAns2`, `wrongAns3`) VALUES
(14, 'Radiocarbon is produced in the atmosphere as a result of', 'collision between fast neutrons and nitrogen nuclei present in the atmosphere', 'action of ultraviolet light from the sun on atmospheric oxygen', 'action of solar radiations particularly cosmic rays on carbon dioxide present in the atmosphere', 'lightning discharge in atmosphere'),
(15, '	\r\nThe absorption of ink by blotting paper involves', 'capillary action phenomenon', 'diffusion of ink through the blotting', 'viscosity of ink', 'siphon action'),
(16, '	\r\nSiphon will fail to work if', 'the level of the liquid in the two vessels are at the same height', 'the densities of the liquid in the two vessels are equal', 'both its limbs are of unequal length', 'the temperature of the liquids in the two vessels are the same'),
(17, 'It is easier to roll a stone up a sloping road than to lift it vertical upwards because', 'work done in rolling a stone is less than in lifting it', 'work done in both is same but the rate of doing work is less in rolling', 'work done in lifting the stone is equal to rolling it', 'work done in lifting the stone is equal to rolling it'),
(18, 'Large transformers, when used for some time, become very hot and are cooled by circulating oil. The heating of the transformer is due to', 'both the heating effect of current and hysteresis loss', 'hysteresis loss alone', 'the heating effect of current alone', 'intense sunlight at noon'),
(19, 'Nuclear sizes are expressed in a unit named', 'Fermi', 'angstrom', 'newton', 'tesla'),
(20, 'Light year is a unit of', 'distance', 'time', 'light', 'intensity of light'),
(21, 'Mirage is due to', 'unequal heating of different parts of the atmosphere', 'magnetic disturbances in the atmosphere', 'depletion of ozone layer in the atmosphere', 'equal heating of different parts of the atmosphere'),
(22, 'Light from the Sun reaches us in nearly', '8 minutes', '2 minutes', '4 minutes', '16 minutes'),
(23, 'Stars appears to move from east to west because', 'the earth rotates from west to east', 'all stars move from east to west', 'the earth rotates from east to west', 'the background of the stars moves from west to east');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE IF NOT EXISTS `students` (
  `name` varchar(20) NOT NULL,
  `address` varchar(20) NOT NULL,
  `number` bigint(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `gender` char(10) NOT NULL,
  `id` int(15) unsigned NOT NULL DEFAULT '0',
  `password` varchar(20) NOT NULL,
  `result` varchar(5) DEFAULT NULL,
  `totalRightAns` int(11) DEFAULT NULL,
  UNIQUE KEY `email` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`name`, `address`, `number`, `email`, `gender`, `id`, `password`, `result`, `totalRightAns`) VALUES
('bisho', 'balkumari', 9847555071, 'dkt@gmail.com', 'male', 162, '123', 'fail', 0),
('a', 'a', 9847555071, 'aaa', 'male', 163, 'a', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `totalstudents`
--

CREATE TABLE IF NOT EXISTS `totalstudents` (
  `count` bigint(10) NOT NULL DEFAULT '0',
  `year` int(5) NOT NULL DEFAULT '2016'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `totalstudents`
--

INSERT INTO `totalstudents` (`count`, `year`) VALUES
(4, 2016);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
