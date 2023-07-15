package com.user.service.Controllers;

import com.user.service.Entities.Calificacion;
import com.user.service.Entities.Usuario;
import com.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Usuario> saveUser(@RequestBody Usuario user){
        Usuario svUser = userService.save(user);
        return new ResponseEntity<>(svUser, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable String id){

        Usuario gtUser = userService.getUsuarioById(id);
        return new ResponseEntity<>(gtUser,HttpStatus.OK);

    }

     int countRetry = 1;
    @GetMapping("getAllDataUser/{id}")
   // @CircuitBreaker(name="myCircuitBreakerForNotUserFound", fallbackMethod = "fallbackNotUserFound")
    @Retry(name="myRetry" , fallbackMethod = "fallbackNotUserFound")
    public ResponseEntity<Usuario> getAllDataUserWithFeing(@PathVariable String id){
        log.info("listar la informacion completa del usuario");
        log.info("reintentos " , countRetry);
        countRetry ++;
        Usuario gtUser = userService.getAllDataUserWithOpenFeing(id);
        return new ResponseEntity<>(gtUser,HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Usuario>> getAllUsers(){
        List<Usuario> gtUser = userService.getAllUsers();
        return new ResponseEntity<>(gtUser,HttpStatus.OK);

    }

    @PostMapping("/saveCalificasionUsingUserService")
    public HttpStatus saveUser(@RequestBody Calificacion calificacion){
        ResponseEntity<Calificacion> user = userService.saveCalificacion(calificacion);
        return HttpStatus.CREATED;
    }

    @DeleteMapping("/calificaciones/{calificacionId}")
    public HttpStatus deleteCalificacion(@PathVariable String calificacionId){
         userService.deleteCalificacion(calificacionId);
        return HttpStatus.OK;
    }

    public ResponseEntity<Usuario> fallbackNotUserFound(String userId, Exception exp){
    log.info("Usuario no encontrado algun servicio esta caido entra: Fallback", exp.getMessage());
    Usuario user = Usuario.builder()
            .usuarioId("123")
            .email("test@test.com")
            .nombre("Usuario test")
            .build();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
