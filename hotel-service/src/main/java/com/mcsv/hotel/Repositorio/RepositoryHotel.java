package com.mcsv.hotel.Repositorio;

import com.mcsv.hotel.Entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryHotel extends JpaRepository<Hotel, String> {
}
