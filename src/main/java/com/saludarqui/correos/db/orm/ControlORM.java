package com.saludarqui.correos.db.orm;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "control")
@Entity
@Data
@NoArgsConstructor
public class ControlORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Control")
    private Long idControl;

    @Column
    private Long idAfiliado;

    @Column
    private String nombreAfiliado;

    @Column
    private Long idBeneficiario;

    @Column
    private String nombreBeneficiario;
}
