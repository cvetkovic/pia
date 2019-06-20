package pia.controllers;

import java.io.Serializable;
import javax.inject.Named;

@Named(value = "navigatorController")
public class navigatorController implements Serializable {

    private String navigatorPath = "navigator_notlogged.xhtml";
    private String leftPath = "";
    private String rightPath = "";

    public String getNavigatorPath() {
        return navigatorPath;
    }

    public void setNavigatorPath(String navigatorPath) {
        this.navigatorPath = navigatorPath;
    }

    public String getLeftPath() {
        return leftPath;
    }

    public void setLeftPath(String leftPath) {
        this.leftPath = leftPath;
    }

    public String getRightPath() {
        return rightPath;
    }

    public void setRightPath(String rightPath) {
        this.rightPath = rightPath;
    }

}
