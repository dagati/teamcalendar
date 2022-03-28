DROP TABLE IF EXISTS `announcement` CASCADE;
DROP TABLE IF EXISTS `invitation_code` CASCADE;
DROP TABLE IF EXISTS `member_list` CASCADE;
DROP TABLE IF EXISTS `participant` CASCADE;
DROP TABLE IF EXISTS `role_list` CASCADE;
DROP TABLE IF EXISTS `role` CASCADE;
DROP TABLE IF EXISTS `schedule` CASCADE;
DROP TABLE IF EXISTS `team` CASCADE;
DROP TABLE IF EXISTS `user` CASCADE;

CREATE TABLE `user`
(
    `id`        BIGINT      NOT NULL AUTO_INCREMENT,
    `email`     VARCHAR(64) NOT NULL UNIQUE,
    `name`      VARCHAR(10) NOT NULL,
    `password`  VARCHAR(64) NOT NULL,
    `authority` CHAR(5)     NOT NULL, -- ADMIN / USER

    PRIMARY KEY (`id`)
);

CREATE TABLE `team`
(
    `id`        BIGINT      NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(20) NOT NULL UNIQUE,
    `leader_id` BIGINT      NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_team_to_user` FOREIGN KEY (`leader_id`) REFERENCES `user` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);

CREATE TABLE `invitation_code`
(
    `id`      BIGINT      NOT NULL AUTO_INCREMENT,
    `token`   VARCHAR(16) NOT NULL UNIQUE,
    `team_id` BIGINT      NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_invitation_code_to_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);

CREATE TABLE `member_list`
(
    `id`      BIGINT NOT NULL AUTO_INCREMENT,
    `team_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_member_list_to_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    CONSTRAINT `fk_member_list_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);

CREATE TABLE `schedule`
(
    `id`             BIGINT        NOT NULL AUTO_INCREMENT,
    `name`           VARCHAR(40)   NOT NULL,
    `detail`         VARCHAR(2000) NOT NULL,
    `start_DATETIME` DATETIME      NOT NULL,
    `end_DATETIME`   DATETIME      NOT NULL,
    `color`          CHAR(7)       NOT NULL DEFAULT '#333333',
    `team_id`        BIGINT        NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_schedule_to_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);

CREATE TABLE `participant`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `schedule_id` BIGINT NOT NULL,
    `user_id`     BIGINT NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_participant_to_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    CONSTRAINT `fk_participant_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);

CREATE TABLE `role`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(20) NOT NULL,
    `team_id`     BIGINT      NOT NULL,
    `schedule_id` BIGINT      NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_role_to_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    CONSTRAINT `fk_role_to_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);

CREATE TABLE `role_list`
(
    `id`      BIGINT NOT NULL AUTO_INCREMENT,
    `role_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_role_list_to_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT,
    CONSTRAINT `fk_role_list_to_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);

CREATE TABLE `announcement`
(
    `id`               BIGINT        NOT NULL AUTO_INCREMENT,
    `title`            VARCHAR(100)  NOT NULL,
    `content`          VARCHAR(2000) NOT NULL,
    `created_DATETIME` DATETIME      NOT NULL,
    `writer_id`        BIGINT        NOT NULL,

    PRIMARY KEY (`id`),
    CONSTRAINT `fk_announcement_to_user` FOREIGN KEY (`writer_id`) REFERENCES `user` (`id`)
        ON DELETE RESTRICT
        ON UPDATE RESTRICT
);
