package com.mcsv.hotel.Service.HotelServiceImpl;

import com.mcsv.hotel.Entity.Hotel;
import com.mcsv.hotel.Exceptions.ResourceNotFoundExceptions;
import com.mcsv.hotel.Repositorio.RepositoryHotel;
import com.mcsv.hotel.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImp implements HotelService {

    @Autowired
    RepositoryHotel repositoryHotel;

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomUUD = UUID.randomUUID().toString();
        hotel.setHotelId(randomUUD);
        return repositoryHotel.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return repositoryHotel.findAll();
    }

    @Override
    public Hotel getHotelById(String id) {
        return repositoryHotel.findById(id).orElseThrow(() -> new ResourceNotFoundExceptions("Hotel no encontrado"));
    }
}
