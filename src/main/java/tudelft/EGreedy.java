package tudelft;

public abstract class EGreedy {


    public abstract Action getRandomAction(Agent r, Maze m);


    public abstract  Action getBestAction(Agent r, Maze m, QLearning q);


    public abstract Action getEGreedyAction(Agent r, Maze m, QLearning q, double epsilon);

}