package pe.edu.upeu.calcfx.modelo;


public class CalcTO {
    int id;
    String num1;
    String num2;
    char operador;
    String resultado;



    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public char getOperador() {
        return operador;
    }

    public void setOperador(char operador) {
        this.operador = operador;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

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
