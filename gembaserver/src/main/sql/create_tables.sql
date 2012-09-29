SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `gemba` DEFAULT CHARACTER SET latin1 ;
SHOW WARNINGS;
USE `gemba` ;

-- -----------------------------------------------------
-- Table `gemba`.`Family`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`Family` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`Family` (
  `FamilyId` VARCHAR(34) NOT NULL ,
  `LoginToken` VARCHAR(64) NOT NULL ,
  PRIMARY KEY (`FamilyId`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE UNIQUE INDEX `FamilyId_UNIQUE` ON `gemba`.`Family` (`FamilyId` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`Person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`Person` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`Person` (
  `PersonId` VARCHAR(34) NOT NULL ,
  `FamilyFk` VARCHAR(34) NOT NULL ,
  `FamilyName` VARCHAR(64) NULL DEFAULT NULL ,
  `GivenName` VARCHAR(64) NULL DEFAULT NULL ,
  `DateOfBirth` DATE NULL DEFAULT NULL ,
  `Sex` CHAR(1) NULL DEFAULT NULL ,
  `SocialInsuranceNumber` VARCHAR(16) NULL DEFAULT NULL ,
  PRIMARY KEY (`PersonId`) ,
  CONSTRAINT `person_ibfk_1`
    FOREIGN KEY (`FamilyFk` )
    REFERENCES `gemba`.`Family` (`FamilyId` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

SHOW WARNINGS;
CREATE INDEX `FamilyFk` ON `gemba`.`Person` (`FamilyFk` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`AccessToken`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`AccessToken` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`AccessToken` (
  `AccessTokenId` VARCHAR(34) NOT NULL ,
  `PersonFk` VARCHAR(34) NOT NULL ,
  `DoctorNumber` VARCHAR(8) NULL ,
  `LoginToken` VARCHAR(45) NOT NULL ,
  `ValidFrom` DATE NULL ,
  `ValidUntil` DATE NULL ,
  PRIMARY KEY (`AccessTokenId`) ,
  CONSTRAINT `fk_AccessToken_Person1`
    FOREIGN KEY (`PersonFk` )
    REFERENCES `gemba`.`Person` (`PersonId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `Fk_AccessToken_Person1` ON `gemba`.`AccessToken` (`PersonFk` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`Diary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`Diary` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`Diary` (
  `DiaryId` VARCHAR(34) NOT NULL ,
  `PersonFk` VARCHAR(34) NOT NULL ,
  `EntryTime` DATETIME NOT NULL ,
  `HealthState` SMALLINT NULL ,
  `Comment` TEXT NULL ,
  PRIMARY KEY (`DiaryId`) ,
  CONSTRAINT `fk_Diary_Person1`
    FOREIGN KEY (`PersonFk` )
    REFERENCES `gemba`.`Person` (`PersonId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `Fk_Diary_Person1` ON `gemba`.`Diary` (`PersonFk` ASC) ;

SHOW WARNINGS;
CREATE UNIQUE INDEX `DiaryId_UNIQUE` ON `gemba`.`Diary` (`DiaryId` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`Measurement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`Measurement` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`Measurement` (
  `MeasurementId` VARCHAR(34) NOT NULL ,
  `PersonFk` VARCHAR(34) NOT NULL ,
  `EntryTime` DATETIME NOT NULL ,
  `MeasurementFrom` DATETIME NULL ,
  `MeasurementUntil` DATETIME NULL ,
  `Measurement` VARCHAR(24) NOT NULL ,
  `Value` DECIMAL(10,0)  NOT NULL ,
  `Unit` VARCHAR(12) NOT NULL ,
  `Declaration` VARCHAR(128) NULL ,
  PRIMARY KEY (`MeasurementId`) ,
  CONSTRAINT `fk_Measurement_Person1`
    FOREIGN KEY (`PersonFk` )
    REFERENCES `gemba`.`Person` (`PersonId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `Fk_Measurement_Person1` ON `gemba`.`Measurement` (`PersonFk` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`Target`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`Target` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`Target` (
  `TargetId` INT NOT NULL ,
  `PersonFk` VARCHAR(34) NOT NULL ,
  `TargetType` VARCHAR(24) NOT NULL ,
  `Comment` TEXT NOT NULL ,
  `TargetDate` DATE NOT NULL ,
  `Measurement` VARCHAR(24) NULL ,
  `TargetValue` DECIMAL(10,0)  NOT NULL ,
  `Unit` VARCHAR(12) NULL ,
  PRIMARY KEY (`TargetId`) ,
  CONSTRAINT `fk_Target_Person1`
    FOREIGN KEY (`PersonFk` )
    REFERENCES `gemba`.`Person` (`PersonId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `Fk_Target_Person1` ON `gemba`.`Target` (`PersonFk` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`DataSource`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`DataSource` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`DataSource` (
  `DataSourceId` VARCHAR(34) NOT NULL ,
  `PersonFk` VARCHAR(34) NOT NULL ,
  `ServiceProvider` VARCHAR(64) NULL ,
  `ServiceProviderUrl` VARCHAR(128) NULL ,
  `Measurement` VARCHAR(24) NULL ,
  `Frequency` INT NULL ,
  `AuthToken` VARCHAR(120) NULL ,
  `AuthTokenSec` VARCHAR(120) NULL ,
  PRIMARY KEY (`DataSourceId`) ,
  CONSTRAINT `fk_DataSource_Person1`
    FOREIGN KEY (`PersonFk` )
    REFERENCES `gemba`.`Person` (`PersonId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `Fk_DataSource_Person1` ON `gemba`.`DataSource` (`PersonFk` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`Prescription`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`Prescription` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`Prescription` (
  `PrescriptionId` VARCHAR(34) NOT NULL ,
  `PersonFk` VARCHAR(34) NOT NULL ,
  `MediCode` VARCHAR(24) NULL ,
  `MediName` VARCHAR(128) NULL ,
  `StartDate` DATETIME NULL ,
  `Repetitions` INT NULL ,
  `Quantity` DECIMAL(10,0)  NULL ,
  `Unit` VARCHAR(12) NULL ,
  `Frequency` DECIMAL(10,0)  NULL ,
  `IsuredBy` VARCHAR(8) NULL ,
  PRIMARY KEY (`PrescriptionId`) ,
  CONSTRAINT `fk_Prescription_Person1`
    FOREIGN KEY (`PersonFk` )
    REFERENCES `gemba`.`Person` (`PersonId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `PrescriptionId_UNIQUE` ON `gemba`.`Prescription` (`PrescriptionId` ASC) ;

SHOW WARNINGS;
CREATE INDEX `Fk_Prescription_Person1` ON `gemba`.`Prescription` (`PersonFk` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`Sickness`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`Sickness` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`Sickness` (
  `SicknessId` VARCHAR(34) NOT NULL ,
  `PersonFk` VARCHAR(34) NOT NULL ,
  `Inconvenience` VARCHAR(64) NULL ,
  `BodyPart` VARCHAR(45) NULL ,
  `LevelOfPain` SMALLINT NULL ,
  `TypeOfPain` VARCHAR(24) NULL ,
  `Temperature` DECIMAL(10,0)  NULL ,
  PRIMARY KEY (`SicknessId`) ,
  CONSTRAINT `fk_Sickness_Person1`
    FOREIGN KEY (`PersonFk` )
    REFERENCES `gemba`.`Person` (`PersonId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `SicknessId_UNIQUE` ON `gemba`.`Sickness` (`SicknessId` ASC) ;

SHOW WARNINGS;
CREATE INDEX `Fk_Sickness_Person1` ON `gemba`.`Sickness` (`PersonFk` ASC) ;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `gemba`.`Folder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gemba`.`Folder` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `gemba`.`Folder` (
  `idFolder` INT NOT NULL ,
  `PersonFk` VARCHAR(34) NOT NULL ,
  `ContentType` VARCHAR(64) NULL ,
  `MimeType` VARCHAR(24) NULL ,
  `Content` BLOB NULL ,
  PRIMARY KEY (`idFolder`) ,
  CONSTRAINT `fk_Folder_Person1`
    FOREIGN KEY (`PersonFk` )
    REFERENCES `gemba`.`Person` (`PersonId` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `Fk_Folder_Person1` ON `gemba`.`Folder` (`PersonFk` ASC) ;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
