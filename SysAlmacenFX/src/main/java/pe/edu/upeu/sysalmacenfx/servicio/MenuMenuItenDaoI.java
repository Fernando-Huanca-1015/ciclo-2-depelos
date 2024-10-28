package pe.edu.upeu.sysalmacenfx.servicio;

import pe.edu.upeu.sysalmacenfx.dto.MenuMenuItenTO;

import java.util.List;
import java.util.Properties;

public interface MenuMenuItenDaoI {

    public List<MenuMenuItenTO> listaAccesos(String perfil, Properties idioma);

}
