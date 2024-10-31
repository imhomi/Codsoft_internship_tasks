package package1;

import java.util.Scanner;

public class MarksCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Welcome to the Marks Calculator ");
        
        System.out.print("\nEnter Student's Name: ");
        String studentName = scanner.nextLine();

        System.out.print("Enter Student's ID: ");
        String studentID = scanner.nextLine();

        System.out.print("Enter the number of subjects: ");
        int numSubjects = scanner.nextInt();
        scanner.nextLine(); 

        String[] subjects = new String[numSubjects];
        int[] marks = new int[numSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("\nEnter subject " + (i + 1) + " name: ");
            subjects[i] = scanner.nextLine();
            System.out.print("Enter marks for " + subjects[i] + " (out of 100): ");
            marks[i] = scanner.nextInt();
            scanner.nextLine(); 
            totalMarks += marks[i];
        }

        double average = totalMarks / (double) numSubjects;
        String grade = (average >= 90) ? "A ⭐" : (average >= 80) ? "B " : (average >= 70) ? "C 👍" : (average >= 60) ? "D " : "F ❌";
        String feedback = switch (grade.trim()) {
            case "A" -> "🏆 Outstanding Performance!";
            case "B" -> "🎉 Great Work!";
            case "C" -> "😊 Keep Improving!";
            case "D" -> "📈 Needs Improvement!";
            default -> "💡 Try Harder Next Time!";
        };

        // Display Results
        System.out.println("\n---------------------------------------");
        System.out.println("         Student Report                  ");
        System.out.println("---------------------------------------");
        System.out.println("👤 Name        : " + studentName);
        System.out.println("🆔 Student ID  : " + studentID);
        System.out.println("📊 Subjects and Marks:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.println("   - " + subjects[i] + ": " + marks[i] + " marks");
        }
        System.out.println("\n🎯 Total Marks        : " + totalMarks);
        System.out.println("📈 Average Percentage : " + average + "%");
        System.out.println("🎓 Grade              : " + grade);
        System.out.println("💬 Feedback           : " + feedback);
        System.out.println("---------------------------------------");

        scanner.close();
    }
}
