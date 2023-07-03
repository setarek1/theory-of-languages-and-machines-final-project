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
    public DFA createDFAfromNFA(NFA nfa, ArrayList<Character> nfaSymbols) {
        DFA dfa = new DFA();
        Queue<DFAState> queue = new LinkedList<>();
        DFAState startState = new DFAState();
        ArrayList<String> DFAStatesNames = new ArrayList<>();
        startState.states.add(nfa.start);
        startState.states.addAll(epsilonClosure(nfa.start));
        dfa.start = startState;
        //dfa.states.add(dfa.start); ???
        queue.add(startState);
        while (!queue.isEmpty()) {
            ArrayList<State> reachableStates = new ArrayList<>();
            //ArrayList<State> epsilonStates = new ArrayList<>();
            DFAState currState = queue.poll();
            for (Character symbol : nfaSymbols) {
                /*ArrayList<State> reachableStates = new ArrayList<>();*/
                ArrayList<State> epsilonStates = new ArrayList<>();
                reachableStates = deleteRepeatedStates(findReachableStates(currState, symbol));
                DFAState newState = new DFAState();
                for (State state : reachableStates) {
                    ArrayList<State> reachableEpsilonStates;// = new ArrayList<>();
                    reachableEpsilonStates = epsilonClosure(state);
                    epsilonStates = getDifference(epsilonStates, reachableEpsilonStates);
                    //newState.states.addAll(epsilonStates);
                }
                newState.states.addAll(epsilonStates);
                newState.states.addAll(deleteRepeatedStates(reachableStates));
                String name ="";
                for (State s : newState.states) name += (s.name + ",");
                if ((!newState.states.isEmpty()) && (!dfa.DFAstates.contains(newState)) && (!DFAStatesNames.contains(name))) {
                    newState.name = name;
                    currState.transitions.add(new DFATransition(symbol, newState)); // is it done? (DFA Transitions) <----
                    dfa.DFAstates.add(newState);
                    queue.add(newState);
                    DFAStatesNames.add(name);
                    //currState.transitions.add(new DFATransition(symbol, newState));
                }
            }
            for (DFAState dfaState : dfa.DFAstates){
                if (dfaState.name.equals(nfa.end.name + ",") || dfaState.equals(nfa.end))
                    dfaState.isFinal = true;
            }
        }
        DFA finalDFa = createFinalDFA(dfa, nfa);
        return finalDFa;
    }

    public ArrayList<State> epsilonClosure(State state) {
        ArrayList<State> closureArray = new ArrayList<>();
        Stack<State> stack = new Stack<>();

        stack.push(state);
        //closureArray.add(state);

        while (!stack.isEmpty()) {
            State current = stack.pop();
            for (Transition transition : current.transitions) {
                if ((transition.input == '$') && (!closureArray.contains(transition.state))) {
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
                if ((transition.input == symbol) && !(arrayList.contains(transition.state))) {
                    arrayList.add(transition.state);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<State> deleteRepeatedStates(ArrayList<State> inputArray) {
        ArrayList<State> outputArray = new ArrayList<>();
        ArrayList<State> tempArray = new ArrayList<>();
        //if (inputArray.size() == 0) return null;
        //outputArray.add(inputArray.get(0));
        for (State s : inputArray) {
            boolean add = true;
                /*if (!outputArray.contains(s))
                        outputArray.add(s);*/
            for (State st : outputArray) {
                tempArray = new ArrayList<>();
                if (st.name.equals(s.name)) {
                    add = false;
                    //break;
                }
                if (add) tempArray.add(s);
            }
            outputArray.addAll(tempArray);
        }
        return inputArray;
    }

    public ArrayList<State> getDifference(ArrayList<State> ep1, ArrayList<State> ep2) {
        ArrayList<State> result = new ArrayList<>();
        result.addAll(ep1);
        for (State s : ep2) {
            if (!ep1.contains(s))
                result.add(s);
        }
        return result;
    }
   public boolean stringExistsInNames(String str, ArrayList<String> statesNames){
        /*
        baraye in ke masalan 1,3,2 va 1,2,3 yeki hesab beshe;
        ((!newState.states.isEmpty()) && (!dfa.DFAstates.contains(newState)) && (!stringExistsInNames(name, DFAStatesNames))){...}
         */
       return false;
   }
   public DFA createFinalDFA(DFA dfa, NFA nfa){
        DFA finalDFa = new DFA();
        int numOfDFAStates = dfa.DFAstates.size(); // + 1 : start?
       finalDFa.start = new DFAState(0);
       /*for (int i = 0; i <= numOfDFAStates; i++) {
           finalDFa.DFAstates.add(new DFAState(i + 1)); // Q1 ... Qn : states
       }
       for (DFAState s : dfa.DFAstates){
           for (Character c : dfa.getDFASymbols()){

           }
       }*/
       dfa.start.name = "0";
       for (int i = 0; i < numOfDFAStates; i++) {
           dfa.DFAstates.get(i).setName(i+1);
       }
       //finalDFa = dfa;
       for (DFAState dfaState : dfa.DFAstates){
           boolean isFinal = false;
           /*for (State s : dfaState.states){

           }*/
           if (dfaState.states.contains(nfa.end))
               dfaState.isFinal = true;
       }
       return dfa;
   }
}
