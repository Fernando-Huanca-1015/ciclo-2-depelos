package pe.edu.upeu.sysalmacenfx.servicio;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacenfx.dto.ModeloDataAutocomplet;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.repositorio.ProductoRepository;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository repo;
    Logger logger= LoggerFactory.getLogger(ProductoService.class);

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

    public List<ModeloDataAutocomplet> listAutoCompletProducto() {
        List<ModeloDataAutocomplet> listarProducto = new ArrayList<>();
        try {
            for (Producto producto : repo.findAll()) {
                ModeloDataAutocomplet data = new ModeloDataAutocomplet();
                data.setIdx(String.valueOf(producto.getIdProducto()));
                data.setNameDysplay(producto.getNombre());
                data.setOtherData(producto.getPu() + ":" + producto.getStock());
                listarProducto.add(data);
            }
        } catch (Exception e) {
            logger.error("Error al realizar la busqueda", e);
        }
        return listarProducto;
    }


}
