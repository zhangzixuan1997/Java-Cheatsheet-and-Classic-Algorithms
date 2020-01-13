//McGill Assignment 4
//Student Name: Sean Zhang
//Student Number : 260873386
import java.util.Arrays;
public class ExamGrading {
    public static void main(String[] args) {
        char[][] responses = {{'C', 'A', 'B', 'B', 'C', 'A'},
                {'A', 'A', 'B', 'B', 'B', 'B'},
                {'C', 'B', 'A', 'B', 'C', 'A'},
                {'A', 'B', 'A', 'B', 'B', 'B'}};
        char[] solutions = {'C', 'A', 'B', 'B', 'C', 'C'};
        double[] grades = gradeAllStudents(responses, solutions);
        System.out.println(Arrays.toString(grades));
        int numWrongSimilar = numWrongSimilar(responses[1], responses[3], solutions);
        System.out.println(numWrongSimilar);
        int numMatches = numMatches(responses, solutions, 0, 1);
        System.out.println(numMatches);
        int[][] findSimilarAnswers = findSimilarAnswers(responses, solutions, 1);
        System.out.println(Arrays.deepToString(findSimilarAnswers));
    }

    public static double[] gradeAllStudents(char[][] responses, char[] solutions) {
        //1st int means how many students
        //2nd int means how many responses for each student
        int numberOfStudents = responses.length;
        int numberOfQuestions = solutions.length;
        int[][] correctQuestions = new int[numberOfStudents][numberOfQuestions];
        for (int i = 0; i < numberOfStudents; i++) {
            for (int j = 0; j < numberOfQuestions; j++) {
                if (responses[i][j] == solutions[j]) {
                    correctQuestions[i][j] = 1;
                } else {
                    correctQuestions[i][j] = 0;
                }
            }
        }
        double[] grades = new double[numberOfStudents];
        for (int i = 0; i < numberOfStudents; i++) {
            double sum = 0;
            for (int j = 0; j < numberOfQuestions; j++) {
                sum += correctQuestions[i][j];
            }
            //representing the grades for each student as percentages.
            grades[i] = (sum / numberOfQuestions * 100);
        }
        return grades;
    }

    //This method returns the number of wrong answers that the students had the same.
    public static int numWrongSimilar(char[] responseOne, char[] responseTwo, char[] solutions) {
        if (responseOne.length == responseTwo.length && responseOne.length == solutions.length) {
            int[] sameWrongAnswer = new int[solutions.length];
            for (int i = 0; i < solutions.length; i++) {
                if ((responseOne[i] == responseTwo[i]) && (responseOne[i] != solutions[i])) {
                    sameWrongAnswer[i] = 1;
                } else {
                    sameWrongAnswer[i] = 0;
                }
            }
            int sum = 0;
            for (int i = 0; i < sameWrongAnswer.length; i++) {
                sum += sameWrongAnswer[i];
            }
            return sum;
        } else {
            throw new IllegalArgumentException("Students have responses that diﬀer in length from each-other, or from the solutions.");
        }

    }

    //The method returns the number of students whose wrong answers are too similar to those of student index.
    public static int numMatches(char[][] responses, char[] solutions, int index, int similarityThreshold) {
        int count = 0;
        for (int i = 0; i < responses.length; i++) {
            if (numWrongSimilar(responses[i], responses[index], solutions) >= similarityThreshold) {
                count++;
            }
        }
        if (numWrongSimilar(responses[index], responses[index], solutions) >= similarityThreshold) {
            count--;
        }
        return count;
    }

    /*This method returns an int[][] where each index represents a student, and each sub-array lists
    the indices of all students who have similar wrong answers to the student at that index.*/

    public static int[][] findSimilarAnswers(char[][] responses, char[] solutions, int similarityThreshold) {
        int[][] similarAnswers = new int[responses.length][];
        for (int i = 0; i < responses.length; i++) {
            int[] index = new int[numMatches(responses, solutions, i, similarityThreshold)];
            int num = 0;
            for (int j = 0; j < responses.length; j++) {
                if(j==i){
                    // to exclude the students themselves.
                    continue;
                }
                if (numWrongSimilar(responses[i], responses[j], solutions) >= similarityThreshold) {
                    index[num]=j;
                    num++;
                }
            }
            similarAnswers[i] = index;
        }
        return similarAnswers;
    }
}
