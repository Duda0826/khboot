package com.spring.client.article.domain;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.spring.client.comment.domain.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


@Setter
@Getter
//@ToString
@ToString(exclude = "comments") //양방향 참조에서 상호 호출을 방지하기 위해 comments 필드를 제외.
@Entity
@Table(name = "boot_article")
@SequenceGenerator(name = "article_generator",
        sequenceName = "boot_article_seq",
        initialValue = 1,
        allocationSize = 1)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_generator")
    private Long no;

    /* 객체 필드를 테이블 컬럼에 매핑 */
    @Column(length = 15, nullable = false)
    private String name;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    @ColumnDefault(value = "sysdate")
    private LocalDateTime regDate;

    @ColumnDefault(value = "0")
    private Integer hit = 0;

    // cascade = CascadeType.ALL → 게시글 저장/삭제 시 댓글도 함께 저장/삭제된다.
    // mappedBy = "article" → Comment 엔티티의 'article' 필드가 연관관계의 주인임을 명시
    // fetch 속성은 게시판 정보를 조회할 때 연관관계에 있는 게시판 정보도 같이 조회할 것인지를 결정할 때 사용.
    // fetch = FetchType.EAGER → 게시글을 조회할 때 연관된 댓글도 즉시 함께 조회된다 (즉시 로딩)
    // 기본값은 LAZY이지만, EAGER를 명시하여 즉시 로딩을 사용하도록 설정
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "article", fetch = FetchType.EAGER)
    @JsonManagedReference            //양방향 순환 참조를 방지하기 위해 사용
    private List<Comment> comments;  //댓글 목록을 저장하는 필드
}