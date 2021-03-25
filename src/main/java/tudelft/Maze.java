package tudelft;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

// import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;

public class Maze {
    private Action[] actions;
    private State[][] states;
    private HashMap<State, Double> R;

    public Maze(File file) {
        //to build a 2-d maze from a file
        actions=new Action[4];
        actions[0]=new Action("up");
        actions[1]=new Action("down");
        actions[2]=new Action("left");
        actions[3]=new Action("right");

        try {
            RandomAccessFile r=new RandomAccessFile(file, "r");
            StringTokenizer dims=new StringTokenizer(r.readLine(), " ");
            int w=Integer.parseInt(dims.nextToken());
            int h=Integer.parseInt(dims.nextToken());
            states=new State[h][w];
            int y=0;
            String line=r.readLine();
            while (line!=null && !line.trim().equals("")) {
                int x=0;
                StringTokenizer locs=new StringTokenizer(line, " ");
                while (locs.hasMoreTokens())
                {
                    states[y][x]=new State(locs.nextToken());
                    x++;
                }
                line=r.readLine();
                y++;
            }
        } catch (Exception e) {
            System.out.println("Error reading maze file "+file);
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Ready reading maze file "+file);
        R=new HashMap<State, Double>();
    }

    private boolean isWalkable(State s) {
        //the maze's way to check if you can walk on a particular state
        //you dont need to use this directly, use the method getValidActions()
        return s.type.equals("1");
    }

    public ArrayList<Action> getValidActions(Agent r) {
        //use this method to retrieve the list of possible actions for an agent
        //the method checks if surrounding states are "walkable" and if the agent is not going out of the maze dimensions.
        //The method returns the list of actions
        ArrayList<Action>actionList=new ArrayList<Action>();
        if (r.y>0 && isWalkable(states[r.y-1][r.x]))
            actionList.add(actions[0]);
        if (r.y<states.length-1 && isWalkable(states[r.y+1][r.x]))
            actionList.add(actions[1]);
        if (r.x>0 && isWalkable(states[r.y][r.x-1]))
            actionList.add(actions[2]);
        if (r.x<states[r.y].length-1 && isWalkable(states[r.y][r.x+1]))
            actionList.add(actions[3]);
        return actionList;
    }

    public void setR(State s, double r) {
        //use this method to set the reward of the end state to the value in teh excercise
        //you can also play around with setting other states to a non-0 reward.
        //this is called reward shaping, and you can speed up the learning but also
        //teach the agent a suboptimal solution inadvertible.
        R.put(s,  new Double(r));
    }

    public double getR(State s) {
        //use this method te retreive the reward for a particular state
        if (R.containsKey(s))
            return R.get(s).doubleValue();
        return 0;
    }

    public State getState(int x, int y) {
        //simply returns the state at the location the agent is at
        //use this to find the current state of the agent, or use tudelft.Agent.getState(tudelft.Maze m)
        return states[y][x];
    }
}