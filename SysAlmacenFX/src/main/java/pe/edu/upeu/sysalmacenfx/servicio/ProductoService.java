package pe.edu.upeu.sysalmacenfx.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;

import java.util.List;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository repo;

    public Producto save(Producto to){
        return repo.save(to);
    }
    public List<Producto> list(){
        return repo.findAll();
    }
    public Producto update(Producto to, Long id){
        try {
            Producto toe=repo.findById(id).get();
            if(toe!=null){
                toe.setNombre(to.getNombre());
            }
            return repo.save(toe);
        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
        return null;
    }

    public Producto update(Producto to){
        return repo.save(to);
    }
    public void delete(Long id){
        repo.deleteById(id);
    }
    public Producto searchById(Long id){
        return repo.findById(id).get();
    }
}
