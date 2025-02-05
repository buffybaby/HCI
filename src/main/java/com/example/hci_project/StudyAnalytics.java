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

    // ✅ Fix: Add this method
    public String getAnalytics() {
        if (studyRecords.isEmpty()) {
            return "No study data available.";
        }

        StringBuilder result = new StringBuilder("Study Analytics:\n");
        for (Map.Entry<String, Long> entry : studyRecords.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(" min\n");
        }
        return result.toString();
    }
}
