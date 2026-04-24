import utils.MiEntradaSalida;

import java.sql.*;

public class Ejercicio3 {
    static void main(String[] args) {
        PropertiesReader pop;

        try {
            pop = PropertiesReader.getInstance();

            double precioLim = MiEntradaSalida.leerDouble("Introduce un numero limite: ");

            String letra = MiEntradaSalida.leerLinea("Introduce por que letra quieres buscar: ");

            String sql = "Select LOWER(productName) as nombre, buyPrice from products where MSRP < ? and productName like ? order by nombre";

            try (Connection con = DriverManager.getConnection(pop.get("url"), pop.get("usuario"), pop.get("pass")); Statement st = con.createStatement();
                 PreparedStatement pr = con.prepareStatement(sql)){

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
