package com.mcsvcalificacion.Controller;

import com.mcsvcalificacion.Entity.Calificacion;
import com.mcsvcalificacion.Services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calificaciones")
public class CalificacionController {

    @Autowired
    private CalificacionService calificacionService;

    @PostMapping
    public ResponseEntity<Calificacion> saveCalificacion(@RequestBody Calificacion calificacion){

        return ResponseEntity.status(HttpStatus.CREATED).body(calificacionService.saveCalificacion(calificacion));

    }


    @GetMapping("/getAll")
    public ResponseEntity<List<Calificacion>> getAllCalificaciones(){
        return ResponseEntity.ok(calificacionService.getAll());
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Calificacion>> getAllCalificacionesByUserId(@PathVariable String userId){
        return ResponseEntity.ok(calificacionService.getCalificacionesByUserID(userId));
    }

    @GetMapping("/hotels/{hotelsId}")
    public ResponseEntity<List<Calificacion>> getAllCalificacionesByHotelId(@PathVariable String hotelsId){
        return ResponseEntity.ok(calificacionService.getCalificacionByHotelId(hotelsId));
    }


    @DeleteMapping("{calificacionId}")
    public HttpStatus deleteCalificacion(@PathVariable String calificacionId){
        calificacionService.deleteCalificacion(calificacionId);
        return HttpStatus.OK;
    }


}
