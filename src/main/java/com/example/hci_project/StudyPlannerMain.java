package com.example.hci_project;

import java.util.Scanner;

public class StudyPlannerMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudyTimer timer = new StudyTimer();
        StudyAnalytics analytics = new StudyAnalytics();
        StudyCalendar calendar = new StudyCalendar();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); 

        String[] subjects = new String[numSubjects];
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter subject " + (i + 1) + ": ");
            subjects[i] = scanner.nextLine();
        }

        System.out.println("\nStarting Study Planner...");
        
        boolean running = true;
        while (running) {
            System.out.println("\nChoose an option:\n1. Start Study Timer\n2. Stop Study Timer\n3. Add Study Session to Calendar\n4. View Analytics\n5. View Calendar\n6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    timer.startTimer();
                    break;
                case 2:
                    timer.stopTimer();
                    System.out.print("Enter the subject you studied: ");
                    String subject = scanner.nextLine();
                    analytics.recordStudySession(subject, timer.getElapsedTimeInMinutes());
                    break;
                case 3:
                    System.out.print("Enter subject: ");
                    String calendarSubject = scanner.nextLine();
                    System.out.print("Enter date and time (e.g., 2025-02-10 14:00): ");
                    String dateTime = scanner.nextLine();
                    calendar.addSession(calendarSubject, dateTime);
                    break;
                case 4:
                    analytics.displayAnalytics();
                    break;
                case 5:
                    calendar.displaySchedule();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting Study Planner...");
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }

        scanner.close();
    }
}
