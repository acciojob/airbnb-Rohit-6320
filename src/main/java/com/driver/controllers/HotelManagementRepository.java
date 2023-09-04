package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class HotelManagementRepository {

    HashMap<String, Hotel> hotelDB=new HashMap<>();

    HashMap<Integer, User> userDB=new HashMap<>();

    HashMap<String, Booking> bookingDB=new HashMap<>();

    HashMap<Integer, Integer> userBookingDB=new HashMap<>();

    public String addHotel(Hotel hotel) {
        if(hotel==null || hotel.getHotelName()==null)
        {
            return "FAILURE";
        }

        if(hotelDB.containsKey(hotel.getHotelName()))
        {
            return "FAILURE";
        }

        // Add
        hotelDB.put(hotel.getHotelName(), hotel);
        return "SUCCESS";
    }


    public Integer addUser(User user) {
        userDB.put(user.getaadharCardNo(), user);
        return user.getaadharCardNo();
    }

    public String getHotelWithMostFacilities() {
        int max=0;
        String ans="";
        for(String HotelName: hotelDB.keySet())
        {
            Hotel currHotel=hotelDB.get(HotelName);
            if(max==currHotel.getFacilities().size())
            {
                int a=ans.compareTo(HotelName);
                if(a>0)
                {
                    ans=HotelName;
                }
            }

            if(max<currHotel.getFacilities().size())
            {
                max=currHotel.getFacilities().size();
                ans=HotelName;
            }
        }
        return ans;
    }

    public int bookARoom(Booking booking) {
        String id= UUID.randomUUID().toString();
        booking.setBookingId(id);
        // Add
        bookingDB.put(id, booking);

        Hotel hotel=hotelDB.get(booking.getHotelName());

        if(hotel.getAvailableRooms()<booking.getNoOfRooms())
        {
            return -1;
        }

        hotel.setAvailableRooms(hotel.getAvailableRooms()-booking.getNoOfRooms());
        return booking.getNoOfRooms()*hotel.getPricePerNight();
    }

    public int getBookings(Integer aadharCard) {
        int ans=0;
        for(String id: bookingDB.keySet())
        {
            if(bookingDB.get(id).getBookingAadharCard()==aadharCard)
            {
                ans+=bookingDB.get(id).getNoOfRooms();
            }
        }
        return ans;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        List<Facility> old=hotelDB.get(hotelName).getFacilities();

        for(Facility f: newFacilities)
        {
            if(!old.contains(f))
            {
                old.add(f);
            }
        }
        hotelDB.get(hotelName).setFacilities(old);
        return hotelDB.get(hotelName);
    }
}
