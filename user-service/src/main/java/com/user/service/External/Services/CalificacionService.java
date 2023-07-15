package com.user.service.External.Services;

import com.user.service.Entities.Calificacion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name= "CALIFICACION-SERVICE")
public interface CalificacionService {

    @PostMapping("/api/v1/calificaciones")
    ResponseEntity<Calificacion> saveCalificacion(Calificacion calificacion);

    @PutMapping("/api/v1/calificaciones/{calificacionId}")
    ResponseEntity<Calificacion> updateCalificacion(@PathVariable String califacionId);

    @DeleteMapping("/api/v1/calificaciones/{calificacionId}")
    void deleteCalificacion(@PathVariable String calificacionId);
}
