package br.com.infox.icones;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class Icone {
    Icon trashIcon = createImageIcon("/br/com/infox/icones/trash-icon.png", "Você deletou algo!");
    Icon databaseIcon = createImageIcon("/br/com/infox/icones/database-icon.png", "Você Criou algo!");
    Icon dataEditIcon = createImageIcon("/br/com/infox/icones/data-edit-icon.png", "Você alterou algo!");
    Icon printIcon = createImageIcon("/br/com/infox/icones/print.png", "Você Imprimiu algo!");
    Icon cliRptIcon = createImageIcon("/br/com/infox/icones/cliente-report-icon.png", "Relatorio de Cliente (Client Report)!");
    Icon serRptIcon = createImageIcon("/br/com/infox/icones/servico-report-icon.png", "Relatorio de Serviço (Service Report)!");

    //ImageIcon printIcon = new ImageIcon("/br/com/infox/icones/print.png");
    
    public Icon printIcon(){
        return printIcon;
    }
    
    public Icon cliRptIcon(){
        return cliRptIcon;
    }
    
    public Icon serRptIcon(){
        return serRptIcon;
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Icone.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }//ImageIcon

    public static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = Icone.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("ImageIcon : Couldn't find file: " + path);
            return null;
        }
    }//ImageIcon


}//class
