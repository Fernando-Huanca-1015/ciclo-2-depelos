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
@Table(name = "upeu_vent_carrito")
public class VentCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrito")
    public Long idCarrito;
    @Column(name = "dniruc", nullable = false, length = 12)
    public String dniruc;
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    public Producto producto;
    @Column(name = "nombre_producto", nullable = false, length = 120)
    public String nombreProducto;
    @Column(name = "cantidad", nullable = false)
    public Double cantidad;
    @Column(name = "punitario", nullable = false)
    public Double punitario;
    @Column(name = "ptotal", nullable = false)
    public Double ptotal;
    @Column(name = "estado", nullable = false)
    public int estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    public Usuario usuario;
}
