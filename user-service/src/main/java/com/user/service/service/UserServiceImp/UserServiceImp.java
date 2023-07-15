package com.user.service.service.UserServiceImp;

import com.user.service.Entities.Calificacion;
import com.user.service.Entities.Hotel;
import com.user.service.External.Services.CalificacionService;
import com.user.service.External.Services.HotelService;
import com.user.service.Repositorio.UsuarioRepositorio;
import com.user.service.Exceptions.ResourceNotFounfExp;
import com.user.service.Entities.Usuario;
import com.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UsuarioRepositorio userRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private CalificacionService calificacionService;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserServiceImp() {
    }

    @Override
    public Usuario save(Usuario usuario) {
        String randomUUD = UUID.randomUUID().toString();
        usuario.setUsuarioId(randomUUD);
        return userRepo.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public Usuario getUsuarioById(String id) {
        Usuario usr =  userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFounfExp("Usuario no encontrado con ID ${}" + id));
        // Nos comunicamos por rest tamplate con el servicio de calificaciones
        // y le especificamos que tipo de clase en este caso este end point devuelve un arrrayList
       /* Calificacion[] calificacionByUserId = restTemplate
                .getForObject("http://localhost:8083/api/v1/calificaciones/users/"+id, Calificacion[].class);
        logger.info("{}", calificacionByUserId);
        List<Calificacion> cl = Arrays.stream(calificacionByUserId).map(calificacion -> {
            Hotel hotel = restTemplate.
                    getForObject("http://localhost:8082/api/v1/hotels/"+calificacion.getHotelId(), Hotel.class);
            calificacion.setHotel(hotel);
            return calificacion;
        }).collect(Collectors.toList());*/
        // Cuando trabajamos con load balancer no es necesario especificar el puerto ya que no buscamos puertos especificos
        // buscamos por medio del nombre del servicio para poder hacer un Round Robin
        Calificacion[] calificacionByUserId = restTemplate
                .getForObject("http://CALIFICACION-SERVICE/api/v1/calificaciones/users/"+id, Calificacion[].class);
        logger.info("{}", calificacionByUserId);
        List<Calificacion> cl = Arrays.stream(calificacionByUserId).map(calificacion -> {
            Hotel hotel = restTemplate.
                    getForObject("http://HOTEL-SERVICE/api/v1/hotels/"+calificacion.getHotelId(), Hotel.class);
            calificacion.setHotel(hotel);
            return calificacion;
        }).collect(Collectors.toList());

        logger.info("{}", calificacionByUserId);
        usr.setUsuarioCalificaciones(cl);
        return usr;
    }

    @Override
    public Usuario getAllDataUserWithOpenFeing(String id) {
        Usuario usr =  userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFounfExp("Usuario no encontrado con ID ${}" + id));
        Calificacion[] calificacionByUserId = restTemplate
                .getForObject("http://CALIFICACION-SERVICE/api/v1/calificaciones/users/"+usr.getUsuarioId(), Calificacion[].class);
        logger.info("{}", calificacionByUserId);
        List<Calificacion> cl = Arrays.stream(calificacionByUserId).map(calificacion -> {
          /*  Hotel hotel = restTemplate.
                    getForObject("http://HOTEL-SERVICE/api/v1/hotels/"+calificacion.getHotelId(), Hotel.class);*/
            /***En lugar de usar rest template usamos el servicio Feing**/

            Hotel hotel = hotelService.getHotel(calificacion.getHotelId());
            calificacion.setHotel(hotel);
            return calificacion;
        }).collect(Collectors.toList());

        logger.info("{}", calificacionByUserId);
        usr.setUsuarioCalificaciones(cl);
        return usr;
    }

    @Override
    public ResponseEntity<Calificacion> updateCalificacion(String califacionId) {
        return null;
    }

    @Override
    public void deleteCalificacion(String califacionId) {
        calificacionService.deleteCalificacion(califacionId);;
    }

    @Override
    public ResponseEntity<Calificacion> saveCalificacion(Calificacion calificacion) {
       Calificacion cal = calificacionService.saveCalificacion(calificacion).getBody();

        ResponseEntity rp = new ResponseEntity<Calificacion>(cal, HttpStatus.CREATED);
         return rp;
    }


}
