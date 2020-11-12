package com.example.springmongodb.controller;

import com.example.springmongodb.entities.Hotel;
import com.example.springmongodb.entities.QHotel;
import com.example.springmongodb.repositories.HotelRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private HotelRepository hotelRepository;

    public HotelController(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @GetMapping("/all")
    public List<Hotel> getAll() {
        List<Hotel> hotels = this.hotelRepository.findAll();

        return hotels;
    }

    @PostMapping
    public void insert(@RequestBody Hotel hotel) {
        this.hotelRepository.save(hotel);
    }

    @PutMapping
    public void update(@RequestBody Hotel hotel) {
        this.hotelRepository.insert(hotel);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.hotelRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Hotel getById(@PathVariable("id") String id) {
        Hotel hotel = this.hotelRepository.findById(id).get();

        return hotel;
    }

    @GetMapping("/price/{maxPrice}")
    public List<Hotel> getByPricePerNight(@PathVariable("maxPrice") int maxPrice) {
        List<Hotel> hotels = this.hotelRepository.findByPricePerNightLessThan(maxPrice);

        return hotels;
    }

    @GetMapping("/city/{city}")
    public List<Hotel> getByCity(@PathVariable("city") String city) {
        List<Hotel> hotels = this.hotelRepository.findByCity(city);

        return hotels;
    }

    @GetMapping("/country/{country}")
    public List<Hotel> getByCountry(@PathVariable("country") String country) {
        //Create a queryclass
        QHotel qHotel = new QHotel("hotel");

        //Using the query class we can create the filters
        BooleanExpression filterByCountry = qHotel.address.country.eq(country);

        //We can add the filters to the find all method
        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByCountry);

        return hotels;
    }

    @GetMapping("/recommended")
    public List<Hotel> getRecommended() {
        final int maxPrice = 100;
        final int minRating = 7;

        QHotel qHotel = new QHotel("hotel");

        BooleanExpression filterByPrice = qHotel.pricePerNight.lt(maxPrice);
        BooleanExpression filterByRating = qHotel.reviews.any().rating.gt(minRating);

        List<Hotel> hotels = (List<Hotel>) this.hotelRepository.findAll(filterByPrice.and(filterByRating));

        return hotels;
    }

}
