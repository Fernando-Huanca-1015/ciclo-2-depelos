package pe.edu.upeu.calcfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.calcfx.modelo.CalcTO;

@Repository
public interface CalculadoraRepository extends JpaRepository<CalcTO, Long> {
}
