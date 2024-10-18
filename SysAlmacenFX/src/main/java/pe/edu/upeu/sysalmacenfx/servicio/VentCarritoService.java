package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.CompCarrito;
import pe.edu.upeu.sysalmacenfx.modelo.VentCarrito;
import pe.edu.upeu.sysalmacenfx.repositorio.VentCarritoRepository;

import java.util.List;

@Service
public class VentCarritoService {
    @Autowired
    VentCarritoRepository repo;

    public VentCarrito save(VentCarrito to) {
        return repo.save(to);
    }

    public List<VentCarrito> list() {
        return repo.findAll();
    }

    public VentCarrito update(VentCarrito to, Long id) {
        try {
            VentCarrito toe = repo.findById(id).orElse(null);
            if (toe != null) {
                toe.setEstado(to.getEstado());
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

    public VentCarrito searchById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
