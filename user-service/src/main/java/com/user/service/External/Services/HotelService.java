package com.user.service.External.Services;

import com.user.service.Entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("/api/v1/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
