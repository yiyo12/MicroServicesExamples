package com.mcsv.hotel.Service;

import com.mcsv.hotel.Entity.Hotel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HotelService {

    Hotel saveHotel(Hotel hotel);
    List<Hotel> getAllHotels();

    Hotel getHotelById(String id);
}
