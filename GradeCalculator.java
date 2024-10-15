import java.util.Scanner;

public class GradeCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); 
        
        String[] studentNames = new String[numStudents];
        double[] examScores = new double[numStudents];
        double[] quizScores = new double[numStudents];

       
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter the name of student " + (i + 1) + ": ");
            studentNames[i] = scanner.nextLine();

            System.out.print("Enter the exam score:  " + (i + 1) + ": ");
            examScores[i] = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter the quiz score:" + (i + 1) + ": ");
            quizScores[i] = scanner.nextDouble();
            scanner.nextLine(); 
        }

        for (int i = 0; i < numStudents; i++) {
            double averageScore = (examScores[i] + quizScores[i]) / 2;
            char letterGrade = assignLetterGrade(averageScore);

            System.out.println("Student: " + studentNames[i]);
            System.out.println("Average Score: " + averageScore);
            System.out.println("Letter Grade: " + letterGrade);
            System.out.println("  ");
        }

        scanner.close();
    }

   
    private static char assignLetterGrade(double averageScore) {
        if (averageScore >= 95) {
            return 'A';
        } else if (averageScore >= 85) {
            return 'B';
        } else if (averageScore >= 75) {
            return 'C';
        } else if (averageScore >= 65) {
            return 'D';
        } else {
            return 'F';
        }
    }
}

