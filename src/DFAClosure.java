import java.util.ArrayList;

public class DFAClosure {
    public DFA intersectionOfDFAs(DFA dfa1, DFA dfa2,ArrayList<Character> symbols){
        int numOfStatesNeeded = dfa1.DFAstates.size() + dfa2.DFAstates.size();
        ArrayList<DFAState> finalDFAStates = new ArrayList<>();
        for (int i = 0; i < dfa1.DFAstates.size(); i++) {
            for (int j = 0; j < dfa2.DFAstates.size(); j++) {
                String name = dfa1.DFAstates.get(i).name + dfa2.DFAstates.get(i).name;
                DFAState newState = new DFAState();
                newState.name = name;
                finalDFAStates.add(newState);
            }
        }
        for (int i = 0; i < dfa1.DFAstates.size(); i++) {
            for (int j = 0; j < dfa2.DFAstates.size(); j++) {
                for ( Character c : symbols){
                    //String s1, s2;
                   // s1 = String.valueOf(dfa1.DFAstates.get(i).getStateFromInputName(c));
                    //finalDFAStates.get(i + j).transitions.add(new DFATransition(c, dfa1.DFAstates.get(i).getStateFromInputName(c)));
                    //finalDFAStates.get(i + j).transitions.add(new DFATransition(c, dfa1.DFAstates.get(i).getStateFromInputName(c)));
                    int s1, s2;
                    //s1 = dfa1.DFAstates.get(i).transitions.

                }
            }
        }
        return new DFA();
    }
}
