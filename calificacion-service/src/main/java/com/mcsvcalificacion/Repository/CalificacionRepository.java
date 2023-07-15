package com.mcsvcalificacion.Repository;

import com.mcsvcalificacion.Entity.Calificacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends MongoRepository<Calificacion, String> {

    //Metodos para extrare

    List<Calificacion> findByUserId(String userId);
    List<Calificacion> findByHotelId(String hotelId);

}
