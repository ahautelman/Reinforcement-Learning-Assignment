package tudelft.mysolution;

import tudelft.Agent;
import tudelft.EGreedy;
import tudelft.Maze;
import tudelft.QLearning;

import java.io.File;

public class RunMe {

    public static void main(String[] args) {

        //load the maze
        //TODO replace this with the location to your maze on your file system
        Maze maze = new Maze(new File("C:\\data\\development\\github\\QLearning\\data\\toy_maze.txt"));

        //Set the reward at the bottom right to 10
        maze.setR(maze.getState(9, 9), 10);

        //create a robot at starting and reset location (0,0) (top left)
        Agent robot=new Agent(0,0);

        //make a selection object (you need to implement the methods in this class)
        EGreedy selection=new MyEGreedy();

        //make a Qlearning object (you need to implement the methods in this class)
        QLearning learn=new MyQLearning();

        boolean stop=false;

        //keep learning until you decide to stop
        while (!stop) {
            //TODO implement the action selection and learning cycle

            //TODO figure out a stopping criterion
        }

    }

}