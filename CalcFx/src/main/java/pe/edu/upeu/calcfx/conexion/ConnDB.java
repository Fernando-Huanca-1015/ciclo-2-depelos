package pe.edu.upeu.calcfx.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnDB {
    Connection conn;

    public Connection getConn() {
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:data/dbcalc.db?foreign_keys=on;";
            if (conn== null) {
                conn= DriverManager.getConnection(dbURL);
            }
            System.out.println("Conecion exitosa....");

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        ConnDB db = new ConnDB();
        Connection conx = db.getConn();
        try {
            PreparedStatement ps=conx.prepareStatement("SELECT * from calculadora");
            ResultSet rs=ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("num1")+
                        rs.getString("num2")+
                        rs.getString("operador")+
                        rs.getString("resultado")
                        );
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
