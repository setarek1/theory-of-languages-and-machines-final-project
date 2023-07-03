import java.util.ArrayList;

public class DFAState {
    String name;
    ArrayList<State> states;
    ArrayList<DFATransition> transitions;
    boolean isFinal;
    public DFAState(){
        states = new ArrayList<>();
        transitions = new ArrayList<>();
    }
    public DFAState(int num){
        transitions = new ArrayList<>();
        states = new ArrayList<>();
        name = num + "";
    }
    public DFAState getStateFromInputName(char input){
        for (DFATransition t : transitions){
            if ( t.input == input)
                return t.state;
        }
        return null;
    }
    public String getStateNameFromInputName(char input){
        for (DFATransition t : transitions){
            if ( t.input == input)
                return t.state.name;
        }
        return null;
    }
    public int getStateIndex(char input){
        for (int i = 0; i < transitions.size(); i++) {
            if ( transitions.get(i).input == input)
                return i;
        }
        return -1;
    }
    public void setName(int i){
        this.name = (i+"");
    }


}
