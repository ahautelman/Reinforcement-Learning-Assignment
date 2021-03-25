package tudelft.mysolution;

import tudelft.*;

import java.util.ArrayList;
import java.util.Random;

public class MyEGreedy extends EGreedy {

    @Override
    public Action getRandomAction(Agent r, Maze m) {
        //TODO to select an action at random in State s
        ArrayList<Action> actions = m.getValidActions(r);
        Random rand = new Random();
        return actions.get(rand.nextInt(actions.size()));
    }

    @Override
    public Action getBestAction(Agent r, Maze m, QLearning q) {
        //TODO to select the best possible action currently known in State s.
        ArrayList<Action> actions = m.getValidActions(r);
        double[] actionValues = q.getActionValues(r.getState(m), actions);

        Action bestAction = actions.get(0);
        double maxValue = actionValues[0];

        for (int i = 0; i < actionValues.length; i++)
        {
            if (actionValues[i] > maxValue)
            {
                maxValue = actionValues[i];
                bestAction = actions.get(i);
            }
        }

        return bestAction;
    }

    @Override
    public Action getEGreedyAction(Agent r, Maze m, QLearning q, double epsilon) {
        //TODO to select between random or best action selection based on epsilon.
        double prob = Math.random();

        if (prob <= epsilon) {
            return getRandomAction(r, m);
        }

        return getBestAction(r, m, q);
    }

}