package pe.edu.upeu.sysalmacenfx.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ModeloDataAutocomplet;
import pe.edu.upeu.sysalmacenfx.modelo.Cliente;
import pe.edu.upeu.sysalmacenfx.repositorio.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository repo;
    Logger logger= LoggerFactory.getLogger(ClienteService.class);
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

    public List<ModeloDataAutocomplet> listAutoCompletCliente() {
        List<ModeloDataAutocomplet> listarclientes = new ArrayList<>();
        try {
            for (Cliente cliente : repo.findAll()) {
                ModeloDataAutocomplet data = new ModeloDataAutocomplet();
                data.setIdx(cliente.getDniruc());
                data.setNameDysplay(cliente.getNombres());
                data.setOtherData(cliente.getTipoDocumento());
                listarclientes.add(data);
            }
        } catch (Exception e) {
            logger.error("Error durante la operación", e);
        }
        return listarclientes;
    }

}
