package pe.edu.upeu.calcfx.dao;

import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.conexion.ConnDB;
import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public int idGenerate(){
        int idx=0;
        try {
            ps=conn.prepareStatement("SELECT (MAX(id)+1) as idx FROM calculadora;");
            rs=ps.executeQuery();
            if(rs.next()){
                idx=rs.getInt("idx");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return idx;
    }

    public List<CalcTO> listar(){

        List<CalcTO> listC = new ArrayList<>();
        try {
            ps=conn.prepareStatement("SELECT * from calculadora;");
            rs=ps.executeQuery();
            while(rs.next()){
                CalcTO calcTO = new CalcTO();
                calcTO.setId(rs.getInt("id"));
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
        int idx=idGenerate();
        System.out.println(idx);
        int i=0;
        conn= con.getConn();
        try {
            ps=conn.prepareStatement("INSERT into calculadora(id, num1, num2, operador, resultado) values(?,?, ?, ?, ?);  ");
            ps.setInt(++i, idx);
            ps.setString(++i, calcTO.getNum1());
            ps.setString(++i, calcTO.getNum2());
            ps.setString(++i, String.valueOf(calcTO.getOperador()));
            ps.setString(++i, calcTO.getResultado());
            ps.executeUpdate();
           // System.out.println(idx+" n1:"+calcTO.getNum1()+" n2:"+calcTO.getNum2()+" op:"+calcTO.getOperador()+ calcTO.getResultado());
        }catch (Exception e){
            System.out.println("EIN:"+e.getMessage());
        }
    }

    public void actualizar(CalcTO calcTO, int id){
        int i=0;
        try {
            ps=conn.prepareStatement("UPDATE  calculadora SET num1 =?, num2 =?, operador =?, resultado =? WHERE id =?; ");
            ps.setString(++i, calcTO.getNum1());
            ps.setString(++i, calcTO.getNum2());
            ps.setString(++i, String.valueOf(calcTO.getOperador()));
            ps.setString(++i, calcTO.getResultado());
            ps.setInt(++i, id);
            ps.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void eliminar(int id){
        try {
            ps=conn.prepareStatement("DELETE FROM calculadora WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
