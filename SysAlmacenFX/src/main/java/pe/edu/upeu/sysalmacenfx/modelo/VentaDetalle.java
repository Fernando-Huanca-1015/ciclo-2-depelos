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
@Table(name = "upeu_venta_detalle")
public class VentaDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta_detalle")
    private Long idVentaDetalle;
    @Column(name = "pu", nullable = false)
    private Double pu;
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    @Column(name = "descuento", nullable = false)
    private Double descuento;
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name = "id_venta", referencedColumnName =
            "id_venta",
            nullable = false, foreignKey = @ForeignKey(name =
            "FK_VENTA_VENTADETALLE"))
    private Venta venta;
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName =
            "id_producto",
            nullable = false, foreignKey = @ForeignKey(name =
            "FK_PRODUCTO_VENTADETALLE"))
    private Producto producto;
}