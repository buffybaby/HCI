package com.example.hci_project;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class StudyTimer {

    private long startTime;
    private long elapsedTime;
    private boolean running;
    private Timeline timeline;
    private Runnable updateUI;

    public StudyTimer(Runnable updateUI) {
        this.updateUI = updateUI;
        this.timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateUI.run()));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void startTimer() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
            timeline.play();
        }
    }

    public void stopTimer() {
        if (running) {
            elapsedTime += System.currentTimeMillis() - startTime;
            running = false;
            timeline.stop();
        }
    }

    public long getElapsedTimeInMinutes() {
        return elapsedTime / (1000 * 60);
    }
}
