import utils.MiEntradaSalida;

import java.sql.*;

public class Ejercicio2 {
    static void main(String[] args) {
        String url, usuario, pass;
        PropertiesReader pop = null;

        try {
            pop = PropertiesReader.getInstance();

            double precioLim = MiEntradaSalida.leerDouble("Introduce el precio maximo: ");
            String sqlPrepared = "Select * from products where buyPrice < ? order by buyPrice";

            try (Connection con = DriverManager.getConnection(pop.get("url"), pop.get("usuario"), pop.get("pass")); Statement st = con.createStatement();
                 PreparedStatement pr = con.prepareStatement(sqlPrepared)){

                String sql = "Select * from products where buyPrice < 400 order by buyPrice";

                ResultSet rs = statemen




            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}
