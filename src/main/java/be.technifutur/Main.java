package be.technifutur;

import be.technifutur.dao.ProductDAO;
import be.technifutur.dao.ShipperDAO;
import be.technifutur.dao.SupplierDAO;
import be.technifutur.dao.impl.ProductDAOimpl;
import be.technifutur.dao.impl.ShipperDAOimpl;
import be.technifutur.dao.impl.SupplierDAOimpl;

public class Main {
    public static void main(String[] args) {
        ProductDAO products = new ProductDAOimpl();
        SupplierDAO suppliers = new SupplierDAOimpl();
        ShipperDAO shippers = new ShipperDAOimpl();

        //*/

        //*/
    }
}
