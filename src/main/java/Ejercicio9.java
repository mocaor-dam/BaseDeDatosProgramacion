import utils.MiEntradaSalida;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ejercicio9 {
    private static final String URL = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static void main(String[] args) {
        String nombreUsuario = MiEntradaSalida.leerLinea("Introduce el nombre del usuario: \n");

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            eliminarUsuario(connection, nombreUsuario);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarUsuario(Connection connection, String nombreUsuario){
        try(PreparedStatement pt1 = connection.prepareStatement("DELETE FROM orderdetails WHERE orderNumber in (SELECT orderNumber FROM orders WHERE customerNumber = (SELECT customerNumber FROM customers WHERE customerName = ?))");
            PreparedStatement pt2 = connection.prepareStatement("DELETE FROM orders WHERE customerNumber = (SELECT customerNumber FROM customers WHERE customerName = ?)");
            PreparedStatement pt3 = connection.prepareStatement("DELETE FROM payments WHERE customerNumber = (SELECT customerNumber FROM customers WHERE customerName = ?)");
            PreparedStatement pt4 = connection.prepareStatement("DELETE FROM customers WHERE customerName = ?")) {

            connection.setAutoCommit(false);

            pt1.setString(1, nombreUsuario);
            pt2.setString(1, nombreUsuario);
            pt3.setString(1, nombreUsuario);
            pt4.setString(1, nombreUsuario);

            pt1.executeUpdate();
            pt2.executeUpdate();
            pt3.executeUpdate();
            int lineasBorradas = pt4.executeUpdate();

            if (lineasBorradas > 0){
                connection.commit();
                System.out.println("Borrado con éxito");
            } else {
                connection.rollback();
                System.out.println("No se ha encontrado");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }


}
