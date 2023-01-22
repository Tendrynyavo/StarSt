package stock;

import com.ovoc01.dao.annotation.*;
import com.ovoc01.dao.java.ObjectDAO;
import element.Composition;
import element.Fabrication;
import element.Product;

import java.lang.Boolean;
import java.sql.Connection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

@Tables
public class Transaction extends ObjectDAO {
    @Column
    @PrimaryKey(prefix = "TRS",seqComp = "transSeq")
    String idTransaction;

    @Column
    @ForeignKey(classReference = Composition.class)
    String idProduit;

    @Column
    @com.ovoc01.dao.annotation.Boolean
    Boolean isEntering;

    @Column
    String dateTransaction;

    @Column
    @Nummer
    Double quantity;

    @Column
    @Nummer
    Double priceUnitaire;

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public Boolean getIsEntering() {
        return isEntering;
    }

    public void setIsEntering(Boolean entering) {
        isEntering = entering;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPriceUnitaire() {
        return priceUnitaire;
    }

    public void setPriceUnitaire(Double priceUnitaire) {
        this.priceUnitaire = priceUnitaire;
    }

    public Transaction(){

    }

    public Transaction(String idProduit, Boolean isEntering, String dateTransaction, Double quantity, Double priceUnitaire) {
        setIdProduit(idProduit);
        setIsEntering(isEntering);
        setDateTransaction(dateTransaction);
        setQuantity(quantity);
        setPriceUnitaire(priceUnitaire);
    }


    public void manageStock(Connection c) throws Exception{
        if(isEntering) entreStock(c);
        else sortieStock(c);
        insert(c);
    }
    public void entreStock(Connection c) throws Exception {
        Stock stock = new Stock();
        stock.setIdProduit(getIdProduit());
        Vector<Stock> stockVector = stock.select(c);
        Composition composition = new Composition();composition.setIdComposant(getIdProduit());
        Vector<Composition> compositions = composition.select(c);
        if(stockVector.isEmpty()){
            stock.setDateStock(getDateTransaction());
            stock.setQuantity(getQuantity());
            stock.setPriceUnitaire(getPriceUnitaire());
            compositions.get(0).setPrix(getPriceUnitaire());
            stock.insert(c);
            compositions.get(0).update(c);
            return;
        }
        double cump = CUMP(c);
        stockVector.get(0).setDateStock(getDateTransaction());
        stockVector.get(0).setQuantity(stockVector.get(0).getQuantity()+getQuantity());
        compositions.get(0).setPrix(cump);
        stockVector.get(0).setPriceUnitaire(cump);
        compositions.get(0).update(c);
        stockVector.get(0).update(c);
    }

    public void sortieStock(Connection c) throws Exception {
        Stock stock = new Stock();
        stock.setIdProduit(getIdProduit());
        Vector<Stock> stockVector = stock.select(c);
        if(stockVector.isEmpty()) throw new Exception("Empty stock");
        stockVector.get(0).setDateStock(getDateTransaction());

        stockVector.get(0).setQuantity(stockVector.get(0).getQuantity()-getQuantity());
        stockVector.get(0).update(c);
    }

    public double CUMP(Connection c) throws Exception {
        double cump = 0.0;
        Stock stock = new Stock();stock.setIdProduit(getIdProduit());
        Vector<Stock> stockVector = stock.select(c);
        if(stockVector.isEmpty()) return getPriceUnitaire();
        cump = getPriceUnitaire()*getQuantity()+stockVector.get(0).getPriceUnitaire()*stockVector.get(0).getQuantity();
        return cump/(getQuantity()+stockVector.get(0).getQuantity());
    }
}
