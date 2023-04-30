-- -----------------------------------------------------
-- Table `mydb`.`File`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`File` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `user` VARCHAR(45) NOT NULL,
                                             `type` VARCHAR(45) NOT NULL,
                                             `data` LONGBLOB NOT NULL,
                                             `saved` DATETIME NOT NULL,
                                             PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Message`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`Message` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `user` VARCHAR(45) NOT NULL,
                                                `title` VARCHAR(255) NOT NULL,
                                                `question` VARCHAR(255) NOT NULL,
                                                PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `test`.`User` (
                                             `id` INT NOT NULL AUTO_INCREMENT,
                                             `username` VARCHAR(255) NOT NULL,
                                             `nr_matricula` VARCHAR(45) NOT NULL,
                                             `curso` VARCHAR(255) NOT NULL,
                                             `nivel` VARCHAR(255) NOT NULL,
                                             `mc` DOUBLE NOT NULL,
                                             `ira` DOUBLE NOT NULL,
                                             PRIMARY KEY (`id`))
    ENGINE = InnoDB;