package server.home.model;

import com.pi4j.io.gpio.RaspiPin;
import com.sun.istack.internal.Interned;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;
import server.home.controller.board.type.ControllerInterface;
import server.home.utils.ControllerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by default on 08/10/16.
 */
public class Artifact {

    private TypeArtifact typeArtifact;
    private ControllerInterface controller;
    private String name;
    private Class paramPower[] = {Integer.TYPE};
    private Class noParam[] = {};

    public Artifact(TypeArtifact typeArtifact, ControllerInterface controller, String name, Class[] paramPower, Class[] noParam) {
        this.typeArtifact = typeArtifact;
        this.controller = controller;
        this.name = name;
        this.paramPower = paramPower;
        this.noParam = noParam;
    }

    public Artifact(TypeArtifact typeArtifact, String idBoard, RaspiPin pin, String name) {
        this.typeArtifact = typeArtifact;
        this.controller = ControllerFactory.getController(typeArtifact, pin, idBoard);
        this.name = name;
    }

    public String runAction(@NotNull String action, @Nullable Integer pwd) {
        Method method;
        String response =null;
        try {
            if (pwd == null) {
                method = controller.getClass().getDeclaredMethod(action, noParam);
            } else {
                method = controller.getClass().getDeclaredMethod(action, paramPower);
            }
            response = (String)method.invoke(controller,pwd);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return response;
    }

    public TypeArtifact getTypeArtifact() {
        return typeArtifact;
    }

    public void setTypeArtifact(TypeArtifact typeArtifact) {
        this.typeArtifact = typeArtifact;
    }

    public ControllerInterface getController() {
        return controller;
    }

    public void setController(ControllerInterface controller) {
        this.controller = controller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class[] getParamPower() {
        return paramPower;
    }

    public void setParamPower(Class[] paramPower) {
        this.paramPower = paramPower;
    }

    public Class[] getNoParam() {
        return noParam;
    }

    public void setNoParam(Class[] noParam) {
        this.noParam = noParam;
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Artifact)obj).getName());
    }
}
