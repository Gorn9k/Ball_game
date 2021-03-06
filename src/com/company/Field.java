package com.company;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Field extends JPanel {
    private boolean paused;
    private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
    private ArrayList<BigBall> big_balls = new ArrayList<BigBall>(1);
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            repaint();
        }
    });

    public Field() {
        setBackground(Color.WHITE);
        repaintTimer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D) g;
        for (BouncingBall ball : balls) {
            ball.paint(canvas);
        }
        if(!big_balls.isEmpty()) {
            big_balls.get(0).paintBigBall(canvas);
        }
    }

    public void addBall() {
        balls.add(new BouncingBall(this));
    }

    public void addBigBall() { big_balls.add(new BigBall(this)); }

    public void deleteBigBall(){
        big_balls.remove(0);
    }

    public synchronized void pause() {
        paused = true;
    }

    public synchronized void resume() {
        paused = false;
        notifyAll();
    }

    public synchronized void canMove(BouncingBall ball) throws InterruptedException {
        if (paused) {
            wait();
        }
    }

    public synchronized void canMove(BigBall ball) throws InterruptedException {
        if (paused) {
            wait();
        }
    }

    public ArrayList<BigBall> getList(){
        return this.big_balls;
    }
}

