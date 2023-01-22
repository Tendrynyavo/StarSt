import com.ovoc01.dao.java.MyConnection;
import com.ovoc01.dao.utilities.Utilities;
import element.Composition;
import element.Fabrication;
import element.Product;
import stock.Stock;
import stock.Transaction;

import java.sql.Connection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws Exception{
        Connection c = Utilities.createConnection();
        try {
            System.out.println(new Stock().stockTotal(c));

            Vector<Product> productVector = new Product().select(c);
            Product p = productVector.get(4);
            Vector<Composition>compositionVector = p.getPrimaryCompositions(c);
            System.out.println(p.canBeCreated(c,1));
            //p.create(c,1);
            c.commit();
        }catch (Exception e){
            e.printStackTrace();
                c.rollback();
        }finally {
            c.close();
        }
        //System.out.println(transaction.CUMP(c));
    }
}