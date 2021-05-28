package fr.eni.tp_papeterie.bo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eni Ecole Le panier stocke les articles sélectionnés par
 * l'utilisateur au cours de sa navigation. Le panier n'est pas
 * sauvegardé.
 */
public class Panier {

    // Attributs d'instance
    private List<Ligne> lignesPanier;
    private float montant;

    // Constructeurs
    public Panier() {
        this.lignesPanier = new ArrayList<Ligne>();
    }

    // Getters et Setters

    /**
     * @return the lignesPanier
     */
    public List<Ligne> getLignesPanier() {
        return lignesPanier;
    }

    /**
     * Ajouter une ligne au panier.
     *
     * @param article
     * @param qte
     */
    public void addLigne(Article article, int qte) {
        Ligne ligneAdding = new Ligne(article, qte);
        lignesPanier.add(ligneAdding);
        // lignesPanier.add(new Ligne(article, qte));
    }

    /**
     * Retourne la ligne sélectionnée du Panier ou null si pas trouvée
     *
     * @param index
     * @return
     */
    public final Ligne getLigne(int index) {
        return lignesPanier.get(index);
    }

    /**
     * Présenter le détail du panier
     */
    @Override
    public String toString() {
        StringBuilder bf = new StringBuilder();
        bf.append("Panier : \n\n");
        for (Ligne ligne : lignesPanier) {
            bf.append("ligne ");
            bf.append(lignesPanier.indexOf(ligne));
            bf.append(" :\t");
            bf.append(ligne.toString());
            bf.append("\n");
        }
        bf.append("\nValeur du panier : ").append(getMontant());
        bf.append("\n\n");
        return bf.toString();
    }

    /**
     * Supprimer la ligne du panier. La quantité en stock augmente de la
     * quantité associée à la ligne
     */
    public void removeLigne(int index) {
        lignesPanier.remove(index);
    }

    /**
     * Modifier la quantité placée dans le panier La quantité en stock augment
     * ou diminue en fonction de cette nouvelle qté
     *
     * @param index
     * @param newQte
     */
    public void updateLigne(int index, int newQte) {
        this.getLigne(index).setQte(newQte);
        // this.lignesPanier.get(index).setQte(newQte);
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

}
