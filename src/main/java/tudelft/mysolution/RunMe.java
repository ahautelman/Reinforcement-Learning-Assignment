package tudelft.mysolution;

import tudelft.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunMe {

    public static void main(String[] args) {

        boolean stop=false;
        double gamma = 0.9;
        double alfa = 0.7;
        int epochs = 0;
        int maxEpochs = 10;
        int steps = 0;
        double epsilonK = 0.001;

        ArrayList<ArrayList<Integer>> averages = new ArrayList<>(10);
        for(int i = 0; i < 10; i++) {
            averages.add(new ArrayList<Integer>());
        }

        while (epochs < maxEpochs) {
            double epsilon = 1;
            double bestReward = 0;
            int epochsSinceBest = 0;

            List<Double> foundRewards = new ArrayList<>();
            //load the maze
            //TODO replace this with the location to your maze on your file system
            Maze maze = new Maze(new File("data/toy_maze.txt"));

            //Set the reward at the bottom right to 10
            maze.setR(maze.getState(9, 9), 10);

            //Set the reward at the bottom right to 10
            maze.setR(maze.getState(9, 0), 5);

            //create a robot at starting and reset location (0,0) (top left)
            Agent robot=new Agent(0,0);

            //make a selection object (you need to implement the methods in this class)
            EGreedy selection=new MyEGreedy();

            //make a Qlearning object (you need to implement the methods in this class)
            QLearning learn=new MyQLearning();
            double r = 0;
            while(steps < 20000) {

                while (!(robot.x == 9 && robot.y == 9) && !(robot.x == 9 && robot.y == 0)) {
                    State currentState = robot.getState(maze);
                    Action nextAction = selection.getEGreedyAction(robot, maze, learn, epsilon);
                    robot.doAction(nextAction, maze);
                    State futureState = robot.getState(maze);
                    r = maze.getR(futureState);
                    learn.updateQ(currentState, nextAction, r, futureState, maze.getValidActions(robot), alfa, gamma);
                }

                if(r > bestReward) {
                    bestReward = r;
                    epochsSinceBest = 0;
                } else {
                    epochsSinceBest++;
                }
                foundRewards.add(r);
                epsilon = Math.max(epsilon - epsilonK * epochsSinceBest, 0.05);
//                epsilon = Math.max(epsilon - epsilonK, 0.05);
//                System.out.println("epsilon: " + epsilon);
//                System.out.println("reward: " + r);

                int trial = robot.reset();
                averages.get(epochs).add(trial);
                steps += trial;

            }
            Double[] array = new Double[foundRewards.size()];
            DataToCSV.writeToFile(foundRewards.toArray(array), "found_rewards_toy_maze");
            System.out.println("LastReward:" + r);
            steps = 0;
            epochs++;
        }

        int index = 0;
        int minTrials = Integer.MAX_VALUE;
        System.out.println("trial size:");

        for(int i = 0; i < 10; i++) {
            System.out.println(i + ": " + averages.get(i).size());
            if(averages.get(i).size() < minTrials) {
                index = i;
                minTrials = averages.get(i).size();
            }
        }

        int[] averageOfTrials = new int[minTrials];

//        for(int i = 0; i < minTrials; i++) {
//            int num = 0;
//            for(int j = 0; j < 10; j++) {
//                if(averages.get(j).size() > i) {
//                    averageOfTrials[i] += averages.get(j).get(i);
//                    num++;
//                }
//            }
//            averageOfTrials[i] /= num;
//        }

        DataToCSV.writeToFile(averageOfTrials, "toy maze");
        System.out.println(Arrays.toString(averageOfTrials));

    }

}