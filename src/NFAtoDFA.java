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
                    ArrayList<State> reachableStates = deleteRepeatedStates(findReachableStates(currState, symbol));
                    DFAState newState = new DFAState();
                    for(State state : reachableStates) {
                        newState.states.addAll(deleteRepeatedStates(epsilonClosure(state)));
                    }
                    newState.states.addAll(reachableStates);
                    if (!newState.states.isEmpty() && !dfa.states.contains(newState)){
                        currState.transitions.add(new DFATransition(symbol, newState));
                        dfa.states.add(newState);
                        queue.add(newState);
                        //currState.transitions.add(new DFATransition(symbol, newState));
                    }
                }
            }
            return dfa;
        }

        public ArrayList<State> epsilonClosure(State state) {
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

        public ArrayList<State> findReachableStates(DFAState state, Character symbol) {
            ArrayList<State> arrayList = new ArrayList<>();
            for (State s : state.states) {
                for (Transition transition : s.transitions) {
                    if (transition.input == symbol) {
                        arrayList.add(transition.state);
                    }
                }
            }
            return arrayList;
        }
        public ArrayList<State> deleteRepeatedStates(ArrayList<State> inputArray){
            if ( inputArray.size() == 0) return  new ArrayList<>();
            ArrayList<State> outputArray = new ArrayList<>();
            boolean isRepeated = false;
            outputArray.add(inputArray.get(0));
            for (int i = 0; i < inputArray.size(); i++) {
                isRepeated = false;
                int j = 0;
                for ( ; (j < i) ; j++){
                    if (inputArray.get(i).transitions == outputArray.get(j).transitions){
                        isRepeated = true;
                        break;
                    }
                }
                if(!isRepeated){
                    //if (j !=0 ) outputArray.set(j, inputArray.get(i));
                    /*else*/ outputArray.add(inputArray.get(i));
                }
            }
            return outputArray;
        }

}
