package com.user.service.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Calificacion {
    private String calificacionId;
    private String userId;
    private String hotelId;
    private int calificacion;
    private String observaciones;
    private Hotel hotel;

}
