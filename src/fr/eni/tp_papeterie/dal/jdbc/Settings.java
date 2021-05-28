package fr.eni.tp_papeterie.dal.jdbc;

import java.util.Properties;

public class Settings {
    private static Properties propriete;

    static {
        try {
            propriete = new Properties();
            propriete.load(Settings.class.getResourceAsStream("settings.properties"));
            //propriete.loadFromXML(Settings.class.getRessourceAsStream("settings.xml"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static String getPropriete(String cle) {
        String parametre = propriete.getProperty(cle, null);
        return parametre;
    }
}
