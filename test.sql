CREATE TABLE `projectgestionetudiant`.`etudiant` (
  `idetudiant` INT UNSIGNED NOT NULL,
  `etudiantFirstName` VARCHAR(45) NOT NULL,
  `etudiantLastName` VARCHAR(45) NOT NULL,
  `etudiantEmail` VARCHAR(45) NOT NULL,
  `etudiantPhoneNumbre` VARCHAR(45) NOT NULL,
  `etudiantspecialite` VARCHAR(45) NULL,
  `etudiantModule` VARCHAR(45) NULL,
  PRIMARY KEY (`idetudiant`),
  UNIQUE INDEX `idetudiant_UNIQUE` (`idetudiant` ASC),
  INDEX `etudiantSpecialite_idx` (`etudiantspecialite` ASC),
  INDEX `etudiantModule_idx` (`etudiantModule` ASC),
  CONSTRAINT `etudiantSpecialite`
    FOREIGN KEY (`etudiantspecialite`)
    REFERENCES `projectgestionetudiant`.`specialite` (`specialiteName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `etudiantModule`
    FOREIGN KEY (`etudiantModule`)
    REFERENCES `projectgestionetudiant`.`module` (`moduleName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
