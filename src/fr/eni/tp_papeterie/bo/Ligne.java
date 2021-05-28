package fr.eni.tp_papeterie.bo;

public class Ligne {
    //Attributs
    protected int qte;
    protected Article article;

    //Constructeurs
    Ligne(Article article, int qte) {
        this.article = article;
        this.qte = qte;
    }

    //Getters et Setters
    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Article getArticle() {
        return this.article;
    }

    private void setArticle(Article article) {
        this.article = article;
    }

    // Retourne le prix unitaire d'un article
    public float getPrix() {
        return this.article.getPrixUnitaire();
    }

    //Méthodes
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("Ligne [");
        buf.append(" qte=");
        buf.append(getQte());
        buf.append(", prix=");
        buf.append(getPrix());
        buf.append(", ");
        if (article != null) {
            buf.append("article=");
            buf.append(getArticle().toString());
        }
        buf.append("]");
        return buf.toString();
    }
}

