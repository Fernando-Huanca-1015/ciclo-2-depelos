package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upeu.sysalmacenfx.modelo.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    @Query(value = "SELECT u.* FROM upeu_usuario u WHERE u.user=:userx ", nativeQuery = true)
    Usuario buscarUsuario(@Param("userx") String userx);

    @Query(value = "SELECT u.* FROM upeu_usuario u WHERE u.user=:user and u.clave=:clave", nativeQuery = true)
    Usuario loginUsuario(@Param("user") String user, @Param("clave") String clave);
}
