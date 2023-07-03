import java.util.ArrayList;

public class DFAClosure {
    public DFA intersectionOfDFAs(DFA dfa1, DFA dfa2,ArrayList<Character> symbols){
        int numOfStatesNeeded = dfa1.DFAstates.size() * dfa2.DFAstates.size();
        ArrayList<DFAState> finalDFAStates = new ArrayList<>();
        DFA finalDfa = new DFA();
        //finalDfa.start.name = "0,0";
        //finalDFAStates.add(finalDfa.start);
        dfa1.DFAstates.add(0, dfa1.start);
        dfa2.DFAstates.add(0, dfa2.start);
        for (int i = 0; i < dfa1.DFAstates.size(); i++) {
            for (int j = 0; j < dfa2.DFAstates.size(); j++) {
                //if (i != 0 && j != 0 && i!=j){
                    String name = dfa1.DFAstates.get(i).name + "," + dfa2.DFAstates.get(j).name;
                    DFAState newState = new DFAState();
                    newState.name = name;
                    if (dfa1.DFAstates.get(i).isFinal && dfa2.DFAstates.get(j).isFinal)
                        newState.isFinal = true;
                    finalDFAStates.add(newState);
                    if (i ==0 && j==0) finalDfa.start = newState;
                }
            //}
        }
        for (Character c : symbols){
            String s1 = dfa1.start.getStateNameFromInputName(c);
            String s2 = dfa2.start.getStateNameFromInputName(c);
            finalDfa.start.transitions.add(new DFATransition(c,getDFA(s1 + "," + s2, finalDFAStates)));
        }
        for (int i = 0; i < dfa1.DFAstates.size(); i++) {
            for (int j = 0; j < dfa2.DFAstates.size(); j++) {
                for ( Character c : symbols){
                    //String s1, s2;
                   // s1 = String.valueOf(dfa1.DFAstates.get(i).getStateFromInputName(c));
                    //finalDFAStates.get(i + j).transitions.add(new DFATransition(c, dfa1.DFAstates.get(i).getStateFromInputName(c)));
                    //finalDFAStates.get(i + j).transitions.add(new DFATransition(c, dfa1.DFAstates.get(i).getStateFromInputName(c)));
                    //int s1, s2;
                    //s1 = dfa1.DFAstates.get(i).transitions.
                    String s1 = dfa1.DFAstates.get(i).getStateNameFromInputName(c);
                    String s2 = dfa2.DFAstates.get(j).getStateNameFromInputName(c);
                    finalDFAStates.get((dfa2.DFAstates.size()) * i + j).transitions.add(new DFATransition(c,getDFA(s1 + "," + s2, finalDFAStates)));
                                                            //+ 1 -> including start ?
                }
            }
        }
        finalDfa.DFAstates = finalDFAStates;
        return finalDfa;
    }
    public DFA DFAComplement(DFA dfa){
        dfa.start.isFinal = true;
        for (DFAState s : dfa.DFAstates){
            s.isFinal = !s.isFinal;
        }
        return dfa;
    }
    public DFAState getDFA(String name, ArrayList<DFAState> arrayList){
        for (DFAState s : arrayList){
            if (s.name.equals(name))
                return s;
        }
        return null;
    }
}
