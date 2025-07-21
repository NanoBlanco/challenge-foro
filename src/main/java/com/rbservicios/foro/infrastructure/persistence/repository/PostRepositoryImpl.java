package com.rbservicios.foro.infrastructure.persistence.repository;

import com.rbservicios.foro.domain.model.Post;
import com.rbservicios.foro.domain.model.Tag;
import com.rbservicios.foro.domain.model.User;
import com.rbservicios.foro.domain.repository.PostRepository;
import com.rbservicios.foro.domain.repository.TagRepository;
import com.rbservicios.foro.infrastructure.persistence.JpaPostRepository;
import com.rbservicios.foro.infrastructure.persistence.JpaTagRepository;
import com.rbservicios.foro.infrastructure.persistence.entity.PostEntity;
import com.rbservicios.foro.infrastructure.persistence.entity.TagEntity;
import com.rbservicios.foro.infrastructure.persistence.mapper.PostMapper;
import com.rbservicios.foro.infrastructure.presentation.PostUpdateReqDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository repository;
    private final JpaTagRepository tagRepository;

    public PostRepositoryImpl(JpaPostRepository repository, JpaTagRepository tagRepository) {
        this.repository = repository;
        this.tagRepository = tagRepository;
    }

    @Override
    public Post save(Post post) {
        PostEntity entity = PostMapper.fromDomainWithoutTags(post);

        if (post.getTags() != null && !post.getTags().isEmpty()) {
            List<Long> tagIds = post.getTags().stream()
                    .map(Tag::getId)
                    .toList();

            List<TagEntity> tagEntities = tagRepository.findAllById(tagIds);
            entity.setTags(tagEntities);
        }

        entity = repository.save(entity);
        return PostMapper.toDomain(entity);
    }

    @Override
    public Optional<Post> findById(Long id) {
        return repository.findById(id).map(PostMapper::toDomain);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll().stream().map(PostMapper::toDomain).toList();
    }

    @Override
    public List<Post> findByTag(Tag tag) {
        List<PostEntity> entities = repository.findByTags_Id(tag.getId());
        return entities.stream()
                .map(PostMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByUser(User user) {
        List<PostEntity> entities = repository.findByUser_Id(user.getId());
        return entities.stream()
                .map(PostMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Post post) {

    }

}
