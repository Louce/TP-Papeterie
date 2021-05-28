package fr.eni.tp_papeterie.dal.jdbc;

import fr.eni.tp_papeterie.bo.Article;

public interface ArticleDAO {
    void update(Article article);

    void insert(Article article);

    Article selectById(int id);

    void delete(int id);
}