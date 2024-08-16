CREATE TABLE IF NOT EXISTS users
(
    id            BIGSERIAL PRIMARY KEY,
    user_name     VARCHAR(30) UNIQUE    NOT NULL ,
    password      VARCHAR(65)           NOT NULL ,
    email         VARCHAR(50) UNIQUE    NOT NULL ,
    phone_number  VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS roles
(
    id BIGSERIAL PRIMARY KEY ,
    role VARCHAR(20) NOT NULL
);


CREATE TABLE IF NOT EXISTS user_roles
(
    user_id BIGINT REFERENCES users(id),
    role_id BIGINT REFERENCES roles(id),
    PRIMARY KEY (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS projects
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(50)                     NOT NULL ,
    description VARCHAR(255)                    NOT NULL ,
    state       VARCHAR(20)                     NOT NULL ,
    created_at  TIMESTAMP WITH TIME ZONE        NOT NULL ,
    author_id   BIGINT REFERENCES users(id)     NOT NULL
);

CREATE TABLE IF NOT EXISTS task_stage
(
    id BIGSERIAL PRIMARY KEY ,
    stage VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS project_task_stage
(
    project_id  BIGINT REFERENCES projects(id) ,
    task_stage BIGINT REFERENCES task_stage(id),
    PRIMARY KEY (project_id, task_stage)
);

CREATE TABLE IF NOT EXISTS priority
(
    id BIGSERIAL PRIMARY KEY ,
    level VARCHAR(10)   NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks (
    id            BIGSERIAL   PRIMARY KEY,
    title         VARCHAR(100)                      NOT NULL ,
    created_at    TIMESTAMP WITH TIME ZONE          NOT NULL ,
    start_time    TIMESTAMP WITH TIME ZONE,
    end_time      TIMESTAMP WITH TIME ZONE,
    priority_id   BIGINT REFERENCES priority(id)    NOT NULL ,
    status_id     BIGINT REFERENCES task_stage(id) NOT NULL ,
    author_id     BIGINT REFERENCES users(id)       NOT NULL ,
    executor_id   BIGINT REFERENCES users(id),
    project_id    BIGINT REFERENCES projects(id)    NOT NULL
);


CREATE TABLE IF NOT EXISTS comments
(
    id         BIGSERIAL  PRIMARY KEY,
    message    VARCHAR(250),
    task_id    BIGINT REFERENCES tasks(id) NOT NULL
);
