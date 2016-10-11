package server.home.model;

import java.util.List;
import java.util.Optional;

/**
 * Created by default on 10/10/16.
 */
public class Room {

    private List<Artifact> artifacts;
    private String name;

    public Room(List<Artifact> artifacts, String name) {
        this.artifacts = artifacts;
        this.name = name;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artifact getArtifact(String name){
        Optional<Artifact> optional = this.artifacts.stream().filter(x -> x.getName().equals(name)).findFirst();
        return optional.isPresent()?optional.get():null;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Room)obj).getName());
    }
}
