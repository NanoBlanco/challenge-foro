CREATE TABLE comments (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    post_id BIGINT NOT NULL,
    create_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_usuario foreign key (user_id) references usuarios(id),
    CONSTRAINT fk_comment_post foreign key (post_id) references posts(id)
);