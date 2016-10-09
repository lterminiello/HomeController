package server.home.model;

import com.pi4j.io.gpio.RaspiPin;
import server.home.controller.board.type.ControllerInterface;
import server.home.utils.ControllerFactory;

/**
 * Created by default on 08/10/16.
 */
public class Artifact {

    private TypeArtifact typeArtifact;
    private ControllerInterface controller;
    private String name;


    public Artifact(TypeArtifact typeArtifact, String idBoard, RaspiPin pin, String name) {
        this.typeArtifact = typeArtifact;
        this.controller = ControllerFactory.getController(typeArtifact,pin,idBoard);
        this.name = name;
    }
}
