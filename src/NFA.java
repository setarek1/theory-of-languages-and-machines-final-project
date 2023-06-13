import java.util.ArrayList;

public class NFA {
    State start, end;
    ArrayList<State> states; // not including start and end
    public NFA(){}
    public NFA(char c){
        start = new State("0");
        end = new State("1");
        start.transitions.add(new Transition(c, end));

    }
    public void printTransitions(){ //forgot about start and end
        String string = null;
        for (State state : states){
            string += state.name + " : ";
            for (int i = 0; i < state.transitions.size(); i++) {
                string += "(" + state.transitions.get(i).input + ", " + state.transitions.get(i).state.name + ") ";
            }
            string += "\n";
        }
        System.out.println(string);
    }
   /* private void updateStateNames(){
        for (State s : states){
            if (s.name == null){
                s.name =
            }
        }
    }*/

}

