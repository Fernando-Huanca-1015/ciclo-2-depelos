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
@Table(name = "upeu_compra_detalle")
public class CompraDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra_detalle")
    private Long idCompraDetalle;
    @Column(name = "pu", nullable = false)
    private Double pu;
    @Column(name = "cantidad", nullable = false)
    private Double cantidad;
    @Column(name = "subtotal", nullable = false)
    private Double subtotal;
    @ManyToOne
    @JoinColumn(name = "id_compra", referencedColumnName = "id_compra",
            nullable = false, foreignKey = @ForeignKey(name =
            "FK_COMPRA_COMPRADETALLE"))
    private Compra compra;
    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto",
            nullable = false, foreignKey = @ForeignKey(name =
            "FK_PRODUCTO_COMPRADETALLE"))
    private Producto producto;
}