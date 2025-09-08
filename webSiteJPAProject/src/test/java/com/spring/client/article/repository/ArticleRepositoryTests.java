package com.spring.client.article.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.client.article.domain.Article;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ArticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    /* 게시판 등록 - save(): 주어진 엔티티를 저장  */
    @Test
    public void articleInsertTest() {
        Article article = new Article();
        article.setName("늘한봄");
        article.setTitle("힘이 나는 명언");
        article.setContent("행복의 문이 하나 닫히면 다른 문이 열린다 그러나 우리는 종종 닫힌 문을 멍하니 바라보다가 우리를 향해 열린 문을 보지 못하게 된다.");
        article.setRegDate(LocalDateTime.now());

        log.info("### article 테이블에 첫번째 데이터 입력");
        articleRepository.save(article);

        Article article1 = new Article();
        article1.setName("김금희");
        article1.setTitle("노력 명언");
        article1.setContent("단 한번의 노력으로 자기의 바람을 성취할 수 없다. 또한 단 한번의 실패로 그 소망을 모두 포기할 수도 없는 것이다.");
        article1.setRegDate(LocalDateTime.now());

        log.info("### article 테이블에 두번째 데이터 입력");
        articleRepository.save(article1);
    }

    /* 게시판 리스트 - findAll(): T타입의 모든 인스턴스를 반환.*/
    @Test
    public void articleListTest() {
        List<Article> articleList = articleRepository.findAll();
        articleList.forEach(article -> log.info(article.toString()));
    }

}