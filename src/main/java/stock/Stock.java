package stock;

import com.ovoc01.dao.annotation.*;
import com.ovoc01.dao.java.ObjectDAO;
import element.Composition;
import element.Product;

import java.sql.Connection;
import java.util.Vector;

@Tables
public class Stock extends ObjectDAO {

    @Column
    @PrimaryKey(prefix = "STCK",seqComp = "stockSeq")
    String idStock;
    @Column
    String dateStock;

    @Column
    @Nummer
    Double quantity;

    @Column
    @Nummer
    Double priceUnitaire;
    @Column
    @ForeignKey(classReference = Composition.class)
    String idProduit;

    public String getIdStock() {
        return idStock;
    }

    public void setIdStock(String idStock) {
        this.idStock = idStock;
    }

    public String getDateStock() {
        return dateStock;
    }

    public void setDateStock(String dateStock) {
        this.dateStock = dateStock;
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

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public Stock(String dateStock, Double quantity, Double priceUnitaire, String idProduit) {
        this.dateStock = dateStock;
        this.quantity = quantity;
        this.priceUnitaire = priceUnitaire;
        this.idProduit = idProduit;
    }

    public Stock(){

    }

    public double stockTotal(Connection c) throws Exception {
       Vector<Stock> stockVector = select(c);
       double price = 0.0;
        for (Stock stock: stockVector) {
            price+=stock.getPriceUnitaire()*stock.getQuantity();
        }
        return price;
    }

}
