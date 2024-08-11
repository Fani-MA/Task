CREATE TABLE IF NOT EXISTS users
(
    id            BIGSERIAL PRIMARY KEY,
    user_name     VARCHAR(30) UNIQUE    NOT NULL ,
    password      VARCHAR(50)           NOT NULL ,
    email         VARCHAR(50) UNIQUE    NOT NULL ,
    phone_number  VARCHAR(20)
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

CREATE TABLE IF NOT EXISTS task_status
(
    id BIGSERIAL PRIMARY KEY ,
    status VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS project_task_states
(
    project_id  BIGINT REFERENCES projects(id) ,
    task_status BIGINT REFERENCES task_status(id),
    PRIMARY KEY (project_id, task_status)
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
    status_id     BIGINT REFERENCES task_status(id) NOT NULL ,
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
