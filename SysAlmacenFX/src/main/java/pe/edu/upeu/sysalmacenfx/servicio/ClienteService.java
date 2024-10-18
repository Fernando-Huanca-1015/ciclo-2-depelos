package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Cliente;
import pe.edu.upeu.sysalmacenfx.repositorio.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;

    public Cliente save(Cliente to) {
        return repo.save(to);
    }

    public List<Cliente> list() {
        return repo.findAll();
    }

    // Actualizar un cliente por ID
    public Cliente update(Cliente to, String dniruc) {
        try {
            Cliente toe = repo.findById(dniruc).orElse(null);
            if (toe != null) {
                toe.setNombres(to.getNombres());
                return repo.save(toe);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }


    public void delete(String dniruc) {
        repo.deleteById(dniruc);
    }


    public Cliente searchById(String dniruc) {
        return repo.findById(dniruc).orElse(null);
    }
}
