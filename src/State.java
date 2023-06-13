import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class State {
    String name;
    //HashMap<Character, State> inAndOutStates;
    ArrayList<Transition> transitions;
    boolean isStart;
    boolean isEnd;
    State nextState;
    public State(String name){
        this.name = name;
    }
    /*public State(){
        this.name =null;
    }*/

}
