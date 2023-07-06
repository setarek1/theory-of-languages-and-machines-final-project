import java.util.ArrayList;

public class DFA implements Cloneable{
    DFAState start = new DFAState();
    ArrayList<Integer> finalsIndexForIntersection = new ArrayList<>();
    ArrayList<DFAState> DFAstates = new ArrayList<>(); // not including start and end??? -> including end - not including start
    public ArrayList<Character> getDFASymbols(){ // is not done
        ArrayList<Character> returnArrayList = new ArrayList<>();
        for (DFAState s : DFAstates){

        }
        return returnArrayList;
    }
    public DFA clone() throws CloneNotSupportedException {
        return (DFA) super.clone();
    }


}
