import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        REtoNFA reToNfa = new REtoNFA();
        //reToNfa.createNFAFromRE("(a+b)c+f");
        NFA nfa = new NFA(0);
        nfa.end.setName(5+"");
        State state1 = new State("1");
        State state2 = new State("2");
        State state3 = new State("3");
        State state4 = new State("4");
        //State state5 = new State("5");
        //State state6 = new State("6");
        /*nfa.start.transitions.add(new Transition('$', state1));
        nfa.start.transitions.add(new Transition('$', state2));
        nfa.start.transitions.add(new Transition('b', state5));
        state1.transitions.add(new Transition('a', state4));
        state2.transitions.add(new Transition('a', state3));
        state5.transitions.add(new Transition('$', nfa.end));
        state4.transitions.add(new Transition('$', nfa.end));
        state3.transitions.add(new Transition('$', nfa.end));*/
        nfa.start.transitions.add(new Transition('a', state1));
        nfa.start.transitions.add(new Transition('b', state2));
        state1.transitions.add(new Transition('a', state2));
        state1.transitions.add(new Transition('b', state3));
        //state1.transitions.add(new Transition('a', nfa.end));
        state2.transitions.add(new Transition('b', state3));
        state3.transitions.add(new Transition('a', state4));
        state4.transitions.add(new Transition('$', nfa.end));

        NFAtoDFA nfaToDfa = new NFAtoDFA();
        nfa.states.add(state1);
        nfa.states.add(state2);
        nfa.states.add(state3);
        nfa.states.add(state4);
        //nfa.states.add(state5);
        /*DFAState dfaState = new DFAState();
        dfaState.states.add(nfa.start);
        ArrayList<State> array = nfaToDfa.findReachableStates(dfaState,'a');
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i).name);
        }*/
        ArrayList nfaSymbols = new ArrayList<>();
        nfaSymbols.add('b');
        nfaSymbols.add('a');
        nfaToDfa.createDFAfromNFA(nfa, nfaSymbols);
        /*ArrayList<State> s = new ArrayList<>();
        s.add(state1);
        s.add(state1);
        s.add(state2);
        s.add(state3);
        ArrayList<State> o = nfaToDfa.deleteRepeatedStates(s);
        System.out.println("DONE");*/

    }
}
