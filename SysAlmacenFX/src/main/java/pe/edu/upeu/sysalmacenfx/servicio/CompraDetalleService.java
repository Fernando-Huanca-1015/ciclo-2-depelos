package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.CompraDetalle;
import pe.edu.upeu.sysalmacenfx.repositorio.CompraDetalleRepository;
import java.util.List;

@Service
public class CompraDetalleService {
    @Autowired
    CompraDetalleRepository repo;

    public CompraDetalle save(CompraDetalle to) {
        return repo.save(to);
    }

    public List<CompraDetalle> list() {
        return repo.findAll();
    }

    public CompraDetalle update(CompraDetalle to, Long id) {
        try {
            CompraDetalle toe = repo.findById(id).orElse(null);
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

    public CompraDetalle searchById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
