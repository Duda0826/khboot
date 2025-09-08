package com.spring.client.article.service;

import com.spring.client.article.domain.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Article saveArticle(Article article);          // 글 등록 / 수정
    Optional<Article> getArticleById(Long id);     // 단건 조회
    List<Article> getAllArticles();                // 전체 조회
    void deleteArticle(Long id);                   // 삭제
}
