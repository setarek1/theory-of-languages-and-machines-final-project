import java.util.Stack;

public class REtoNFA {
    public void createNFAFromRE(String re){
        // join ->
        // postfix ->
        // char array ->
        NFA firstNFA, secondNFA, tempNFA;
        Stack<NFA> stack = new Stack<>();
        char[] REarray = {'a'/*, 'b', 'c', '.', '+', '*', 'd'*/, '*'};
        for (char c : REarray){
            switch(c){
                case '+':
                    firstNFA = stack.pop();
                    secondNFA = stack.pop();
                    tempNFA = unionOfNFAs(firstNFA, secondNFA);
                    stack.push(tempNFA);
                    break;

                case '*':
                    firstNFA = stack.pop();
                    tempNFA = starNFA(firstNFA);
                    stack.push(tempNFA);
                    break;

                case '.':
                    firstNFA = stack.pop();
                    secondNFA = stack.pop();
                    tempNFA = concatenationOfNFAs(firstNFA, secondNFA);
                    stack.push(tempNFA);

                    break;

                default:
                    stack.push(singleCharNFA(c));
            }
        }
    }

    public NFA unionOfNFAs(NFA first, NFA second){
        NFA result = new NFA();
        int numOfStates = first.states.size() + second.states.size();
        result.start = new State((numOfStates + 2 /*start, end*/+ 1) + "");
        result.end = new State((numOfStates + 2 + 2) + "");
        if (result.start.transitions.size() == 0)
            result.start.transitions.add(0, new Transition('$', first.start));
        else result.start.transitions.set(0, new Transition('$', first.start));
        if (result.start.transitions.size() > 1)
        result.start.transitions.set(1, new Transition('$', second.start));
        else result.start.transitions.add(new Transition('$', second.start));
        //result.states.add(result.start);
        first.end.transitions.add(new Transition('$', result.end));
        second.end.transitions.add(new Transition('$', result.end));
        for (int i = 0; i < first.states.size(); i++) {
            result.states.add(first.states.get(i));
        }
        for (int i = 0; i < second.states.size(); i++) {
            result.states.add(second.states.get(i));
        }
        //result.states.add(result.end);
        return result;
    }
    public NFA singleCharNFA(char ch){
        return new NFA(ch);
    }
    public NFA concatenationOfNFAs(NFA left, NFA right){
        State temp;
        temp = left.end;
        int numOfStates = right.states.size() + left.states.size();
        right.start.setName((numOfStates + 2 /*start, end*/+ 1) + "");
        right.end.setName((numOfStates + 2 /*start, end*/+ 2) + "");
        //temp.transitions.add(new Transition('$', right.start));
        left.end.transitions.add(new Transition('$', right.start));
        left.states.add(right.start);
        for (int i = 0; i < right.states.size(); i++) {
            left.states.add(right.states.get(i));
        }
        left.states.add(right.end);
        //left.end = temp;
        return left;
    }
    public NFA starNFA(NFA first){
        NFA nfa = new NFA(first.states.size()+2);
        nfa.start.transitions.add(new Transition('$', first.end));
        nfa.start.transitions.add(new Transition('$', first.start));
        nfa.states.add(first.start);
        first.end.transitions.add(new Transition('$', nfa.end));
        for (int i = 0; i < first.states.size(); i++) {
            nfa.states.add(first.states.get(i));
        }
        nfa.states.add(first.end);
        nfa.end.transitions.add(new Transition('$', first.start));
        return nfa;
    }




}
