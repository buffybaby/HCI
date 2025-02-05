package com.example.hci_project;

import java.util.Timer;
import java.util.TimerTask;

public class StudyTimer {

    private Timer timer;
    private long startTime;
    private long elapsedTime;

    public StudyTimer() {
        this.timer = new Timer();
        this.elapsedTime = 0;
    }

    public void startTimer() {
        startTime = System.currentTimeMillis();
        System.out.println("Study session started!");
    }

    public void stopTimer() {
        if (startTime > 0) {
            elapsedTime += System.currentTimeMillis() - startTime;
            System.out.println("Study session stopped! Total time: " + getElapsedTimeInMinutes() + " minutes.");
        }
    }

    public long getElapsedTimeInMinutes() {
        return elapsedTime / (1000 * 60);
    }
}
