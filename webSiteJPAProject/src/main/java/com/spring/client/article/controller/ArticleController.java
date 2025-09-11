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

    @GetMapping("/articleList")
    public String articleList(Article article, Model model) {
        List<Article> articleList = articleService.articleList(article);
        model.addAttribute("articleList", articleList);

        return "client/article/articleList";
    }

    @GetMapping("insertForm")
    public String insertForm(Article article){
        return "client/article/insertForm";
    }

    @PostMapping("/articleInsert")
    public String articleInsert(Article article){
        articleService.articleInsert(article);
        return "redirect:/article/articleList";
    }

    @GetMapping("/{no}")
    public String articleDetail(@PathVariable Long no, Article article, Model model){
        article.setNo(no);
        Article detail = articleService.articleDetail(article);
        model.addAttribute("detail", detail);

        String newLine = System.getProperty("line.separator").toString();
        model.addAttribute("newLine", newLine);

        return "client/article/articleDetail";
    }

    @PostMapping("/updateForm")
    public String updateForm(Article article, Model model){
        Article updateData = articleService.getArticle(article.getNo());
        model.addAttribute("updateData", updateData);
        return "client/article/updateForm";
    }

    @PostMapping("/articleUpdate")
    public String articleUpdate(Article article){
        Article updateData = articleService.getArticle(article.getNo());

        articleService.articleUpdate(article);
        return "redirect:/article/" + article.getNo();
    }

    @PostMapping("articleDelete")
    public String articleDelete(Article article){
        Article deleteData = articleService.getArticle(article.getNo());
        articleService.articleDelete(article);
        return "redirect:/article/articleList";
    }
}
