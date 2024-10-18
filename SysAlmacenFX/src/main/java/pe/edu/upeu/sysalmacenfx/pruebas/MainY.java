package pe.edu.upeu.sysalmacenfx.pruebas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;
import pe.edu.upeu.sysalmacenfx.repositorio.CategoriaRepository;
import pe.edu.upeu.sysalmacenfx.servicio.CategoriaService;

import java.util.List;
import java.util.Scanner;

@Component
public class MainY {
    @Autowired
    CategoriaService service;
    public void registro(){
        System.out.println("MAIN CATEGORIA");
        Categoria ca=new Categoria();
        ca.setNombre("Celulares");
        Categoria dd=service.save(ca);
        System.out.println("Reporte:");
        System.out.println(dd.getIdCategoria() + "  "+ dd.getNombre());
    }

    public void listar(){
        List<Categoria> datos=service.list();
        System.out.println("ID\tNombre");
        for (Categoria ca: datos) {
            System.out.println(ca.getIdCategoria()+"\t"+ca.getNombre());
        }
    }

    public void menu(){
        int opc=0;
        Scanner cs=new Scanner(System.in);
        String mensaje="Seleccione una opcion: \n 1=Crear\n2=Listar\n0=Salir";
        System.out.println(mensaje);
        opc=Integer.parseInt(cs.next());
        do {
            switch (opc){
                case 1:{registro();}
                case 2:{listar();}
            }
            System.out.println(mensaje);
            opc=Integer.parseInt(cs.next());
        }while(opc!=0);
    }

}
