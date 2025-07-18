CREATE TABLE post_tags (
    post_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (post_id, tag_id),
    CONSTRAINT fk_post FOREIGN KEY (post_id) REFERENCES posts(id),
    CONSTRAINT fk_tag FOREIGN KEY (tag_id) REFERENCES tags(id)
);