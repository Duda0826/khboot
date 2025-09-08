package com.spring.client.article.controller;

import com.spring.client.article.domain.Article;
import com.spring.client.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    // 글 목록 조회
    @GetMapping("/list")
    public String listArticles(Model model) {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "article/list";
    }

    // 글 상세 조회
    @GetMapping("/detail/{id}")
    public String getArticle(@PathVariable("id") Long id, Model model) {
        Article article = articleService.getArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다. id=" + id));
        model.addAttribute("article", article);
        return "article/detail";
    }

    // 글 작성 폼
    @GetMapping("/write")
    public String writeForm(Model model) {
        model.addAttribute("article", new Article());
        return "article/write";
    }

    // 글 작성 처리
    @PostMapping("/write")
    public String writeArticle(@ModelAttribute Article article) {
        articleService.saveArticle(article);
        return "redirect:/article/list";
    }

    // 글 수정 폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model) {
        Article article = articleService.getArticleById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다. id=" + id));
        model.addAttribute("article", article);
        return "article/edit";
    }

    // 글 수정 처리
    @PostMapping("/edit")
    public String editArticle(@ModelAttribute Article article) {
        articleService.saveArticle(article);
        return "redirect:/article/detail/" + article.getNo();
    }

    // 글 삭제
    @GetMapping("/delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticle(id);
        return "redirect:/article/list";
    }
}
