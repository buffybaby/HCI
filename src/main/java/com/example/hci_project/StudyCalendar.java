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

    // ✅ Fix: Add this method
    public String getSchedule() {
        if (studySchedule.isEmpty()) {
            return "No study sessions scheduled.";
        }

        StringBuilder result = new StringBuilder("Study Calendar:\n");
        for (String session : studySchedule) {
            result.append(session).append("\n");
        }
        return result.toString();
    }
}
