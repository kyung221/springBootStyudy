package org.example.study.web.dto;

import lombok.RequiredArgsConstructor;
import org.example.study.service.posts.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { //서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        model.addAttribute("posts",postsService.findAllDesc());//postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        return "index"; //return index.mustache
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
