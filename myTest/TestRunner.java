package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * TestRunner is a utility class that executes JUnit tests for the ListAdapter,
 * Iterator, and ListIterator classes.
 *
 * @test.libraries JUnit 4.13.2 Hamcrest 1.3
 */
public class TestRunner {

    public static void main(String[] args) {
        System.out.println("Starting JUnit Test Execution...");
        long startTime = System.currentTimeMillis();

        // Create JUnit runner
        JUnitCore junit = new JUnitCore();

        // Run all test classes
        Result result = junit.run(
                EmptyMapAdapterTests.class, // no items
                SmallSizeMapAdapterTests.class, // 5 items
                MediumSizeMapAdapterTests.class, // 100.000 items
                LargeSizeMapAdapterTests.class // MAX_INT+1 elements
        );

        long endTime = System.currentTimeMillis();

        // Print results
        printResults(result, endTime - startTime);
    }

    /**
     * Prints the results of the JUnit test execution. with formatting for
     * better readability.
     *
     * @param result        The result of the JUnit test execution.
     * @param executionTime The total time taken to execute the tests in
     *                      milliseconds.
     */
    private static void printResults(Result result, long executionTime) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("JUNIT TEST EXECUTION SUMMARY");
        System.out.println("=".repeat(50));
        System.out.println("Total Tests: " + result.getRunCount());
        System.out.println("Ignored Tests: " + result.getIgnoreCount());
        System.out.println("\033[0;31m" + "Failed Tests: " + "\033[0m" + result.getFailureCount());
        System.out.println("\033[0;32m" + "Passed Tests: " + "\033[0m" + (result.getRunCount() - result.getFailureCount()));
        System.out.println("\033[0;32m" + "Success Rate: " + "\033[0m" + (result.getRunCount() > 0 ?
                ((result.getRunCount() - result.getFailureCount()) * 100 / result.getRunCount()) : 0) + "%");
        System.out.println("Execution Time: " + executionTime + " ms");
        System.out.println("=".repeat(50));

        // Print failure details
        if (result.getFailureCount() > 0) {
            System.out.println("\nFAILURE DETAILS:");
            for (Failure failure : result.getFailures()) {
                System.out.println("- " + "\033[0;31m" + failure.getTestHeader() + "\033[0m");
                System.out.println("  " + failure.getMessage() + "\n");
            }
        }

        if (result.wasSuccessful()) {
            System.out.println("ALL TESTS PASSED!");
        } else {
            System.out.println("SOME TESTS FAILED!");
        }
    }
}