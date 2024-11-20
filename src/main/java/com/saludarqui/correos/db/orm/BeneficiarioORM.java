package com.saludarqui.correos.db.orm;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "beneficiario")
@Entity
@Data
@NoArgsConstructor
public class BeneficiarioORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_beneficiario")
    private Long idBeneficiario;

    @Column
    private String nombre;

    @JoinColumn(name = "id_afiliado", unique = true)
    @OneToOne(cascade = CascadeType.ALL)
    private AfiliadoORM afliliadoORM;

}
