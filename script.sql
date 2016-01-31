-- MySQL Script generated by MySQL Workbench
-- 01/31/16 11:26:04
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema db_todo
-- -----------------------------------------------------
-- Base de donnée reprenant les Todo des dossiers

-- -----------------------------------------------------
-- Schema db_todo
--
-- Base de donnée reprenant les Todo des dossiers
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_todo` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `db_todo` ;

-- -----------------------------------------------------
-- Table `db_todo`.`t_level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_todo`.`t_level` (
  `level` VARCHAR(32) NOT NULL COMMENT '',
  PRIMARY KEY (`level`)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_todo`.`t_profil`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_todo`.`t_profil` (
  `user` VARCHAR(32) NOT NULL COMMENT '',
  `nom` VARCHAR(45) NULL COMMENT '',
  `prenom` VARCHAR(45) NULL COMMENT '',
  `ip` VARCHAR(16) NULL COMMENT '',
  `last_time_ip` DATETIME NULL COMMENT '',
  `level` VARCHAR(32) NOT NULL COMMENT '',
  PRIMARY KEY (`user`)  COMMENT '',
  UNIQUE INDEX `user_UNIQUE` (`user` ASC)  COMMENT '',
  INDEX `fk_t_profil_t_level1_idx` (`level` ASC)  COMMENT '',
  CONSTRAINT `fk_t_profil_t_level1`
    FOREIGN KEY (`level`)
    REFERENCES `db_todo`.`t_level` (`level`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_todo`.`t_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_todo`.`t_group` (
  `groupname` VARCHAR(32) NOT NULL COMMENT '',
  PRIMARY KEY (`groupname`)  COMMENT '',
  UNIQUE INDEX `group_UNIQUE` (`groupname` ASC)  COMMENT '')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_todo`.`rel_profil_group`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_todo`.`rel_profil_group` (
  `t_profil_user` VARCHAR(32) NOT NULL COMMENT '',
  `t_group_groupname` VARCHAR(32) NOT NULL COMMENT '',
  INDEX `fk_rel_profil_group_t_group1_idx` (`t_group_groupname` ASC)  COMMENT '',
  CONSTRAINT `fk_rel_profil_group_t_profil`
    FOREIGN KEY (`t_profil_user`)
    REFERENCES `db_todo`.`t_profil` (`user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rel_profil_group_t_group1`
    FOREIGN KEY (`t_group_groupname`)
    REFERENCES `db_todo`.`t_group` (`groupname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_todo`.`t_dossier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_todo`.`t_dossier` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(45) NULL COMMENT '',
  `profil_owner` VARCHAR(32) NOT NULL COMMENT '',
  `group_owner` VARCHAR(32) NOT NULL COMMENT '',
  `pv` VARCHAR(45) NULL COMMENT '',
  `dossier_instruction` VARCHAR(45) NULL COMMENT '',
  `comment` LONGTEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_t_dossier_t_profil1_idx` (`profil_owner` ASC)  COMMENT '',
  INDEX `fk_t_dossier_t_group1_idx` (`group_owner` ASC)  COMMENT '',
  CONSTRAINT `fk_t_dossier_t_profil1`
    FOREIGN KEY (`profil_owner`)
    REFERENCES `db_todo`.`t_profil` (`user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_dossier_t_group1`
    FOREIGN KEY (`group_owner`)
    REFERENCES `db_todo`.`t_group` (`groupname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `db_todo`.`t_todo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_todo`.`t_todo` (
  `id` INT(11) NOT NULL COMMENT '',
  `t_dossier_id` INT(11) NOT NULL COMMENT '',
  `datestart` DATE NULL COMMENT '',
  `dateend` DATE NULL COMMENT '',
  `profil_owner` VARCHAR(32) NOT NULL COMMENT '',
  `isfinish` TINYINT(1) NULL DEFAULT 0 COMMENT '',
  `comment` LONGTEXT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `fk_t_todo_t_dossier1_idx` (`t_dossier_id` ASC)  COMMENT '',
  INDEX `fk_t_todo_t_profil1_idx` (`profil_owner` ASC)  COMMENT '',
  CONSTRAINT `fk_t_todo_t_dossier1`
    FOREIGN KEY (`t_dossier_id`)
    REFERENCES `db_todo`.`t_dossier` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_t_todo_t_profil1`
    FOREIGN KEY (`profil_owner`)
    REFERENCES `db_todo`.`t_profil` (`user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
