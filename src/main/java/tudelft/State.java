package tudelft;

public class State {
    public String type;
    public State(String type) {
        //the type of the state is a 0 or a 1, 1 being path, 0 being the wall
        this.type=new String(type);
    }

}