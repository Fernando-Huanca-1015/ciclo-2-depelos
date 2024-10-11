package pe.edu.upeu.sysalmacenfx.repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.modelo.Categoria;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class MainX {

    private static final Scanner SCANNER = new Scanner(System.in);

    @Autowired
    CategoriaRepository repository;

    public void registro() {
        System.out.println("MAIN CATEGORIA");
        while (true) {
            System.out.println("Escriba el nombre de la categoría o 'salir' si quiere terminar:");
            String reg_Cat = SCANNER.nextLine().trim();
            if (reg_Cat.equalsIgnoreCase("salir")) {
                break;
            }
            if (reg_Cat.isEmpty()) {
                System.out.println("El espacio no puede estar vacío, inténtelo otra vez");
                continue;
            }
            Categoria categoria = new Categoria();
            categoria.setNombre(reg_Cat);
            Categoria nuevaCategoria = repository.save(categoria);
            System.out.println("Categoría registrada con ID: " + nuevaCategoria.getIdCategoria() + ", Nombre: " + nuevaCategoria.getNombre());
        }
    }

    public void listar() {
        List<Categoria> categorias = repository.findAll();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas.");
        } else {
            System.out.println("ID\tNombre");
            for (Categoria categoria : categorias) {
                System.out.println(categoria.getIdCategoria() + "\t" + categoria.getNombre());
            }
        }
    }

    public void actualizar() {
        try {
            System.out.println("Escribe el ID de la categoría que quieres modificar:");
            Long idMod = SCANNER.nextLong();
            SCANNER.nextLine();  // Limpiar el buffer
            Optional<Categoria> categoriaOptional = repository.findById(idMod);
            if (categoriaOptional.isPresent()) {
                Categoria categoria = categoriaOptional.get();
                System.out.println("Escribe el nuevo nombre para la categoría:");
                String nuevoNombre = SCANNER.nextLine();
                categoria.setNombre(nuevoNombre);
                repository.save(categoria);
                System.out.println("Categoría actualizada.");
            } else {
                System.out.println("No se encontró una categoría con el ID proporcionado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID debe ser numérico.");
            SCANNER.nextLine();  // Limpiar el buffer
        }
    }

    public void borrar() {
        try {
            System.out.println("Escribe el ID de la categoría que quieres eliminar:");
            Long idBorrar = SCANNER.nextLong();
            SCANNER.nextLine();  // Limpiar el buffer
            if (repository.existsById(idBorrar)) {
                repository.deleteById(idBorrar);
                System.out.println("Categoría eliminada.");
            } else {
                System.out.println("No se encontró una categoría con el ID proporcionado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: El ID debe ser numérico.");
            SCANNER.nextLine();  // Limpiar el buffer
        }
    }

    public void borrarTodas() {
        repository.deleteAll();
        System.out.println("Todas las categorías han sido eliminadas.");
    }

    public void menu() {
        String mensaje = "Seleccione una opción Fernando Huanca:\n1 = Crear\n2 = Listar\n3 = Actualizar\n4 = Eliminar\n5 = Eliminar todo\n0 = Salir";
        int opcion = -1;

        while (opcion != 0) {
            System.out.println(mensaje);
            try {
                opcion = Integer.parseInt(SCANNER.nextLine());
                switch (opcion) {
                    case 1:
                        registro();
                        break;
                    case 2:
                        listar();
                        break;
                    case 3:
                        actualizar();
                        break;
                    case 4:
                        borrar();
                        break;
                    case 5:
                        borrarTodas();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida, inténtelo nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        }
    }
}