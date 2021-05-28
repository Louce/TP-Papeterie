package fr.eni.tp_papeterie.dal.jdbc;

import fr.eni.tp_papeterie.bo.Article;

public class DAOFactory {
    public static ArticleDAO getArticleDAO(){
        ArticleDAO articleDAO = new ArticleDAOJdbcImpl();
        return articleDAO;
    }
}
