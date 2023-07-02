package de.todoapp.reward;

/**
 * The RewardInterface represents the functionality related to rewards in the ToDo App.
 * It provides methods for checking rewards, reading rewards, and summing up points.
 */
public interface RewardInterface {

    /**
     * Checks the rewards and determines if any rewards have been earned.
     */
    void check();

    /**
     * Reads the details of the rewards that have been earned.
     */
    void read();

    /**
     * Sums up the points earned from completed tasks.
     */
    void sumPoints();

}