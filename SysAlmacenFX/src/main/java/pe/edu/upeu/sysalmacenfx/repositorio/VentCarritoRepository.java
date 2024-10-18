package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysalmacenfx.modelo.VentCarrito;

@Repository
public interface VentCarritoRepository  extends JpaRepository<VentCarrito, Long> {
}
