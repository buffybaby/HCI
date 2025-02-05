package com.example.hci_project;

import java.util.ArrayList;
import java.util.List;

public class StudyCalendar {
    private List<String> studySchedule;

    public StudyCalendar() {
        this.studySchedule = new ArrayList<>();
    }

    public void addSession(String subject, String dateTime) {
        studySchedule.add(subject + " - " + dateTime);
    }

    public void displaySchedule() {
        System.out.println("\n--- Study Calendar ---");
        for (String session : studySchedule) {
            System.out.println(session);
        }
    }
}
