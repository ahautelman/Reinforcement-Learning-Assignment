package tudelft;

public class Action {
    public String id;
    public Action(String id) {
        //the action id is simply the identifier of the type of action, such as up down left etc...
        this.id=new String(id);
    }
}
