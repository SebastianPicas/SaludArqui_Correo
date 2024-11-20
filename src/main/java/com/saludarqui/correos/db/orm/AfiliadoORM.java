package com.saludarqui.correos.db.orm;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "afiliado")
@Entity
@Data
@NoArgsConstructor
public class AfiliadoORM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_afiliado")
    private Long idAfiliado;

    @Column
    private String nombre;

    @Column
    private String email;
}
