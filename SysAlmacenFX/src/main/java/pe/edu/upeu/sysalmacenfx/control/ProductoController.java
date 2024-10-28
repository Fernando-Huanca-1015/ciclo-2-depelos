package pe.edu.upeu.sysalmacenfx.control;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.componente.ColumnInfo;
import pe.edu.upeu.sysalmacenfx.componente.ComboBoxAutoComplete;
import pe.edu.upeu.sysalmacenfx.componente.TableViewHelper;
import pe.edu.upeu.sysalmacenfx.componente.Toast;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.servicio.CategoriaService;
import pe.edu.upeu.sysalmacenfx.servicio.MarcaService;
import pe.edu.upeu.sysalmacenfx.servicio.ProductoService;
import pe.edu.upeu.sysalmacenfx.servicio.UnidadMedidaService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Component
public class ProductoController {

    @FXML
    TextField txtNombreProducto, txtPUnit,
            txtPUnitOld, txtUtilidad, txtStock, txtStockOld,
            txtFiltroDato;
    @FXML
    ComboBox<ComboBoxOption> cbxMarca;
    @FXML
    ComboBox<ComboBoxOption> cbxCategoria;
    @FXML
    ComboBox<ComboBoxOption> cbxUnidMedida;
    @FXML
    private TableView<Producto> tableView;
    @FXML
    Label lbnMsg;
    @FXML
    private AnchorPane miContenedor;
    Stage stage;

    @Autowired
    MarcaService ms;
    @Autowired
    CategoriaService cs;
    @Autowired
    ProductoService ps;
    @Autowired
    UnidadMedidaService ums;

    private Validator validator;
    ObservableList<Producto> listarProducto;
    Producto formulario;
    Long idProductoCE=0L;

    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), event -> {
            stage = (Stage) miContenedor.getScene().getWindow();
            if (stage != null) {
                System.out.println("El título del stage es: " + stage.getTitle());
            } else {
                System.out.println("Stage aún no disponible.");
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();

        cbxMarca.setTooltip(new Tooltip());
        cbxMarca.getItems().addAll(ms.listarCombobox());
        cbxMarca.setOnAction(event -> {
            ComboBoxOption selectedProduct = cbxMarca.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                String selectedId = selectedProduct.getKey(); // Obtener el ID
                System.out.println("ID del producto seleccionado: " + selectedId);
            }
        });
        new ComboBoxAutoComplete<>(cbxMarca);

        cbxCategoria.setTooltip(new Tooltip());
        cbxCategoria.getItems().addAll(cs.listarCombobox());
        cbxCategoria.setOnAction(event -> {
            ComboBoxOption selectedProduct = cbxCategoria.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                String selectedId = selectedProduct.getKey(); // Obtener el ID
                System.out.println("ID del producto seleccionado: " + selectedId);
            }
        });
        new ComboBoxAutoComplete<>(cbxCategoria);

        cbxUnidMedida.setTooltip(new Tooltip());
        cbxUnidMedida.getItems().addAll(ums.listarCombobox());
        cbxUnidMedida.setOnAction(event -> {
            ComboBoxOption selectedProduct = cbxUnidMedida.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                String selectedId = selectedProduct.getKey(); // Obtener el ID
                System.out.println("ID del producto seleccionado: " + selectedId);
            }
        });
        new ComboBoxAutoComplete<>(cbxUnidMedida);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        // Crear instancia de la clase genérica TableViewHelper
        TableViewHelper<Producto> tableViewHelper = new TableViewHelper<>();
        LinkedHashMap<String, ColumnInfo> columns = new LinkedHashMap<>();
        columns.put("ID Pro.", new ColumnInfo("idProducto", 60.0)); // Columna visible "Columna 1" mapea al campo "campo1"
        columns.put("Nombre Producto", new ColumnInfo("nombre", 200.0)); // Columna visible "Columna 2" mapea al campo "campo2"
        columns.put("P. Unitario", new ColumnInfo("pu", 150.0)); // Columna visible "Columna 2" mapea al campo "campo2"
        columns.put("Utilidad", new ColumnInfo("utilidad", 100.0)); // Columna visible "Columna 2" mapea al campo "campo2"
        columns.put("Marca", new ColumnInfo("marca.nombre", 200.0)); // Columna visible "Columna 2" mapea al campo "campo2"
        columns.put("Categoria", new ColumnInfo("categoria.nombre", 200.0));
        columns.put("Unid. Medida", new ColumnInfo("unidadMedida.nombreMedida",150.0));


        Consumer<Producto> updateAction = (Producto producto) -> {
            System.out.println("Actualizar: " + producto);
            editForm(producto);
        };
        Consumer<Producto> deleteAction = (Producto producto) -> {System.out.println("Actualizar: " + producto);
            ps.delete(producto.getIdProducto()); /*deletePerson(usuario);*/
            double with=stage.getWidth()/1.5;
            double h=stage.getHeight()/2;
            Toast.showToast(stage, "Se eliminó correctamente!!", 2000, with, h);
            listar();
        };

        tableViewHelper.addColumnsInOrderWithSize(tableView, columns,updateAction, deleteAction );

        tableView.setTableMenuButtonVisible(true);
        listar();

    }

    public void listar(){
        try {
            tableView.getItems().clear();
            listarProducto = FXCollections.observableArrayList(ps.list());
            tableView.getItems().addAll(listarProducto);
            // Agregar un listener al campo de texto txtFiltroDato para filtrar los productos
            txtFiltroDato.textProperty().addListener((observable, oldValue, newValue) -> {
                filtrarProductos(newValue);
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void limpiarError(){
        txtNombreProducto.getStyleClass().remove("text-field-error");
        txtPUnit.getStyleClass().remove("text-field-error");
        txtPUnitOld.getStyleClass().remove("text-field-error");
        txtUtilidad.getStyleClass().remove("text-field-error");
        txtStock.getStyleClass().remove("text-field-error");
        txtStockOld.getStyleClass().remove("text-field-error");
        cbxMarca.getStyleClass().remove("text-field-error");
        cbxCategoria.getStyleClass().remove("text-field-error");
        cbxUnidMedida.getStyleClass().remove("text-field-error");
    }

    public void clearForm(){
        txtNombreProducto.setText("");
        txtPUnit.setText("");
        txtPUnitOld.setText("");
        txtUtilidad.setText("");
        txtStock.setText("");
        txtStockOld.setText("");
        cbxMarca.getSelectionModel().select(null);
        cbxCategoria.getSelectionModel().select(null);
        cbxUnidMedida.getSelectionModel().select(null);
        idProductoCE=0L;
        limpiarError();
    }

    @FXML
    public void cancelarAccion(){
        clearForm();
        limpiarError();
    }

    void validarCampos(List<ConstraintViolation<Producto>> violacionesOrdenadasPorPropiedad){
// Crear un LinkedHashMap para ordenar las violaciones
        LinkedHashMap<String, String> erroresOrdenados = new LinkedHashMap<>();
// Mostrar el primer mensaje de error
        for (ConstraintViolation<Producto> violacion : violacionesOrdenadasPorPropiedad) {
            String campo = violacion.getPropertyPath().toString();
            if(campo.equals("nombre")){
                erroresOrdenados.put("nombre", violacion.getMessage());
                txtNombreProducto.getStyleClass().add("text-field-error");
            }else if (campo.equals("pu")) {
                erroresOrdenados.put("pu", violacion.getMessage());
                txtPUnit.getStyleClass().add("text-field-error");
            }else if (campo.equals("puOld")) {
                erroresOrdenados.put("puold", violacion.getMessage());
                txtPUnitOld.getStyleClass().add("text-field-error");
            }else if (campo.equals("utilidad")) {
                erroresOrdenados.put("utilidad", violacion.getMessage());
                txtUtilidad.getStyleClass().add("text-field-error");
            }else if (campo.equals("stock")) {
                erroresOrdenados.put("stock", violacion.getMessage());
                txtStock.getStyleClass().add("text-field-error");
            }else if (campo.equals("stockOld")) {
                erroresOrdenados.put("stockold", violacion.getMessage());
                txtStockOld.getStyleClass().add("text-field-error");
            }else if (campo.equals("marca")) {
                erroresOrdenados.put("marca", violacion.getMessage());
                cbxMarca.getStyleClass().add("text-field-error");
            }else if (campo.equals("categoria")) {
                erroresOrdenados.put("categoria", violacion.getMessage());
                cbxCategoria.getStyleClass().add("text-field-error");
            }else if (campo.equals("unidadMedida")) {
                erroresOrdenados.put("unidadmedida", violacion.getMessage());
                cbxUnidMedida.getStyleClass().add("text-field-error");
            }
        }
        // Mostrar el primer error en el orden deseado
        Map.Entry<String, String> primerError = erroresOrdenados.entrySet().iterator().next();
        lbnMsg.setText(primerError.getValue()); // Mostrar el mensaje del primer error
        lbnMsg.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
    }

    @FXML
    public void validarFormulario() {
        formulario = new Producto();
        formulario.setNombre(txtNombreProducto.getText());
        formulario.setPu(Double.parseDouble(txtPUnit.getText()==""?"0":txtPUnit.getText()));
        formulario.setPuOld(Double.parseDouble(txtPUnitOld.getText()==""?"0":txtPUnitOld.getText()));
        formulario.setUtilidad(Double.parseDouble(txtUtilidad.getText()==""?"0":txtUtilidad.getText()));
        formulario.setStock(Double.parseDouble(txtStock.getText()==""?"0":txtStock.getText()));
        formulario.setStockOld(Double.parseDouble(txtStockOld.getText()==""?"0":txtStockOld.getText()));
        String idxM=cbxMarca.getSelectionModel().getSelectedItem()==null?"0":cbxMarca.getSelectionModel().getSelectedItem().getKey();
        formulario.setMarca(ms.searchById(Long.parseLong(idxM)));
        String idxC=cbxCategoria.getSelectionModel().getSelectedItem()==null?"0":cbxCategoria.getSelectionModel().getSelectedItem().getKey();
        formulario.setCategoria(cs.searchById(Long.parseLong(idxC)));
        String idxUM=cbxUnidMedida.getSelectionModel().getSelectedItem()==null?"0":cbxUnidMedida.getSelectionModel().getSelectedItem().getKey();
        formulario.setUnidadMedida(ums.searchById(Long.parseLong(idxUM)));
        Set<ConstraintViolation<Producto>> violaciones = validator.validate(formulario);
// Si prefieres ordenarlo por el nombre de la propiedad que violó la restricción, podrías usar:
        List<ConstraintViolation<Producto>> violacionesOrdenadasPorPropiedad = violaciones.stream()
                .sorted((v1, v2) -> v1.getPropertyPath().toString().compareTo(v2.getPropertyPath().toString()))
                .collect(Collectors.toList());
        if (violacionesOrdenadasPorPropiedad.isEmpty()) {
// Los datos son válidos
            lbnMsg.setText("Formulario válido");
            lbnMsg.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            limpiarError();
            double with=stage.getWidth()/1.5;
            double h=stage.getHeight()/2;
            if(idProductoCE!=0L && idProductoCE>0L){
                formulario.setIdProducto(idProductoCE);
                ps.update(formulario);
                Toast.showToast(stage, "Se actualizó correctamente!!", 2000, with, h);
                clearForm();
            }else{
                ps.save(formulario);
                Toast.showToast(stage, "Se guardo correctamente!!", 2000, with, h);
                clearForm();
            }
            listar();
        } else {
            validarCampos(violacionesOrdenadasPorPropiedad);
        }
    }

    private void filtrarProductos(String filtro) {
        if (filtro == null || filtro.isEmpty()) {
// Si el filtro está vacío, volver a mostrar la lista completa
            tableView.getItems().clear();
            tableView.getItems().addAll(listarProducto);
        } else {
// Aplicar el filtro
            String lowerCaseFilter = filtro.toLowerCase();
            List<Producto> productosFiltrados = listarProducto.stream()
                    .filter(producto -> {
// Verificar si el filtro coincide con alguno de los campos
                        if (producto.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        if (String.valueOf(producto.getPu()).contains(lowerCaseFilter)) {
                            return true;
                        }
                        if (String.valueOf(producto.getUtilidad()).contains(lowerCaseFilter)) {
                            return true;
                        }
                        if (producto.getMarca().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        if (producto.getCategoria().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        }
                        return false; // Si no coincide con ningún campo
                    })
                    .collect(Collectors.toList());
            // Actualizar los items del TableView con los productos filtrados
            tableView.getItems().clear();
            tableView.getItems().addAll(productosFiltrados);
        }
    }

    public void editForm(Producto producto){
        txtNombreProducto.setText(producto.getNombre());
        txtPUnit.setText(producto.getPu().toString());
        txtPUnitOld.setText(producto.getPuOld().toString());
        txtUtilidad.setText(producto.getUtilidad().toString());
        txtStock.setText(producto.getStock().toString());
        txtStockOld.setText(producto.getStockOld().toString());
// Seleccionar el ítem en cbxMarca según el ID de Marca
        cbxMarca.getSelectionModel().select(
                cbxMarca.getItems().stream()
                        .filter(marca -> Long.parseLong(marca.getKey())==producto.getMarca().getIdMarca())
                        .findFirst()
                        .orElse(null)
        );
// Seleccionar el ítem en cbxCategoria según el ID de Categoria
        cbxCategoria.getSelectionModel().select(
                cbxCategoria.getItems().stream()
                        .filter(categoria -> Long.parseLong(categoria.getKey())==producto.getCategoria().getIdCategoria())
                        .findFirst()
                        .orElse(null)
        );
// Seleccionar el ítem en cbxUnidMedida según el ID de Unidad de Medida
        cbxUnidMedida.getSelectionModel().select(
                cbxUnidMedida.getItems().stream()
                        .filter(unidad -> Long.parseLong(unidad.getKey())==producto.getUnidadMedida().getIdUnidad())
                        .findFirst()
                        .orElse(null)
        );
        idProductoCE=producto.getIdProducto();
        limpiarError();
    }

}
