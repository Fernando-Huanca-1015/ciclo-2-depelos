package pe.edu.upeu.calcfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.calcfx.modelo.CalcTO;
import pe.edu.upeu.calcfx.repositorio.CalculadoraRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalcServiceImp implements CalcServiceI{

    @Autowired
    CalculadoraRepository repository;

    //List<CalcTO> dbOper= new ArrayList<>();

    @Override
    public void guardarResultados(CalcTO to) {
        repository.save(to);
    }

    @Override
    public List<CalcTO> obtenerResultados() {
        return repository.findAll();
    }

    @Override
    public void actualizarResultados(CalcTO to, Long index) {
        to.setId(index);
        repository.save(to);
    }

    @Override
    public void eliminarResultados(Long index) {
        repository.deleteById(index);
    }
}
