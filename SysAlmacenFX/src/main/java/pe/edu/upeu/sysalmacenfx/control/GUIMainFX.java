package pe.edu.upeu.sysalmacenfx.control;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.dto.MenuMenuItenTO;
import pe.edu.upeu.sysalmacenfx.dto.SessionManager;
import pe.edu.upeu.sysalmacenfx.servicio.MenuMenuItemDao;
import pe.edu.upeu.sysalmacenfx.servicio.MenuMenuItenDaoI;
import pe.edu.upeu.sysalmacenfx.utils.UtilsX;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;

@Component
public class GUIMainFX {

    @Autowired
    private ApplicationContext context;
    Preferences userPrefs = Preferences.userRoot();
    UtilsX util = new UtilsX();
    Properties myresources = new Properties();
    MenuMenuItenDaoI mmiDao;
    @FXML
    private TabPane tabPaneFx;
    List<MenuMenuItenTO> lista;
    @FXML
    private BorderPane bp;
    @FXML
    private MenuBar menuBarFx;
    @FXML
    public void initialize() {
        myresources = util.detectLanguage(userPrefs.get("IDIOMAX", "en"));
        mmiDao = new MenuMenuItemDao();
        String perf= SessionManager.getInstance().getNombrePerfil();
        lista = mmiDao.listaAccesos(perf, myresources);
        int[] mmi = contarMenuMunuItem(lista);
        Menu[] menu = new Menu[mmi[0]];
        MenuItem[] menuItem = new MenuItem[mmi[1]];
        menuBarFx = new MenuBar();
        MenuItemListener d = new MenuItemListener();
        SampleMenuListener m = new SampleMenuListener();
        String menuN = "";
        int menui = 0, menuitem = 0;
        char conti = 'N';
        for (MenuMenuItenTO mmix : lista) {
            if (!mmix.menunombre.equals(menuN)) {
                menu[menui] = new Menu(mmix.menunombre);
                menu[menui].setId("m" + mmix.nombreObj);
                menu[menui].setOnShowing(m::menuSelected);
                if (!mmix.menuitemnombre.equals("")) {
                    menuItem[menuitem] = new MenuItem(mmix.menuitemnombre);
                    menuItem[menuitem].setId("mi" + mmix.nombreObj);
                    menuItem[menuitem].setOnAction(d::handle);
                    menu[menui].getItems().add(menuItem[menuitem]);
                    menuitem++;
                }
                menuBarFx.getMenus().add(menu[menui]);
                menuN = mmix.menunombre;
                conti = 'N';
                menui++;
            } else {
                conti = 'S';
            }
            if (!mmix.menuitemnombre.equals("") && mmix.menunombre.equals(menuN) && conti == 'S') {
                menuItem[menuitem] = new MenuItem(mmix.menuitemnombre);
                menuItem[menuitem].setId("mi" + mmix.nombreObj);
                menuItem[menuitem].setOnAction(d::handle);
                menu[menui - 1].getItems().add(menuItem[menuitem]);
                menuitem++;
            }
        }
// Layout principal
        bp.setTop(menuBarFx);
        bp.setCenter(tabPaneFx);
    }


    public int[] contarMenuMunuItem(List<MenuMenuItenTO> data) {
        int menui = 0, menuitem = 0;
        String menuN = "";
        for (MenuMenuItenTO mmi : data) {
            if (!mmi.menunombre.equals(menuN)) {
                menuN = mmi.menunombre;
                menui++;
            }
            if (!mmi.menuitemnombre.equals("")) {
                menuitem++;
            }
        }
        return new int[]{menui, menuitem};
    }


    class MenuItemListener {
        public void handle(javafx.event.ActionEvent e) {

            if (((MenuItem) e.getSource()).getId().equals("mimiregproduct")) {
                tabPaneFx.getTabs().clear();
                FXMLLoader loader = new
                        FXMLLoader(getClass().getResource("/view/main_producto.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent paneFromFXML;
                try {
                    paneFromFXML = loader.load(); // Cargar el contenido FXML
                    ScrollPane dd= new ScrollPane(paneFromFXML);
                    //mc.setContexto(ctx);
                    Tab clienteTab = new Tab("Reg. Producto",dd );
                    tabPaneFx.getTabs().add(clienteTab);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            if(((MenuItem) e.getSource()).getId().equals("mimiautcomp")){
                tabPaneFx.getTabs().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main_prod_autocomp.fxml"));
                loader.setControllerFactory(context::getBean);
                Parent paneFromFXML;
                try {
                    paneFromFXML = loader.load(); // Cargar el contenido FXML
                    ScrollPane dd= new ScrollPane(paneFromFXML);
                    //mc.setContexto(ctx);
                    Tab clienteTab = new Tab("Form Autocomplete",dd );
                    tabPaneFx.getTabs().add(clienteTab);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }


            if (((MenuItem) e.getSource()).getId().equals("mimiselectall")) {
                tabPaneFx.getTabs().clear();
            // Añade la lógica para "mimiselectall"
            }
        }
    }
    class SampleMenuListener {
        public void menuSelected(javafx.event.Event e) {
            if (((Menu) e.getSource()).getId().equals("mmiver1")) {
                System.out.println("llego help");
            }
        }
    }


}
