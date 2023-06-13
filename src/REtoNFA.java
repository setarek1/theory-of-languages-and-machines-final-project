import java.util.Stack;

public class REtoNFA {
    public void createNFAFromRE(String re){
        // join ->
        // postfix ->
        // char array ->
        NFA firstNFA, secondNFA, tempNFA;
        Stack<NFA> stack = new Stack<>();
        char[] REarray = {'a', 'b'/*, 'c', '.', '+'*/, '*', 'd', '.'};
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
                    stack.push(starNFA(firstNFA));
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
        result.start.transitions.set(0, new Transition('$', first.start));
        result.start.transitions.set(0, new Transition('$', second.start));
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
        temp.transitions.add(new Transition('$', right.start));
        for (int i = 0; i < right.states.size(); i++) {
            left.states.add(right.states.get(i));
        }
        left.end = temp;
        return left;
    }
    public NFA starNFA(NFA nfa){
        nfa.start.transitions.add(new Transition('$', nfa.end));
        nfa.end.transitions.add(new Transition('$', nfa.start));
        return nfa;
    }

    public static void main(String[] args) {
        REtoNFA test = null;
        test.createNFAFromRE("");
    }





}
