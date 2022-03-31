-- user
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'alice@gmail.com', 'alice', 'alice', 'ADMIN');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'bob@gmail.com', 'bob', 'bob', 'ADMIN');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'charlie@gmail.com', 'charlie', 'charlie', 'USER');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'david@gmail.com', 'david', 'david', 'USER');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'eve@gmail.com', 'eve', 'eve', 'USER');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'faythe@gmail.com', 'faythe', 'faythe', 'USER');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'grace@gmail.com', 'grace', 'grace', 'USER');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'heidi@gmail.com', 'heidi', 'heidi', 'USER');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'ivan@gmail.com', 'ivan', 'ivan', 'USER');
INSERT INTO `user`(`id`, `email`, `name`, `password`, `authority`)
VALUES (null, 'trudy@gmail.com', 'trudy', 'trudy', 'ADMIN');

-- team
INSERT INTO `team`(`id`, `name`, `leader_id`)
VALUES (null, 'alpha', 1);
INSERT INTO `team`(`id`, `name`, `leader_id`)
VALUES (null, 'bravo', 2);
INSERT INTO `team`(`id`, `name`, `leader_id`)
VALUES (null, 'charlie', 3);

-- invitation_code
INSERT INTO `invitation_code`(`id`, `token`, `team_id`)
VALUES (null, '1111111111111111', 1);
INSERT INTO `invitation_code`(`id`, `token`, `team_id`)
VALUES (null, '2222222222222222', 2);
INSERT INTO `invitation_code`(`id`, `token`, `team_id`)
VALUES (null, '3333333333333333', 3);

-- member_list
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 1, 1);
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 1, 4);
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 1, 5);
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 2, 2);
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 2, 6);
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 2, 7);
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 3, 3);
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 3, 8);
INSERT INTO `member_list`(`id`, `team_id`, `user_id`)
VALUES (null, 3, 9);

-- announcement
INSERT INTO `announcement`(`id`, `title`, `content`, `created_datetime`, `writer_id`)
VALUES (null, 'announcement 1', '공식 사이트를 보면, Maven은 소프트웨어 프로젝트 관리 및 생성 도구라고 정의하고 있다. 즉, Maven은 라이브러리를 관리하고 프로젝트를 빌드하는 도구이다. 그런데 왜 굳이 Maven을 사용해서 라이브러리를 관리해야 할까?', '2022-03-01 09:00:00', 1);
INSERT INTO `announcement`(`id`, `title`, `content`, `created_datetime`, `writer_id`)
VALUES (null, 'announcement 2', '사용자가 입력한 URL 주소 중 도메인 네임을 DNS 서버에 검색하고 DNS 서버에서 해당 도메인 네임에 해당하는 IP 주소를 찾아 사용자가 입력한 URL 정보와 함께 전달한다.', '2022-03-02 12:00:00', 2);
INSERT INTO `announcement`(`id`, `title`, `content`, `created_datetime`, `writer_id`)
VALUES (null, 'announcement 3', 'HTTP(Hyper Text Transfer Protocol)로 서버/클라이언트 모델을 따라 데이터를 주고받기 위한 프로토콜이다. 80번 포트를 사용하며 HTTP 서버가 80번 포트에서 요청을 기다리고 있으며 클라이언트는 80번 포트로 요청을 보내게 된다.', '2022-03-03 15:00:00', 10);

-- schedule
INSERT INTO `schedule`(`id`, `name`, `detail`, `start_datetime`, `end_datetime`, `color`, `team_id`)
VALUES (null, '통합 회의', '통합 회의', '2022-03-14 09:00:00', '2022-03-14 10:00:00', '#907090', 1);
