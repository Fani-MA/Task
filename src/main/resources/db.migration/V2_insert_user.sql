INSERT INTO users(user_name, password, email, phone_number)
    VALUES ('misha', '2112', 'email@mail.com', '+77 88 55 467');

INSERT INTO roles(role)
    VALUES ('ROLE_USER'),
           ('ROLE_ADMIN');

INSERT INTO user_roles
    VALUES (1, 1),
           (1, 2);

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

