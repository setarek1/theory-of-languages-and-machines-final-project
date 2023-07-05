import java.util.ArrayList;

public class DFAState implements Cloneable{
    String name;
    ArrayList<State> states;
    ArrayList<DFATransition> transitions;
    boolean isFinal;
    //boolean isFinalComplement = !isFinal;
    public DFAState(){
        states = new ArrayList<>();
        transitions = new ArrayList<>();
    }
    public DFAState(int num){
        transitions = new ArrayList<>();
        states = new ArrayList<>();
        name = num + "";
    }
    public DFAState(String name){
        this.name = name;
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
    public boolean hasTransitionToFinal(){
        for ( DFATransition transition : transitions){
            if (transition.state != null && transition.state.isFinal)
                return true;
        }
        return false;
    }
    public boolean hasLoop(){
        for (DFATransition t : transitions){
            if (t.state == null) return false;
            if (t.state.name.equals(
                    this.name)
                /*&& t.state == this*/)
                return true;
        }
        return false;
    }


}
