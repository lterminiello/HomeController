package server.home.model;

import java.util.List;
import java.util.Optional;

/**
 * Created by default on 10/10/16.
 */
public class House {

    private List<Room> rooms;

    public House(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Room getRoom(String name){
        Optional<Room> optional = this.rooms.stream().filter(x -> x.getName().equals(name)).findFirst();
        return optional.isPresent()?optional.get():null;
    }
}
