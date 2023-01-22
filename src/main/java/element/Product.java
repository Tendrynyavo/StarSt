package element;

import com.ovoc01.dao.annotation.*;
import com.ovoc01.dao.java.ObjectDAO;
import stock.Stock;
import stock.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

@Tables(name = "produits")
public class Product extends ObjectDAO {
    @Column
    @PrimaryKey(prefix = "P", seqComp = "productSeq")
    String idProduit;

    @Column
    @ForeignKey(classReference = Composition.class)
    String reference;
    @Column
    @Nummer
    Double quantite;

    Composition self;

    public String getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(String idProduit) {
        this.idProduit = idProduit;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) {
        this.quantite = quantite;
    }

    public Composition getSelf() {
        return self;
    }

    public void setSelf(Composition self) {
        this.self = self;
    }


    /**
     * This function calculates the total cost of a product based on its compositions
     *
     * @param c - The connection to the database
     * @return The total cost of the product
     * @throws Exception - If there is an error with the database connection or with the SELECT statement
     */
    public Double calculPrix(Connection c) throws Exception {
        Double price = 0.0;
        Fabrication fabrication = new Fabrication();
        fabrication.setId_produit(getReference());
        Vector<Fabrication> fabricationVector = fabrication.select(c);
        for (Fabrication fabrication1 : fabricationVector) {
            Composition comp = new Composition();
            comp.setIdComposant(fabrication1.getId_composant());
            Vector<Composition> compositions = comp.select(c);
            Vector<Composition> t = new Vector<>();
            compositions.get(0).setQuantity(fabrication1.getQuantite());
            compositions.get(0).getPrimaryComposition(c, t);
            compositions.get(0).setCompositions(t);
            for (int j = 0; j < t.size(); j++) {
                price += (t.get(j).getPrix());
            }
        }
        return price;
    }

    public Vector<Composition> getPrimaryCompositions(Connection c) throws Exception {
        Vector<Composition> primaryCompositions = new Vector<>();
        Fabrication fabrication = new Fabrication();
        fabrication.setId_produit(getReference());
        Vector<Fabrication> fabricationVector = fabrication.select(c);
        double price = 0;
        for (Fabrication fabrication1 : fabricationVector) {
            Composition comp = new Composition();
            comp.setIdComposant(fabrication1.getId_composant());
            Vector<Composition> compositions = comp.select(c);
            Vector<Composition> temp = new Vector<>();
            compositions.get(0).setQuantity(fabrication1.getQuantite());
            compositions.get(0).getPrimaryComposition(c, temp);
            compositions.get(0).setCompositions(temp);
            primaryCompositions.addAll(temp);
        }
        //System.out.println(price);
        return primaryCompositions;
    }

    public boolean canBeCreated(Connection c, double amount) throws Exception {
        boolean canBeCreated = true;
        Vector<Composition> primaryCompositions = getPrimaryCompositions(c);
        Stock stock = new Stock();
        for (Composition comp : primaryCompositions) {
            comp.setQuantity(comp.getQuantity() * amount);
            stock.setIdProduit(comp.getIdComposant());
            Vector<Stock> stockVector = stock.select(c);
            System.out.println(comp.getName());
            //System.out.println(comp.getQuantity());
            if (stockVector.size() == 0) {
                System.out.println(3);
                return false;
            }
            if (stockVector.get(0).getQuantity() < comp.getQuantity()) {
                return false;
            }
        }
        return true;
    }

    public void create(Connection c, double amount) throws Exception {
        if (canBeCreated(c, amount)) {
            System.out.println(2);
            Vector<Composition> primaryCompositions = getPrimaryCompositions(c);
            for (Composition comp : primaryCompositions) {
                System.out.println(4);
                Transaction t = new Transaction();
                t.setIdProduit(comp.getIdComposant());
                t.setQuantity(comp.getQuantity());
                t.setIsEntering(false);
                t.setDateTransaction("now()");
                t.setPriceUnitaire(comp.getPrix());
                t.setPriceUnitaire(t.CUMP(c));
                t.manageStock(c);
            }
            Transaction t = new Transaction();
            t.setIdProduit(getReference());
            t.setQuantity(Double.valueOf(amount));
            t.setIsEntering(true);
            t.setDateTransaction("now()");
            t.setPriceUnitaire(calculPrix(c));
            t.setPriceUnitaire(t.CUMP(c));
            t.manageStock(c);
            System.out.println(amount + " Product create");
        }
    }

}
