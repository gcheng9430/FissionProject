package com.example.fission;

import lombok.Data;

@Data
public class ViewAgent {
    public double[] color;
    public double[] position;
    public double size;

    public double[] getColor() {
        return color;
    }

    public void setColor(double[] color) {
        this.color = color;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] position) {
        this.position = position;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
