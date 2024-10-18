package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Emisor;
import pe.edu.upeu.sysalmacenfx.repositorio.EmisorRepository;

import java.util.List;

@Service
public class EmisorService {
    @Autowired
    EmisorRepository repo;

    public Emisor save(Emisor to) {
        return repo.save(to);
    }

    public List<Emisor> list() {
        return repo.findAll();
    }

    public Emisor update(Emisor to, Long id) {
        try {
            Emisor toe = repo.findById(id).orElse(null);
            if (toe != null) {
                toe.setRuc(to.getRuc());
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

    public Emisor searchById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
