INSERT INTO users(user_name, password, email, phone_number)
    VALUES  ('misha', '$2a$10$4dWfhiBZRtQ/BrTn4mrpG.sOx0rW14qSdIx194deUqP5s0Y.MfwjO', 'email@mail.com', '+77 88 55 467'),
            ('pestonia', '$2a$10$4dWfhiBZRtQ/BrTn4mrpG.sOx0rW14qSdIx194deUqP5s0Y.MfwjO', 'Pestonia@mail.com', '+77 88 63 854'),
            ('kvasar', '$2a$10$4dWfhiBZRtQ/BrTn4mrpG.sOx0rW14qSdIx194deUqP5s0Y.MfwjO', 'kvasar@mail.com', '+77 83 77 467'),
            ('skywing', '$2a$10$4dWfhiBZRtQ/BrTn4mrpG.sOx0rW14qSdIx194deUqP5s0Y.MfwjO', 'skywing@mail.com', '+77 85 64 497');

-- $2a$10$4dWfhiBZRtQ/BrTn4mrpG.sOx0rW14qSdIx194deUqP5s0Y.MfwjO = 2112
INSERT INTO roles(role)
    VALUES ('ROLE_USER'),
           ('ROLE_ADMIN');

INSERT INTO user_roles
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (3, 1),
       (4, 1);

INSERT INTO priority (level)
    VALUES  ('Low'),
            ('Medium'),
            ('High'),
            ('Highest');


INSERT INTO task_stage (stage)
    VALUES ('Created'),
           ('In work'),
           ('On review'),
           ('Completed');

INSERT INTO projects(title, description, state, created_at, author_id)
VALUES  ('My sample project','Study alg','ACTIVE','2024-08-15',1),
        ('Second project','Work tasks','ACTIVE','2024-08-17',1),
        ('Study project','OSI','ACTIVE','2024-08-12',2),
        ('Everyday','Daily tasks','ACTIVE','2024-08-12',1);
