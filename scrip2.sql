CREATE TABLE IF NOT EXISTS `db_todo`.`t_todo` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `t_dossier_id` INT(11) NOT NULL COMMENT '',
  `datestart` DATE NULL COMMENT '',
  `dateend` DATE NULL COMMENT '',
  `profil_owner` VARCHAR(32) NOT NULL COMMENT '',
  `isfinish` TINYINT(1) NULL DEFAULT 0 COMMENT '',
  `comment` LONGTEXT NULL COMMENT '',
  `profil_cloture` VARCHAR(32) NULL COMMENT '',
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
ENGINE = InnoDB