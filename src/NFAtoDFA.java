import java.util.*;

public class NFAtoDFA {
        /*public static ArrayList<State> convertNFAToDFA(NFA nfa, ArrayList<Character> nfaSymbols) {
            ArrayList<DFAState> dfaStates = new ArrayList<>();
            Queue<DFAState> queue = new LinkedList<>();

            DFAState startState = new DFAState();
            startState.states.addAll(epsilonClosure(nfa.start));
            dfaStates.add(startState);
            queue.add(startState);


            while (!queue.isEmpty()) {
                DFAState currentState = queue.poll();
                for (Character symbol : nfaSymbols) {
                    ArrayList<State> reachableStates = findReachableStates(currentState, symbol);
                    ArrayList<State> newStateFinal = new ArrayList<>();
                    for(State state : reachableStates) {
                        newStateFinal.addAll(epsilonClosure(state));
                    }
                    if (!newStateFinal.isEmpty() && !dfaStates.contains(newStateFinal)) {
                        dfaStates.add(newStateFinal);
                        queue.add(newStateFinal);
                    }
                }
            }

            return dfaStates;
        }*/
        public DFA createDFAfromNFA(NFA nfa, ArrayList<Character> nfaSymbols){
            DFA dfa = new DFA();
            Queue<DFAState> queue = new LinkedList<>();
            DFAState startState = new DFAState();
            startState.states.addAll(epsilonClosure(nfa.start));
            dfa.start = startState;
            //dfa.states.add(dfa.start); ???
            queue.add(startState);
            while(!queue.isEmpty()){
                DFAState currState = queue.poll();
                for (Character symbol : nfaSymbols){
                    ArrayList<State> reachableStates = findReachableStates(currState, symbol);
                    DFAState newState = new DFAState();
                    for(State state : reachableStates) {
                        newState.states.addAll(epsilonClosure(state));
                    }
                    if (!newState.states.isEmpty() && !dfa.states.contains(newState)){
                        currState.transitions.add(new DFATransition(symbol, newState));
                        dfa.states.add(newState);
                        queue.add(newState);
                    }

                }
            }

            return dfa;
        }

        public static ArrayList<State> epsilonClosure(State state) {
            ArrayList<State> closureArray = new ArrayList<>();
            Stack<State> stack = new Stack<>();

            stack.push(state);
            closureArray.add(state);

            while (!stack.isEmpty()) {
                State current = stack.pop();
                for (Transition transition : current.transitions) {
                    if (transition.input == '$' && !closureArray.contains(transition.state)) {
                        closureArray.add(transition.state);
                        stack.push(transition.state);
                    }
                }
            }

            return closureArray;
        }

        public static ArrayList<State> findReachableStates(DFAState state, Character symbol) {
            ArrayList<State> arrayList = new ArrayList<>();
            for (State s : state.states) {
                for (Transition transition : s.transitions) {
                    if (transition.input == symbol) {
                        arrayList.add(s);
                    }
                }
            }
            return arrayList;
        }

}