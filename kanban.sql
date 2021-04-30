-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : ven. 30 avr. 2021 à 23:09
-- Version du serveur :  8.0.23-0ubuntu0.20.04.1
-- Version de PHP : 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `kanban`
--

-- --------------------------------------------------------

--
-- Structure de la table `Fiche`
--

CREATE TABLE `Fiche` (
  `id` bigint NOT NULL,
  `dateButoire` datetime(6) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `temps` int NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  `tab_id` bigint DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Fiche`
--

INSERT INTO `Fiche` (`id`, `dateButoire`, `libelle`, `lieu`, `note`, `temps`, `url`, `section_id`, `tab_id`, `user_email`) VALUES
(1, '2021-02-15 00:00:00.000000', 'lot 1502', 'pleine de Beaud', '....', 30, 'http://localhost:8080/fiches/1', 2, 1, 'richard-ngoran.kouame@tolab.ci'),
(35, NULL, 'xxxxxx', 'huhd', ',ghucgs', 0, 'http://localhost:8080/fiches/0', 7, 2, 'estelle.boli@etudiant.univ-rennes1.fr'),
(36, NULL, '24bbb', 'jknin', 'nj', 0, 'http://localhost:8080/fiches/0', 4, 2, 'estelle.boli@etudiant.univ-rennes1.fr'),
(37, '0010-10-12 01:00:00.000000', 'jbhuh', 'bnjb', 'nkn', 59, 'http://localhost:8080/fiches/0', 4, 1, 'estelle.boli@etudiant.univ-rennes1.fr'),
(38, NULL, 'omio', '', 'njb', 0, 'http://localhost:8080/fiches/0', 1, 4, 'lena.mutoni@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `Fiche_Tag`
--

CREATE TABLE `Fiche_Tag` (
  `fiches_id` bigint NOT NULL,
  `tags_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Fiche_Tag`
--

INSERT INTO `Fiche_Tag` (`fiches_id`, `tags_id`) VALUES
(1, 1),
(37, 5),
(35, 3),
(35, 6),
(36, 3),
(36, 2),
(38, 2);

-- --------------------------------------------------------

--
-- Structure de la table `Section`
--

CREATE TABLE `Section` (
  `id` bigint NOT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `tab_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Section`
--

INSERT INTO `Section` (`id`, `libelle`, `tab_id`) VALUES
(1, 'En attente', NULL),
(2, 'En cours', NULL),
(3, 'Réalisé', NULL),
(4, 'Réalisé', NULL),
(5, 'Defaillants', NULL),
(7, 'En cours', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Tableau`
--

CREATE TABLE `Tableau` (
  `id` bigint NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Tableau`
--

INSERT INTO `Tableau` (`id`, `libelle`) VALUES
(1, 'Principal'),
(2, 'Secondaire'),
(3, 'Tertiaire'),
(4, 'Quatrième');

-- --------------------------------------------------------

--
-- Structure de la table `Tag`
--

CREATE TABLE `Tag` (
  `id` bigint NOT NULL,
  `libelle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `Tag`
--

INSERT INTO `Tag` (`id`, `libelle`) VALUES
(1, 'Urgent'),
(2, 'Moins urgent'),
(3, 'Très urgent'),
(5, 'Important'),
(6, 'Moins important'),
(7, 'Très important'),
(8, 'Délicat'),
(9, 'Très délicat'),
(12, 'XXXX'),
(13, 'GHFHJFDI'),
(20, 'bla bla bla'),
(21, 'popito');

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `email` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `User`
--

INSERT INTO `User` (`email`, `nom`, `prenom`) VALUES
('ange-clarisse.sibomana@etudiant.univ-rennes1.fr', 'Sibomana', 'Ange Clarisse'),
('bonaventure.gbehe@etudiant.univ-rennes1.fr', 'Gbehe', 'Bonaventure'),
('estelle.boli@etudiant.univ-rennes1.fr', 'Boli', 'Estelle'),
('idriss.traore@etudiant.univ-rennes1.fr', 'Traoré', 'Idriss'),
('lena.mutoni@gmail.com', 'Mutoni', 'Lena Leo'),
('mardochee.sehi@email.com', 'Mardochée', 'SEHI'),
('maurice.aka@etudiant.univ-rennes1.fr', 'Aka', 'Maurice'),
('moussa.bamba@etudiant.univ-rennes1.fr', 'Bamba', 'Moussa'),
('nella.gatoni@gmail.com', 'Gatoni', 'Nella'),
('rebecca.ehua@etudiant.univ-rennes1.fr', 'Ehua', 'Rebecca'),
('richard-ngoran.kouame@tolab.ci', 'Kouamé', 'N\'goran Richard'),
('samuel.job@tolab.ci', 'JOB', 'Samuel'),
('siesson.josp@tolab.ci', 'Siesson', 'Josparo Jos');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Fiche`
--
ALTER TABLE `Fiche`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhku82603voqbyxsomrlihpeuq` (`section_id`),
  ADD KEY `FKhpoerfhr0p1yel08bwvd2hio1` (`tab_id`),
  ADD KEY `FKjc8qfr0bdvm4f474kk9x4vf44` (`user_email`);

--
-- Index pour la table `Fiche_Tag`
--
ALTER TABLE `Fiche_Tag`
  ADD KEY `FK4cuhkc7msctu5jfw8hgyjemul` (`tags_id`),
  ADD KEY `FK5dheupkajhwpdv5ocw711ybr9` (`fiches_id`);

--
-- Index pour la table `Section`
--
ALTER TABLE `Section`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgkeqh0hp5leokfqiuw0voewr6` (`tab_id`);

--
-- Index pour la table `Tableau`
--
ALTER TABLE `Tableau`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Tag`
--
ALTER TABLE `Tag`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `Fiche`
--
ALTER TABLE `Fiche`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT pour la table `Section`
--
ALTER TABLE `Section`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `Tableau`
--
ALTER TABLE `Tableau`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `Tag`
--
ALTER TABLE `Tag`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Fiche`
--
ALTER TABLE `Fiche`
  ADD CONSTRAINT `FKhku82603voqbyxsomrlihpeuq` FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`),
  ADD CONSTRAINT `FKhpoerfhr0p1yel08bwvd2hio1` FOREIGN KEY (`tab_id`) REFERENCES `Tableau` (`id`),
  ADD CONSTRAINT `FKjc8qfr0bdvm4f474kk9x4vf44` FOREIGN KEY (`user_email`) REFERENCES `User` (`email`);

--
-- Contraintes pour la table `Fiche_Tag`
--
ALTER TABLE `Fiche_Tag`
  ADD CONSTRAINT `FK4cuhkc7msctu5jfw8hgyjemul` FOREIGN KEY (`tags_id`) REFERENCES `Tag` (`id`),
  ADD CONSTRAINT `FK5dheupkajhwpdv5ocw711ybr9` FOREIGN KEY (`fiches_id`) REFERENCES `Fiche` (`id`);

--
-- Contraintes pour la table `Section`
--
ALTER TABLE `Section`
  ADD CONSTRAINT `FKgkeqh0hp5leokfqiuw0voewr6` FOREIGN KEY (`tab_id`) REFERENCES `Tableau` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
