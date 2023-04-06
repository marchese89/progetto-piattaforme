CREATE DATABASE `videogiochi_everytime` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `videogiochi_everytime`;

CREATE TABLE `amministratore` (
  `CF` char(16) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
CREATE TABLE `categoria` (
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `cliente` (
  `CF` char(16) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `cognome` varchar(45) DEFAULT NULL,
  `credito` double DEFAULT '0',
  PRIMARY KEY (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `videogioco` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `descrizione` varchar(150) DEFAULT NULL,
  `prezzo_vendita` double DEFAULT NULL,
  `qta_disp` int(11) DEFAULT NULL,
  `qta_venduta` int(11) DEFAULT NULL,
  `categoria` varchar(45) DEFAULT NULL,
  `percorso` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `categoria_idx` (`categoria`),
  CONSTRAINT `categoria` FOREIGN KEY (`categoria`) REFERENCES `categoria` (`nome`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;


CREATE TABLE `indirizzo` (
  `cod_cliente` char(16) NOT NULL,
  `via` varchar(45) NOT NULL,
  `num_civico` int(11) NOT NULL,
  `cap` int(11) DEFAULT NULL,
  `comune` varchar(45) NOT NULL,
  `provincia` char(2) DEFAULT NULL,
  PRIMARY KEY (`via`,`num_civico`,`comune`,`cod_cliente`),
  KEY `cod_cliente_idx` (`cod_cliente`),
  CONSTRAINT `cod_cliente` FOREIGN KEY (`cod_cliente`) REFERENCES `cliente` (`CF`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `ordine` (
  `codice` int(11) NOT NULL,
  `intestatario` char(16) NOT NULL,
  `data` date NOT NULL,
  `prezzo_totale` double DEFAULT NULL,
  `mod_pagamento` binary(1) DEFAULT '0' COMMENT 'se è un ordine relativo ad un cliente',
  PRIMARY KEY (`codice`),
  KEY `cliente_idx` (`intestatario`),
  CONSTRAINT `intestatario` FOREIGN KEY (`intestatario`) REFERENCES `cliente` (`CF`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `carrello` (
  `id_prodotto` int(11) NOT NULL,
  `id_utente` char(16) NOT NULL,
  `qta` int(11) DEFAULT '1',
  PRIMARY KEY (`id_prodotto`,`id_utente`),
  KEY `id_utente_idx` (`id_utente`),
  CONSTRAINT `id_prodotto` FOREIGN KEY (`id_prodotto`) REFERENCES `videogioco` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_utente` FOREIGN KEY (`id_utente`) REFERENCES `cliente` (`CF`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `resa` (
  `codice_resa` int(11) NOT NULL,
  `codice_ordine` int(11) NOT NULL,
  `motivazione` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codice_resa`,`codice_ordine`),
  KEY `ordine_fornitore_codice_idx` (`codice_ordine`),
  CONSTRAINT `codice_ordine` FOREIGN KEY (`codice_ordine`) REFERENCES `ordine` (`codice`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;















