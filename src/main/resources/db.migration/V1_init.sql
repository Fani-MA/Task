CREATE TABLE IF NOT EXISTS users
(
    id            BIGSERIAL     PRIMARY KEY,
    user_name     VARCHAR(30)  UNIQUE  not null ,
    password      VARCHAR(50)   not null ,
    email         VARCHAR(50) UNIQUE  not null ,
    phone_number  VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS projects
(
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(50) NOT NULL ,
    description VARCHAR(255) NOT NULL ,
    state       VARCHAR(20) NOT NULL ,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL,
    author_id   BIGINT REFERENCES users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS task_status
(
    id BIGSERIAL PRIMARY KEY ,
    status VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS tasks (
    id            BIGSERIAL   PRIMARY KEY,
    author_id     BIGINT REFERENCES users(id)  NOT NULL ,
    executor_id   BIGINT REFERENCES users(id),
    project_id    BIGINT REFERENCES projects(id) NOT NULL,
    priority      VARCHAR(20) NOT NULL ,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL ,
    start_time    TIMESTAMP WITH TIME ZONE,
    end_time      TIMESTAMP WITH TIME ZONE,
    status_id     BIGINT REFERENCES task_status(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS comments
(
    id         BIGSERIAL  PRIMARY KEY,
    message    VARCHAR(250),
    task_id    BIGINT REFERENCES tasks(id) not null
);