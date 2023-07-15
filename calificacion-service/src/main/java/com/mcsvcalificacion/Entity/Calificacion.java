package com.mcsvcalificacion.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("calificaciones")
public class Calificacion {
//las propiedades de mi entity se deben de llamar igual en mi document en mongodb

    @Id
    private String calificacionId;
    private String userId;
    private String hotelId;
    private int calificacion;
    private String observaciones;
}
