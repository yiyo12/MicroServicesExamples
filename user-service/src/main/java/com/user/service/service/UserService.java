package com.user.service.service;

import com.user.service.Entities.Calificacion;
import com.user.service.Entities.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public interface UserService {

    Usuario save(Usuario usuario);
    List<Usuario> getAllUsers();

    Usuario getUsuarioById(String id);

    Usuario getAllDataUserWithOpenFeing(String id);

    ResponseEntity<Calificacion> updateCalificacion(@PathVariable String califacionId);

    void deleteCalificacion(@PathVariable String califacionId);


    ResponseEntity<Calificacion> saveCalificacion(Calificacion calificacion);
}
