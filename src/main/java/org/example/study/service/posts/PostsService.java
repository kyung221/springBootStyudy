package org.example.study.service.posts;

import lombok.RequiredArgsConstructor;
import org.example.study.domain.posts.Posts;
import org.example.study.domain.posts.PostsRepository;
import org.example.study.web.dto.PostsListResponseDto;
import org.example.study.web.dto.PostsResponseDto;
import org.example.study.web.dto.PostsSaveRequestDto;
import org.example.study.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor //final이 선언된 모든 필드를 인자값으로 하는 생성자를 대신 생성해줌
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) //readOnly=true 옵션 : 트랜잭션 범위는 유지하되, 조회 가능만 남겨두어 조회 속도 개선.
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts); //entity를 파라미터로 삭제. deleteById메소드로도 가능
    }
}
