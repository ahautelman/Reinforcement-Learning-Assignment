package tudelft.mysolution;

import tudelft.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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
        double alfa = 0.7;
        double epsilon = 0.1;
        int epochs = 0;
        int maxEpochs = 10;
        int steps = 0;
        ArrayList<ArrayList<Integer>> averages = new ArrayList<>(10);
        for(int i = 0; i < 10; i++) {
            averages.add(new ArrayList<Integer>());
        }

        //keep learning until you decide to stop
        while (epochs < maxEpochs) {
            //TODO implement the action selection and learning cycle
            while(steps < 30000) {
                while (!(robot.x == 9 && robot.y == 9)) {
                    State currentState = robot.getState(maze);
                    Action nextAction = selection.getEGreedyAction(robot, maze, learn, epsilon);
                    robot.doAction(nextAction, maze);
                    State futureState = robot.getState(maze);
                    double r = maze.getR(futureState);
                    learn.updateQ(currentState, nextAction, r, futureState, maze.getValidActions(robot), alfa, gamma);
                }
                int trial = robot.reset();
                averages.get(epochs).add(trial);
                steps += trial;
            }
            steps = 0;
            epochs++;
        }

        int index = 0;
        int maxTrials = Integer.MIN_VALUE;
        System.out.println("trial size:");

        for(int i = 0; i < 10; i++) {
            System.out.println(i + ": " + averages.get(i).size());
            if(averages.get(i).size() > maxTrials) {
                index = i;
                maxTrials = averages.get(i).size();
            }
        }

        int[] averageOfTrials = new int[maxTrials];

        for(int i = 0; i < maxTrials; i++) {
            int num = 0;
            for(int j = 0; j < 10; j++) {
                if(averages.get(j).size() > i) {
                    averageOfTrials[i] += averages.get(j).get(i);
                    num++;
                }
            }
            averageOfTrials[i] /= num;
        }

        System.out.println(Arrays.toString(averageOfTrials));

    }

}