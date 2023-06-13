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
    public void printTransitions(){
        String string = null;
        string += "start : ";
        for (int i = 0; i < start.transitions.size(); i++) {
            string += "(" + start.transitions.get(i).input + ", " + start.transitions.get(i).state.name + ") ";
        }
        string += "\n";
        for (State state : states){
            string += state.name + " : ";
            for (int i = 0; i < state.transitions.size(); i++) {
                string += "(" + state.transitions.get(i).input + ", " + state.transitions.get(i).state.name + ") ";
            }
            string += "\n";
        }
        string += "end : ";
        for (int i = 0; i < end.transitions.size(); i++) {
            string += "(" + end.transitions.get(i).input + ", " + end.transitions.get(i).state.name + ") ";
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

