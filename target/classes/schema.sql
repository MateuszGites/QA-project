
CREATE TABLE IF NOT EXISTS `pokemon` (
	`id` LONG AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    `level` INT NOT NULL,
    PRIMARY KEY(`id`),
    CHECK(`name` <> ''),
    CHECK(`type` <> ''),
    CHECK(`level` >= 5),
    CHECK(`level` <= 150)
);