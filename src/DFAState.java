import java.util.ArrayList;

public class DFAState {
    String name;
    ArrayList<State> states;
    ArrayList<DFATransition> transitions;
    public DFAState(){
        states = new ArrayList<>();
        transitions = new ArrayList<>();
    }
    public DFAState(int num){
        transitions = new ArrayList<>();
        states = new ArrayList<>();
        name = num + "";
    }

}
