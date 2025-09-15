package com.spring.client.comment.domain;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.spring.client.article.domain.Article;


/*
 * JPA(Java Persistence API)는 자바로 DB에 명령을 내리게 하는 도구로, 데이터를 객체 지향적으로 다루는 기술이다.
 * -엔티티와 리파지터리의 개념
 * • 엔티티: DB 데이터를 담는 자바 객체로, 엔티티를 기반으로 테이블 생성
 * • 리파지터리: 엔티티를 관리하는 인터페이스로, 데이터 CRUD 등의 기능 제공
 */

@Setter
@Getter
//@ToString
@ToString(exclude = "article") // article 필드를 제외하고 toString 메서드를 자동으로 생성.
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "boot_comment")
@SequenceGenerator(name = "comment_generator",
        sequenceName = "boot_comment_seq",
        initialValue = 1,
        allocationSize = 1)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
    private Long id;

    @Column
    private String nickname;

    @Column
    private String body;

    @CreationTimestamp
    @ColumnDefault(value = "sysdate")
    private LocalDateTime cdate;

    /*
    @ManyToOne // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="no")   // 외래키 생성, Article 엔티티의 기본키(no)와 매핑
    private Article article; // 해당 댓글의 부모 게시글
    */

    // fetch = FetchType.LAZY → 댓글을 조회할 때 연관된 게시글은 실제 사용 시점에 조회됨 (지연 로딩)
    // 즉 Comment 엔티티를 조회할 때 연관된 Article 엔티티를 즉시 로딩하지 않고, 해당 Article 엔티티가 실제로 필요할 때 로딩
    // JoinColumn(name = "no") → 댓글 테이블에 생성될 외래키 컬럼 이름 (게시글의 기본키 'no'를 참조)
    // nullable = false → 반드시 게시글이 있어야만 댓글이 존재할 수 있음
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="no", nullable = false)
    @JsonBackReference       // 순환 참조 방지
    private Article article; // 이 댓글이 속한 부모 게시글
}