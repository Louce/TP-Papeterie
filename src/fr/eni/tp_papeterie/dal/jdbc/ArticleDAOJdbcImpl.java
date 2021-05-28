package fr.eni.tp_papeterie.dal.jdbc;

import fr.eni.tp_papeterie.bo.Article;
import fr.eni.tp_papeterie.bo.Ramette;
import fr.eni.tp_papeterie.bo.Stylo;

import java.sql.*;

public class ArticleDAOJdbcImpl implements ArticleDAO {
    private final String URL = Settings.getPropriete("url");
    private final String SELECT_BY_ID = "SELECT * FROM Articles WHERE idArticle=?";
    private final String SQL_DELETE = "DELETE FROM Articles WHERE idArticle=?";
    private final String SQL_UPDATE = "UPDATE Articles set " +
            "reference=?,marque=?,designation=?,prixUnitaire=?,qteStock=?,grammage=?,couleur=?" +
            " WHERE idArticle=?";
    private final String SQL_INSERT = "INSERT INTO Articles " +
            "(reference,marque, designation,prixUnitaire,qteStock,grammage,couleur,type) " +
            "VALUES(?,?,?,?,?,?,?,?);";

    @Override
    public void update(Article article) {
        try {
            Connection connection = JdbcTools.recupConnection();
            PreparedStatement etat = connection.prepareStatement(this.SQL_UPDATE);
            etat.setString(1, article.getReference());
            etat.setString(2, article.getMarque());
            etat.setString(3, article.getDesignation());
            etat.setFloat(4, article.getPrixUnitaire());
            etat.setInt(5, article.getQteStock());
            etat.setInt(8, article.getIdArticle());
            if (article instanceof Stylo) {
                etat.setString(7,  ((Stylo) article).getCouleur());
            }
            if (article instanceof Ramette) {
                etat.setInt(6, ((Ramette) article).getGrammage());
            }
            etat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insert(Article article) {
        try (
                Connection connection = JdbcTools.recupConnection();
                PreparedStatement etat = connection.prepareStatement(this.SQL_INSERT)
        ) {
            etat.setString(1, article.getReference());
            etat.setString(2, article.getMarque());
            etat.setString(3, article.getDesignation());
            etat.setFloat(4, article.getPrixUnitaire());
            etat.setInt(5, article.getQteStock());
            if (article instanceof Ramette) {
                etat.setInt(6, ((Ramette) article).getGrammage());
                etat.setString(8, "RAMETTE");
            }
            if (article instanceof Stylo) {
                etat.setString(7, ((Stylo) article).getCouleur());
                etat.setString(8, "STYLO");
            }
            etat.executeUpdate();
            ResultSet rs = etat.getGeneratedKeys(); // Je récupère l'id auto généré par la méthode isnert
            if (rs.next()) {
                int id = rs.getInt(1);
                article.setIdArticle(id); // Et je le met dans l'article en utilisant le Setter
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public Article selectById(int id) {
        Article article = null;
        try (
                Connection connection = JdbcTools.recupConnection();
                Statement etat = connection.createStatement()
        ) {
            String sql = "SELECT idArticle, reference, marque, designation, prixUnitaire, qteStock, grammage, couleur," +
                    "type FROM Articles WHERE idArticle = " + id + ";";
            System.out.println(sql);
            ResultSet rs = etat.executeQuery(sql);
            if (rs.next()) {
                if (rs.getString("type").trim().equalsIgnoreCase("RAMETTE")) {
                    article = new Ramette(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getInt("grammage")
                    );
                }
                if (rs.getString("type").trim().equalsIgnoreCase("STYLO")) {
                    article = new Stylo(
                            rs.getInt("idArticle"),
                            rs.getString("marque"),
                            rs.getString("reference"),
                            rs.getString("designation"),
                            rs.getFloat("prixUnitaire"),
                            rs.getInt("qteStock"),
                            rs.getString("couleur")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return article;
    }
    @Override
    public void delete(int id) {
        try (Connection connection = JdbcTools.recupConnection()) {
            PreparedStatement reqPreparee = connection.prepareStatement(this.SQL_DELETE);
            reqPreparee.setInt(1, id);
            reqPreparee.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

