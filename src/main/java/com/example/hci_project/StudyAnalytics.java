package com.example.hci_project;

import java.util.HashMap;
import java.util.Map;

public class StudyAnalytics {

    private Map<String, Long> studyRecords;

    public StudyAnalytics() {
        studyRecords = new HashMap<>();
    }

    public void recordStudySession(String subject, long minutes) {
        studyRecords.put(subject, studyRecords.getOrDefault(subject, 0L) + minutes);
    }

    public void displayAnalytics() {
        System.out.println("\n--- Study Analytics ---");
        for (Map.Entry<String, Long> entry : studyRecords.entrySet()) {
            System.out.println("Subject: " + entry.getKey() + " | Total Time: " + entry.getValue() + " minutes");
        }
    }

}
