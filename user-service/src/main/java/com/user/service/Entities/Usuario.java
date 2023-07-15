package com.user.service.Entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Usuarios")
public class Usuario {
    @Id
    @Column(name = "usuario")
    private String usuarioId;

    @Column(name = "nombre", length = 20)
    private String nombre;

    @Column(name = "email")
    private String email;

    @Column(name = "informacion")
    private String informacion;

    @Transient //Esto va hacer que esta lista no se guarde (persistir) en la base de datos
    private List<Calificacion> usuarioCalificaciones = new ArrayList<>();

}
