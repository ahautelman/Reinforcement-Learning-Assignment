package tudelft.mysolution;

import tudelft.Action;
import tudelft.QLearning;
import tudelft.State;

import java.util.ArrayList;

public class MyQLearning extends QLearning {

    /**
     * @param s State
     * @param a action made in state s
     * @param r reward
     * @param s_next next action taken
     * @param possibleActions possible action of state s_next
     * @param alfa learning rate
     * @param gamma value contribution factor
     */
    @Override
    public void updateQ(State s, Action a, double r, State s_next, ArrayList<Action> possibleActions, double alfa, double gamma) {
        double currentQ = this.getQ(s, a);
        double maxFutureQ = getMaxOutgoingQ(s_next, possibleActions);
        this.setQ(s, a, (currentQ + alfa * (r + gamma * maxFutureQ - currentQ)));
    }

    /**
     * @param s State
     * @param possibleActions valid action for state s
     * @return biggest value that corresponds to the given state and one of the possible actions
     */
    public double getMaxOutgoingQ(State s, ArrayList<Action> possibleActions) {
        double maxQ = 0;
        for (Action a : possibleActions) {
            maxQ = Math.max(maxQ, this.getQ(s, a));
        }
        return maxQ;
    }

}