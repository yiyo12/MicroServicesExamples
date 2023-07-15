package com.mcsvcalificacion.Services;

import com.mcsvcalificacion.Entity.Calificacion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalificacionService {

   List<Calificacion> getAll();
    List<Calificacion> getCalificacionesByUserID(String userId);
   Calificacion saveCalificacion(Calificacion calificacion);

    List<Calificacion> getCalificacionByHotelId(String hotelId);

    void deleteCalificacion(String calificacionId);
}
