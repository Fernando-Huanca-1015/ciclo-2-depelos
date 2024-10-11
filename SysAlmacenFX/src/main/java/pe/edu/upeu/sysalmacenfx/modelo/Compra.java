package pe.edu.upeu.sysalmacenfx.modelo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "upeu_compra")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_compra")
    private Long idCompra;
    @Column(name = "precio_base", nullable = false)
    private Double precioBase;
    @Column(name = "igv", nullable = false)
    private Double igv;
    @Column(name = "preciototal", nullable = false)
    private Double precioTotal;
    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id_proveedor",
            nullable = false,foreignKey = @ForeignKey(name = "FK_PROVEEDOR_COMPRA"))
    private Proveedor proveedor;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario",
            nullable = false, foreignKey = @ForeignKey(name = "FK_USUARIO_COMPRA"))
    private Usuario usuario;
    @Column(name = "serie", nullable = false, length = 20)
    private String serie;
    @Column(name = "num_doc", nullable = false, length = 20)
    private String numDoc;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_comp", nullable = false)
    private LocalDate fechaComp;
    @Column(name = "tipo_doc", nullable = false, length = 12)
    private String tipoDoc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fecha_reg", nullable = false)
    private LocalDateTime fechaReg;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CompraDetalle> compraDetalles;
}