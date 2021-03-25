package tudelft.mysolution;

import tudelft.*;

import java.io.File;
import java.util.ArrayList;

public class RunMe {

    public static void main(String[] args) {

        //load the maze
        //TODO replace this with the location to your maze on your file system
        Maze maze = new Maze(new File("data/toy_maze.txt"));

        //Set the reward at the bottom right to 10
        maze.setR(maze.getState(9, 9), 10);

        //create a robot at starting and reset location (0,0) (top left)
        Agent robot=new Agent(0,0);

        //make a selection object (you need to implement the methods in this class)
        EGreedy selection=new MyEGreedy();

        //make a Qlearning object (you need to implement the methods in this class)
        QLearning learn=new MyQLearning();

        boolean stop=false;
        double gamma = 0.9;
        double alfa = 0.5;
        int epochs = 0;
        int maxEpochs = 50;
        //keep learning until you decide to stop
        while (epochs < maxEpochs) {
            //TODO implement the action selection and learning cycle
            while(!(robot.x == 9 && robot.y == 9) && robot.nrOfActionsSinceReset < 30000) {
                State currentState = robot.getState(maze);
                Action nextAction = selection.getEGreedyAction(robot, maze, learn, gamma);
                robot.doAction(nextAction, maze);
                State futureState = robot.getState(maze);
                double r = maze.getR(futureState);
                learn.updateQ(currentState, nextAction, r, futureState, maze.getValidActions(robot), alfa, gamma);
            }
            robot.reset();
            epochs++;
            //while (location != end) {
            //  getEgreedyAction
            //  move to node
            //  updateR of current node (gamma * bestActionValue)

            //
            //TODO figure out a stopping criterion
        }

    }

}