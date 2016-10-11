package server.home.service;

import server.home.model.House;

/**
 * Created by default on 10/10/16.
 */
public class HouseService {

    private House house;

    public HouseService(House house) {
        this.house = house;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}
