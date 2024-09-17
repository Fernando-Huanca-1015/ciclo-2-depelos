package pe.edu.upeu.calcfx.servicio;

import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.CalcTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcServiceImp implements CalcServiceI{

    List<CalcTO> dbOper= new ArrayList<>();

    @Override
    public void guardarResultados(CalcTO to) {
        dbOper.add(to);
    }

    @Override
    public List<CalcTO> obtenerResultados() {
        return dbOper;
    }

    @Override
    public void actualizarResultados(CalcTO to, int index) {
        dbOper.set(index,to);
    }

    @Override
    public void eliminarResultados(int index) {
        dbOper.remove(index);
    }
}
