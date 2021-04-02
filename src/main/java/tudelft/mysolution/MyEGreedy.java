package tudelft.mysolution;

import tudelft.*;

import java.util.ArrayList;
import java.util.Random;

public class MyEGreedy extends EGreedy {

    /**
     * @param r Robot
     * @param m Maze that the robot is traversing
     * @return returns a random action that a robot can take
     */
    @Override
    public Action getRandomAction(Agent r, Maze m) {
        ArrayList<Action> actions = m.getValidActions(r);
        Random rand = new Random();
        return actions.get(rand.nextInt(actions.size()));
    }

    /**
     * @param r Robot
     * @param m Maze that the robot is traversing
     * @param q QLearning object that contains information about action values based on some state
     * @return returns the action that has the biggest value for the current state of robot (Agent)
     */
    @Override
    public Action getBestAction(Agent r, Maze m, QLearning q) {
        double epsilon = 0.000001d;
        ArrayList<Action> actions = m.getValidActions(r);
        double[] actionValues = q.getActionValues(r.getState(m), actions);

        ArrayList<Action> bestActions = new ArrayList<>();
        double maxValue = Double.MIN_VALUE;

        for (int i = 0; i < actionValues.length; i++)
        {
            if (actionValues[i] > maxValue)
            {
                bestActions = new ArrayList<>();
                maxValue = actionValues[i];
                bestActions.add(actions.get(i));
            } else if (Math.abs(actionValues[i] - maxValue) < epsilon) {
                bestActions.add(actions.get(i));
            }
        }

        Random rand = new Random();
        return bestActions.get(rand.nextInt(bestActions.size()));
    }

    /**
     * @param r Robot
     * @param m Maze that the robot is traversing
     * @param q QLearning object that contains information about action values based on some state
     * @param epsilon probability of picking a random action instead of the greedy one
     * @return greedy (best) action with probablity 1 - epsilon, random action otherwise
     */
    @Override
    public Action getEGreedyAction(Agent r, Maze m, QLearning q, double epsilon) {
        double prob = Math.random();

        if (prob <= epsilon) {
            return getRandomAction(r, m);
        }

        return getBestAction(r, m, q);
    }

}