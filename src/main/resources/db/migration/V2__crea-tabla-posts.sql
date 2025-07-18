CREATE TABLE posts (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title VARCHAR(150) NOT NULL UNIQUE,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_post_usuario foreign key (user_id) references usuarios(id)
);