package com.example.UI;

import com.example.hci_project.StudyTimer;
import com.example.hci_project.StudyAnalytics;
import com.example.hci_project.StudyCalendar;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class StudyPlannerUI extends Application {
    private StudyTimer timer;
    private StudyAnalytics analytics;
    private StudyCalendar calendar;
    private List<String> subjects;
    private Label timerLabel;
    private ChoiceBox<String> subjectChoice;
    private TextArea analyticsArea;
    private TextArea calendarArea;

    @Override
    public void start(Stage primaryStage) {
        subjects = new ArrayList<>();
        analytics = new StudyAnalytics();
        calendar = new StudyCalendar();
        timer = new StudyTimer(() -> timerLabel.setText("Elapsed Time: " + timer.getElapsedTimeInMinutes() + " min"));


        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Subjects");
        dialog.setHeaderText("Enter subjects (comma-separated)");
        dialog.setContentText("Subjects:");
        dialog.showAndWait().ifPresent(input -> {
            String[] subjectArray = input.split(",");
            for (String subject : subjectArray) {
                subjects.add(subject.trim());
            }
        });

        subjectChoice = new ChoiceBox<>();
        subjectChoice.getItems().addAll(subjects);
        subjectChoice.setValue(subjects.get(0));

        timerLabel = new Label("Elapsed Time: 0 min");

        Button startButton = new Button("Start Timer");
        startButton.setOnAction(e -> timer.startTimer());

        Button stopButton = new Button("Stop Timer");
        stopButton.setOnAction(e -> {
            timer.stopTimer();
            String selectedSubject = subjectChoice.getValue();
            analytics.recordStudySession(selectedSubject, timer.getElapsedTimeInMinutes());
            updateAnalytics();
        });

        Button scheduleButton = new Button("Add to Calendar");
        scheduleButton.setOnAction(e -> {
            TextInputDialog scheduleDialog = new TextInputDialog();
            scheduleDialog.setTitle("Schedule Study Session");
            scheduleDialog.setHeaderText("Enter date and time (e.g., 2025-02-10 14:00)");
            scheduleDialog.setContentText("Date & Time:");
            scheduleDialog.showAndWait().ifPresent(dateTime -> {
                calendar.addSession(subjectChoice.getValue(), dateTime);
                updateCalendar();
            });
        });

        analyticsArea = new TextArea();
        analyticsArea.setEditable(false);
        calendarArea = new TextArea();
        calendarArea.setEditable(false);

        VBox layout = new VBox(10, subjectChoice, timerLabel, startButton, stopButton, scheduleButton, 
                               new Label("Study Analytics"), analyticsArea, 
                               new Label("Study Calendar"), calendarArea);
        layout.setAlignment(Pos.CENTER);
        layout.setMinWidth(400);

        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setTitle("Study Planner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateAnalytics() {
        analyticsArea.setText(analytics.getAnalytics());
    }

    private void updateCalendar() {
        calendarArea.setText(calendar.getSchedule());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
