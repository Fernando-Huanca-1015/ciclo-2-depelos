package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysalmacenfx.modelo.VentaDetalle;

@Repository
public interface VentaDetalleRepository  extends JpaRepository<VentaDetalle, Long> {
}
