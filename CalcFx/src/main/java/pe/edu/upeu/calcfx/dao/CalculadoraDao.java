package pe.edu.upeu.calcfx.dao;

import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.conexion.ConnDB;
import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class CalculadoraDao {
    PreparedStatement ps;
    ResultSet rs;
    ConnDB con;
    Connection conn;

    CalculadoraDao(){
        con = new ConnDB();
        conn= con.getConn();
    }

    public List<CalcTO> listar(){
        List<CalcTO> listC = new ArrayList<>();
        try {
            ps=conn.prepareStatement("SELECT * from calculadora;");
            rs=ps.executeQuery();
            while(rs.next()){
                CalcTO calcTO = new CalcTO();
                calcTO.setNum1(rs.getString("num1"));
                calcTO.setNum2(rs.getString("num2"));
                calcTO.setOperador(rs.getString("operador").charAt(0));
                calcTO.setResultado(rs.getString("resultado"));
                listC.add(calcTO);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return listC;
    }

    public void insertar(CalcTO calcTO){
        try {
            ps=conn.prepareStatement("INSERT into calculadora(num1, num2, operador, resultado) values(?, ?, ?, ?); ");
            ps.setString(1, calcTO.getNum1());
            ps.setString(2, calcTO.getNum2());
            ps.setString(3, String.valueOf(calcTO.getOperador()));
            ps.setString(4, calcTO.getResultado());
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
