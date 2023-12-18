import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class GradeAnalyzer {

    public static void main(String[] args) {
      
        List<Integer> sampleGrades = generateSampleData();

       
        double[] statisticsResult = calculateStatistics(sampleGrades);

        int[] passResults = categorizeStudents(sampleGrades);

        printResults(statisticsResult, passResults);
    }

    private static List<Integer> generateSampleData() {
       
        List<Integer> grades = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            grades.add(random.nextInt(11));
        }

        return grades;
    }

    private static double[] calculateStatistics(List<Integer> grades) {
        // Calculate statistics for a list of grades
        double mean = grades.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
        Collections.sort(grades);
        double median = grades.get(grades.size() / 2);
        double medianGrouped = calculateMedianGrouped(grades);
        double stdDev = calculateStandardDeviation(grades, mean);
        double variance = calculateVariance(grades, mean);

        return new double[]{mean, median, medianGrouped, stdDev, variance};
    }

    private static double calculateMedianGrouped(List<Integer> grades) {
        
        return grades.get(grades.size() / 2);
    }

    private static double calculateStandardDeviation(List<Integer> grades, double mean) {
       
        return Math.sqrt(grades.stream().mapToDouble(value -> Math.pow(value - mean, 2)).sum() / grades.size());
    }

    private static double calculateVariance(List<Integer> grades, double mean) {
       
        return grades.stream().mapToDouble(value -> Math.pow(value - mean, 2)).sum() / grades.size();
    }

    private static int[] categorizeStudents(List<Integer> grades) {
     
        long passedStudents = grades.stream().filter(grade -> grade >= 5).count();
        long nextSemesterStudents = grades.size() - passedStudents;

        double passRate = (passedStudents / (double) grades.size()) * 100.0;

        return new int[]{(int) passedStudents, (int) nextSemesterStudents, (int) passRate};
    }

    private static void printResults(double[] statisticsResult, int[] passResults) {
     
        System.out.println("Mean: " + statisticsResult[0]);
        System.out.println("Median: " + statisticsResult[1]);
        System.out.println("Median Grouped: " + statisticsResult[2]);
        System.out.println("Standard Deviation: " + statisticsResult[3]);
        System.out.println("Variance: " + statisticsResult[4]);
        System.out.println("Passed Students: " + passResults[0]);
        System.out.println("Next Semester Students: " + passResults[1]);
        System.out.println("Pass Rate: " + passResults[2] + "%");
    }
}
