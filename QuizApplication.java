package package1;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

class Quiz {
    private ArrayList<Question> questions;
    private int score;
    private ArrayList<Boolean> results;

    public Quiz(ArrayList<Question> questions) {
        this.questions = questions;
        this.score = 0;
        this.results = new ArrayList<>();
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestionText());
            String[] options = question.getOptions();
            for (int j = 0; j < options.length; j++) {
                System.out.println((j + 1) + ". " + options[j]);
            }

            boolean answered = displayQuestionWithTimer(scanner, question);
            if (!answered) {
                System.out.println("Time's up! Moving to the next question.");
                results.add(false);
            }
        }

        displayResults();
        scanner.close();
    }

    private boolean displayQuestionWithTimer(Scanner scanner, Question question) {
        Timer timer = new Timer();
        boolean[] answered = {false};

        timer.schedule(new TimerTask() {
            public void run() {
                if (!answered[0]) {
                    System.out.println("Time is up for this question.");
                }
                answered[0] = true;
                timer.cancel();
            }
        }, 10000); // 10 seconds timer per question

        while (!answered[0]) {
            System.out.print("Enter your answer (1-4): ");
            int answer = scanner.nextInt();
            if (answer >= 1 && answer <= 4) {
                if (answer - 1 == question.getCorrectAnswerIndex()) {
                    System.out.println("Correct!");
                    score++;
                    results.add(true);
                } else {
                    System.out.println("Incorrect!");
                    results.add(false);
                }
                answered[0] = true;
                timer.cancel();
                return true;
            } else {
                System.out.println("Invalid input, please select a number between 1 and 4.");
            }
        }
        return false;
    }

    private void displayResults() {
        System.out.println("\n--- Quiz Results ---");
        System.out.println("Final Score: " + score + "/" + questions.size());

        System.out.println("\nSummary:");
        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String result = results.get(i) ? "Correct" : "Incorrect";
            System.out.println("Question " + (i + 1) + ": " + result);
        }
    }
}

public class QuizApplication {
    public static void main(String[] args) {
        ArrayList<Question> questions = new ArrayList<>();
        
        questions.add(new Question("What is the capital of France?", new String[]{"Paris", "London", "Rome", "Berlin"}, 0));
        questions.add(new Question("What is 5 + 7?", new String[]{"10", "11", "12", "13"}, 2));
        questions.add(new Question("Which language is used for Android development?", new String[]{"Java", "Python", "JavaScript", "C++"}, 0));
        
        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
