package com.company;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class BigBall implements Runnable {
    private final int SPEED = 20;
    private Field field;
    private int radius;
    private Color color;
    private double x;
    private double y;
    private double speedX;
    private double speedY;

    public BigBall(Field field) {
        this.field = field;
        radius = 100;
        double angle = Math.random() * 2 * Math.PI;
        speedX = 3 * Math.cos(angle);
        speedY = 3 * Math.sin(angle);
        color = new Color((float) Math.random(), (float) Math.random(), (float) Math.random(), 0.4f);
        x = Math.random() * (field.getSize().getWidth() - 2 * radius) + radius;
        y = Math.random() * (field.getSize().getHeight() - 2 * radius) + radius;
        Thread thisThread = new Thread(this);
        thisThread.start();
    }
    @Override
    public void run() {
        try {
            while (true) {
                field.canMove(this);
                if (x + speedX <= radius) {
                    speedX = -speedX;
                    x = radius;
                } else if (x + speedX >= field.getWidth() - radius) {
                    speedX = -speedX;
                    x = new Double(field.getWidth() - radius).intValue();
                } else if (y + speedY <= radius) {
                    speedY = -speedY;
                    y = radius;
                } else if (y + speedY >= field.getHeight() - radius) {
                    speedY = -speedY;
                    y = new Double(field.getHeight() - radius).intValue();
                } else {
                    x += speedX;
                    y += speedY;
                } Thread.sleep(SPEED);
            }
        } catch (InterruptedException ex) {
        }
    }

    public void paintBigBall(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.setPaint(color);
        Ellipse2D.Double big_ball = new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
        canvas.draw(big_ball);
        canvas.fill(big_ball);
    }

    public int getRadius(){
        return radius;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getSpeedX(){ return speedX; }
    public double getSpeedY(){ return speedY; }
}
