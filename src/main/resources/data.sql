-- user
INSERT INTO `user`(`id`, `name`, `email`, `password`, `authority`)
VALUES (null, 'alice', 'alice@gmail.com', 'alicepw', 'ADMIN');
INSERT INTO `user`(`id`, `name`, `email`, `password`, `authority`)
VALUES (null, 'bob', 'bob@gmail.com', 'bobpw', 'USER');
INSERT INTO `user`(`id`, `name`, `email`, `password`, `authority`)
VALUES (null, 'charlie', 'charlie@gmail.com', 'charliepw', 'USER');

-- team
INSERT INTO `team`(`id`, `name`, `code`)
VALUES (null, 'alpha', '1111111111111111');
INSERT INTO `team`(`id`, `name`, `code`)
VALUES (null, 'beta', '2222222222222222');

-- team_relation
INSERT INTO `team_relation`(`id`, `parent_id`, `child_id`, `depth`)
VALUES (null, 1, 1, 0);

-- member
INSERT INTO `member`(`id`, `team_id`, `user_id`)
VALUES (null, 1, 1);
INSERT INTO `member`(`id`, `team_id`, `user_id`)
VALUES (null, 1, 3);

-- role
INSERT INTO `role`(`id`, `name`, `team_id`)
VALUES (null, 'leader', 1);

-- position
INSERT INTO `position`(`id`, `role_id`, `member_id`)
VALUES (null, 1, 1);

-- schedule
INSERT INTO `schedule`(`id`, `name`, `detail`, `begin_date`, `begin_time`, `end_date`, `end_time`, `color`, `team_id`)
VALUES (null, '통합 회의', '통합 회의', '2022-06-15', '09:00:00', '2022-06-15', '10:00:00', '#907090', 1);

-- participant
INSERT INTO `participant`(`id`, `schedule_id`, `member_id`)
VALUES (null, 1, 1);

-- notice
INSERT INTO `notice`(`id`, `title`, `content`, `created_datetime`, `writer_id`)
VALUES (null, '안녕하세요. 팀캘린더입니다.', '팀캘린더는 팀 단위로 일정을 관리하는 서비스입니다.', '2022-06-01 09:00:00', 1);
