package com.mcsvcalificacion.Services.CalificacionServiceImp;

import com.mcsvcalificacion.Entity.Calificacion;
import com.mcsvcalificacion.Repository.CalificacionRepository;
import com.mcsvcalificacion.Services.CalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionServiceImp implements CalificacionService {
    @Autowired
    private CalificacionRepository calificacionRepository;
    @Override
    public List<Calificacion> getAll() {

        return calificacionRepository.findAll();
    }

    @Override
    public List<Calificacion> getCalificacionesByUserID(String userId) {

        return calificacionRepository.findByUserId(userId);
    }

    @Override
    public Calificacion saveCalificacion(Calificacion calificacion) {

        return  calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion>  getCalificacionByHotelId(String hotelId) {
        return  calificacionRepository.findByHotelId(hotelId);

    }

    @Override
    public void deleteCalificacion(String calificacionId) {
       calificacionRepository.deleteById(calificacionId);

    }
}
