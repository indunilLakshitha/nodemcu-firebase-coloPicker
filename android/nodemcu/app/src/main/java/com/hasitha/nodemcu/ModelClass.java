package com.hasitha.nodemcu;

public class ModelClass {

    int ID;
    String Name,Red,Green,Blue;

    public ModelClass(int ID, String name, String red, String green, String blue) {
        this.ID = ID;
        Name = name;
        Red = red;
        Green = green;
        Blue = blue;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRed() {
        return Red;
    }

    public void setRed(String red) {
        Red = red;
    }

    public String getGreen() {
        return Green;
    }

    public void setGreen(String green) {
        Green = green;
    }

    public String getBlue() {
        return Blue;
    }

    public void setBlue(String blue) {
        Blue = blue;
    }
}
