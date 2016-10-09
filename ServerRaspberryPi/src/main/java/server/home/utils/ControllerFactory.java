package server.home.utils;

import com.pi4j.io.gpio.RaspiPin;
import server.home.controller.board.type.ControllerInterface;
import server.home.model.TypeArtifact;
/**
 * Created by default on 08/10/16.
 */
public class ControllerFactory {

    public static ControllerInterface getController(TypeArtifact typeArtifact, RaspiPin pin , String idBoard){
        ControllerInterface controllerInterface = null;
        switch (typeArtifact){
            case LIGHT:
                break;
            case DIMMER:
                break;
            case AIR_CONDITIONER:
                break;
            case TV:
                break;
            case BLIND:
                break;
            case OTHER:
                return null;
        }
        return controllerInterface;
    }
}
