import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        REtoNFA reToNfa = new REtoNFA();
        reToNfa.createNFAFromRE("(a+b)c*");
        NFA nfa = new NFA(0);
        nfa.end.setName(6+"");
        State state1 = new State("1");
        State state2 = new State("2");
        State state3 = new State("3");
        State state4 = new State("4");
        State state5 = new State("5");
        //State state6 = new State("6");
        nfa.start.transitions.add(new Transition('$', state1));
        nfa.start.transitions.add(new Transition('$', state2));
        nfa.start.transitions.add(new Transition('b', state5));
        state1.transitions.add(new Transition('a', state4));
        state2.transitions.add(new Transition('a', state3));
        state5.transitions.add(new Transition('$', nfa.end));
        state4.transitions.add(new Transition('$', nfa.end));
        state3.transitions.add(new Transition('$', nfa.end));
        NFAtoDFA nfaToDfa = new NFAtoDFA();
        /*DFAState dfaState = new DFAState();
        dfaState.states.add(nfa.start);
        ArrayList<State> array = nfaToDfa.findReachableStates(dfaState,'a');
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i).name);
        }*/
        ArrayList nfaSymbols = new ArrayList<>();
        nfaSymbols.add('a');
        nfaSymbols.add('b');
        nfaToDfa.createDFAfromNFA(nfa, nfaSymbols);
    }
}
