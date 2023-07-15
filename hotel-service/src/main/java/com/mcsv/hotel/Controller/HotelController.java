package com.mcsv.hotel.Controller;

import com.mcsv.hotel.Entity.Hotel;
import com.mcsv.hotel.Service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;


    @PostMapping("/save")
    public ResponseEntity<Hotel> saveUser(@RequestBody Hotel user){
        Hotel svHotel = hotelService.saveHotel(user);
        return new ResponseEntity<>(svHotel, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable String id){

        Hotel gtUser = hotelService.getHotelById(id);
        return new ResponseEntity<>(gtUser,HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Hotel>> getAllUsers(){
        List<Hotel> gtUser = hotelService.getAllHotels();
        return new ResponseEntity<>(gtUser,HttpStatus.OK);

    }

}
