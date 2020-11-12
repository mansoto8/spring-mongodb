package com.example.springmongodb.db;

import com.example.springmongodb.entities.Address;
import com.example.springmongodb.entities.Hotel;
import com.example.springmongodb.entities.Review;
import com.example.springmongodb.repositories.HotelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DbSeeder implements CommandLineRunner {

    private HotelRepository hotelRepository;

    public DbSeeder(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public void run(String... args) {
        Hotel marriot = new Hotel(
                "Marriot",
                130,
                new Address("Paris", "France"),
                Arrays.asList(
                        new Review("John", 8, false),
                        new Review("Mary", 7, true)
                )
        );
        Hotel ibis = new Hotel(
                "Ibis",
                90,
                new Address("Bucharest", "Romania"),
                Arrays.asList(
                        new Review("Teddy", 9, true)
                )
        );

        Hotel sofitel = new Hotel(
                "Sofitel",
                200,
                new Address("Rome", "Italy"),
                null
        );

        this.hotelRepository.deleteAll();

        List<Hotel> hotels = Arrays.asList(marriot, ibis, sofitel);
        this.hotelRepository.saveAll(hotels);

    };
}
