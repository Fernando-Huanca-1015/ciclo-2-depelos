package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.CompCarrito;
import pe.edu.upeu.sysalmacenfx.modelo.Compra;
import pe.edu.upeu.sysalmacenfx.repositorio.CompCarritoRepository;
import pe.edu.upeu.sysalmacenfx.repositorio.CompraRepository;

import java.util.List;

@Service
public class CompraService {
    @Autowired
    CompraRepository repo;

    public Compra save(Compra to) {
        return repo.save(to);
    }

    public List<Compra> list() {
        return repo.findAll();
    }

    public Compra update(Compra to, Long id) {
        try {
            Compra toe = repo.findById(id).orElse(null);
            if (toe != null) {
                toe.setNumDoc(to.getNumDoc());
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

    public Compra searchById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
