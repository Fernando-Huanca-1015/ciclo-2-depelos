package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.CompCarrito;
import pe.edu.upeu.sysalmacenfx.repositorio.CompCarritoRepository;

import java.util.List;

@Service
public class CompCarritoService {
    @Autowired
    CompCarritoRepository repo;

    public CompCarrito save(CompCarrito to) {
        return repo.save(to);
    }

    public List<CompCarrito> list() {
        return repo.findAll();
    }

    public CompCarrito update(CompCarrito to, Long id) {
        try {
            CompCarrito toe = repo.findById(id).orElse(null);
            if (toe != null) {
                toe.setCantidad(to.getCantidad());
                return repo.save(toe);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public CompCarrito searchById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
