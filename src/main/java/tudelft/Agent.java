package tudelft;

public class Agent {
    public int x,y;
    private int startX, startY;
    public int nrOfActionsSinceReset=0;

    public Agent(int startX, int startY) {
        this.startX=startX;
        this.startY=startY;
        x=startX;
        y=startY;
    }
    public State getState(Maze m) {
        return m.getState(this.x, this.y);
    }

    public State doAction(Action a, Maze m) {
        //executes an action and returns the new tudelft.State the agent is in according to the tudelft.Maze
        nrOfActionsSinceReset++;
        if (a.id.equals("up"))
            y--;
        if (a.id.equals("down"))
            y++;
        if (a.id.equals("left"))
            x--;
        if (a.id.equals("right"))
            x++;
        return getState(m);
    }

    public void reset() {
        System.out.println(nrOfActionsSinceReset);
        x=startX;
        y=startY;
        nrOfActionsSinceReset=0;
    }
}