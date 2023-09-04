package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelManagementService {
    HotelManagementRepository r=new HotelManagementRepository();

    public String addHotel(Hotel hotel) {
        return r.addHotel(hotel);
    }

    public Integer addUser(User user) {
        return r.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        return r.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return r.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {
        return r.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return r.updateFacilities(newFacilities,hotelName);
    }
}
