-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema managementdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema managementdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `managementdb` ;
USE `managementdb` ;

-- -----------------------------------------------------
-- Table `managementdb`.`company_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`company_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `iduser_type_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`company` (
  `id` VARCHAR(36) NOT NULL,
  `name` VARCHAR(45) NULL,
  `identification_number` VARCHAR(45) NULL,
  `identification_type` VARCHAR(10) NOT NULL,
  `phone` VARCHAR(25) NULL,
  `info1` VARCHAR(100) NULL,
  `info2` VARCHAR(100) NULL,
  `create_time` DATETIME NOT NULL,
  `id_comp_typ` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_company_company_type_idx` (`id_comp_typ` ASC),
  CONSTRAINT `fk_company_company_type`
    FOREIGN KEY (`id_comp_typ`)
    REFERENCES `managementdb`.`company_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`user` (
  `id` VARCHAR(36) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(500) NOT NULL,
  `number` INT NOT NULL,
  `create_time` DATETIME NOT NULL,
  `id_comp` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_user_company1_idx` (`id_comp` ASC),
  CONSTRAINT `fk_user_company1`
    FOREIGN KEY (`id_comp`)
    REFERENCES `managementdb`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` INT NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`state` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code_uf` INT NOT NULL,
  `name` VARCHAR(250) NOT NULL,
  `uf` VARCHAR(2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(250) NULL,
  `number` VARCHAR(45) NULL,
  `zip_code` VARCHAR(45) NULL,
  `district` VARCHAR(250) NULL,
  `info` VARCHAR(250) NULL,
  `id_city` INT NULL,
  `id_stat` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_city1_idx` (`id_city` ASC),
  INDEX `fk_address_state1_idx` (`id_stat` ASC),
  CONSTRAINT `fk_address_city1`
    FOREIGN KEY (`id_city`)
    REFERENCES `managementdb`.`city` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_address_state1`
    FOREIGN KEY (`id_stat`)
    REFERENCES `managementdb`.`state` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cpfcnpj` VARCHAR(18) NULL,
  `email` VARCHAR(100) NULL,
  `id_addr` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_person_address1_idx` (`id_addr` ASC),
  CONSTRAINT `fk_person_address1`
    FOREIGN KEY (`id_addr`)
    REFERENCES `managementdb`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`provider`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`provider` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `active` TINYINT NOT NULL,
  `fantasy_name` VARCHAR(250) NULL,
  `id_comp` VARCHAR(36) NOT NULL,
  `id_pers` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_provider_company1_idx` (`id_comp` ASC),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_provider_person1_idx` (`id_pers` ASC),
  CONSTRAINT `fk_provider_company1`
    FOREIGN KEY (`id_comp`)
    REFERENCES `managementdb`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_provider_person1`
    FOREIGN KEY (`id_pers`)
    REFERENCES `managementdb`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`product_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`product_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NOT NULL,
  `id_comp` VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_category_company1_idx` (`id_comp` ASC),
  CONSTRAINT `fk_category_company1`
    FOREIGN KEY (`id_comp`)
    REFERENCES `managementdb`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NOT NULL,
  `code` VARCHAR(20) NULL,
  `bar_code` VARCHAR(45) NULL,
  `price_sales` DOUBLE NOT NULL,
  `price_cost` DOUBLE NOT NULL,
  `note` VARCHAR(500) NULL,
  `id_comp` VARCHAR(36) NOT NULL,
  `id_cate` INT NULL,
  `id_prov` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_product_company1_idx` (`id_comp` ASC),
  INDEX `fk_product_provider1_idx` (`id_prov` ASC),
  INDEX `fk_product_category1_idx` (`id_cate` ASC),
  CONSTRAINT `fk_product_company1`
    FOREIGN KEY (`id_comp`)
    REFERENCES `managementdb`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_provider1`
    FOREIGN KEY (`id_prov`)
    REFERENCES `managementdb`.`provider` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_category1`
    FOREIGN KEY (`id_cate`)
    REFERENCES `managementdb`.`product_category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`product_stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`product_stock` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(250) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `max` DOUBLE NOT NULL,
  `min` DOUBLE NOT NULL,
  `id_prod` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_product_stock_product1_idx` (`id_prod` ASC),
  CONSTRAINT `fk_product_stock_product1`
    FOREIGN KEY (`id_prod`)
    REFERENCES `managementdb`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`stoke_moviment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`stoke_moviment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `note` VARCHAR(45) NOT NULL,
  `moviment_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_comp` VARCHAR(36) NOT NULL,
  `id_prod_stoc` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `fk_stoke_moviment_company1_idx` (`id_comp` ASC),
  INDEX `fk_stoke_moviment_product_stock1_idx` (`id_prod_stoc` ASC),
  CONSTRAINT `fk_stoke_moviment_company1`
    FOREIGN KEY (`id_comp`)
    REFERENCES `managementdb`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_stoke_moviment_product_stock1`
    FOREIGN KEY (`id_prod_stoc`)
    REFERENCES `managementdb`.`product_stock` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `managementdb`.`phone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `managementdb`.`phone` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `number` VARCHAR(40) NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `id_pers` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_phone_person1_idx` (`id_pers` ASC),
  CONSTRAINT `fk_phone_person1`
    FOREIGN KEY (`id_pers`)
    REFERENCES `managementdb`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
