package pe.edu.upeu.sysalmacenfx.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "upeu_comp_carrito")
public class CompCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compcarrito")
    private Long idCompCarrito;
    @ManyToOne
    @JoinColumn(name = "id_proveedor", nullable = false)
    private Proveedor proveedor;
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
    @Column(name = "nombre_producto", nullable = false,
            length = 160)
    private String nombreProducto;
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    @Column(name = "punitario", nullable = false)
    private Double punitario;
    @Column(name = "ptotal", nullable = false)
    private Double ptotal;
    @Column(name = "estado", nullable = false)
    private int estado;
    @ManyToOne
    @JoinColumn(name = "id_usuario",  nullable = false)
    private Usuario usuario;
}