package pe.edu.upeu.calcfx.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "calculadora")
public class CalcTO {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;
    String num1;
    String num2;
    char operador;
    String resultado;

    @Override
    public String toString() {
        return "CalcTO{" +
                "num1='" + num1 + '\'' +
                ", num2='" + num2 + '\'' +
                ", operador=" + operador +
                ", resultado='" + resultado + '\'' +
                '}';
    }
}
