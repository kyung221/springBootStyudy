package org.example.study.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스임을 나타냄
public class Posts {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성 규칙 > IDENTITY 옵션: auto_increment
    private Long id;

    @Column(length = 500, nullable = false) //테이블 컬럼. 굳이 선언 안해도 되지만, 필요한 옵션 있는 경우 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
