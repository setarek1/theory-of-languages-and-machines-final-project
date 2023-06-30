import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class REtoNFA {
    private static final char[] nonSymbols = {'+', '*', '.', '(', ')'};
    static ArrayList<Character> nfaSymbols = new ArrayList<>();

    public NFA createNFAFromRE(String re){
        // add '.'
        //String concatAdded = addConcat(re);
        // postfix ->
        // char array ->
        NFA firstNFA, secondNFA, tempNFA;
        Stack<NFA> stack = new Stack<>();
        char[] REarray = computePostfix(addConcat(re)).toCharArray();
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
                    secondNFA = stack.pop();
                    firstNFA = stack.pop();
                    tempNFA = concatenationOfNFAs(firstNFA, secondNFA);
                    stack.push(tempNFA);

                    break;
                default:
                    stack.push(singleCharNFA(c));
                    addToSymbols(c);

            }
        }
        return stack.pop();
    }
    private void addToSymbols(char c){
        for (Character s : nfaSymbols){
            if (s == c)
                return;
        }
        nfaSymbols.add(c);
    }
    private static boolean comparePrecedence(char a, char b) {
        String precedence = "+.*";
        return precedence.indexOf(a) > precedence.indexOf(b);
    }
    String computePostfix(String regex) {
        StringBuilder result = new StringBuilder();
        List<Character> stack = new ArrayList<>();

        for (char c : regex.toCharArray()) {
            if (!isNonSymbol(c) || c == '*') {
                result.append(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.get(stack.size() - 1) != '(') {
                    result.append(stack.remove(stack.size() - 1));
                }
                stack.remove(stack.size() - 1);
            } else if (c == '(') {
                stack.add(c);
            } else if (stack.isEmpty() || stack.get(stack.size() - 1) == '(' || comparePrecedence(c, stack.get(stack.size() - 1))) {
                stack.add(c);
            } else {
                while (!stack.isEmpty() && stack.get(stack.size() - 1) != '(' && !comparePrecedence(c, stack.get(stack.size() - 1))) {
                    result.append(stack.remove(stack.size() - 1));
                }
                stack.add(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.remove(stack.size() - 1));
        }

        return result.toString();
    }
    public String addConcat(String regex) {
        StringBuilder result = new StringBuilder();
        int length = regex.length();

        for (int i = 0; i < length - 1; i++) {
            result.append(regex.charAt(i));
            if (!isNonSymbol(regex.charAt(i))) {
                if (!isNonSymbol(regex.charAt(i + 1)) || regex.charAt(i + 1) == '(') {
                    result.append('.');
                }
            }
            if (regex.charAt(i) == ')' && regex.charAt(i + 1) == '(') {
                result.append('.');
            }
            if (regex.charAt(i) == '*' && regex.charAt(i + 1) == '(') {
                result.append('.');
            }
            if (regex.charAt(i) == '*' && !isNonSymbol(regex.charAt(i + 1))) {
                result.append('.');
            }
            if (regex.charAt(i) == ')' && !isNonSymbol(regex.charAt(i + 1))) {
                result.append('.');
            }
        }

        result.append(regex.charAt(length - 1));
        return result.toString();
    }
    private static boolean isNonSymbol(char c) {
        for (char symbol : nonSymbols) {
            if (c == symbol) {
                return true;
            }
        }
        return false;
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
    public NFA concatenationOfNFAs(NFA left, NFA right){ //fix state names
        int numOfStates = right.states.size() + left.states.size();
        NFA nfa = new NFA(numOfStates + 4);
        left.start.setName((numOfStates + 2) + "");
        right.end.setName((numOfStates + 3) + "");
        /*left.start.setName((numOfStates + 2 *//*start, end*//*+ 1) + "");
        left.end.setName((numOfStates + 2 *//*start, end*//*+ 2) + "");
        right.start.setName((numOfStates + 2 *//*start, end*//*+ 1) + "");
        right.end.setName((numOfStates + 2 *//*start, end*//*+ 2) + "");*/
        //temp.transitions.add(new Transition('$', right.start));
        right.end.transitions.add(new Transition('$', nfa.end));
        left.end.transitions.add(new Transition('$', right.start));
        nfa.start.transitions.add(new Transition('$', left.start));
        //left.end.transitions.add(new Transition('$', right.start));
        //left.states.add(right.start);
        nfa.states.add(left.start);
        nfa.states.add(left.end);
        for (int i = 0; i < left.states.size(); i++) {
            nfa.states.add(left.states.get(i));

        }
        for (int i = 0; i < right.states.size(); i++) {
            nfa.states.add(right.states.get(i));
        }
        nfa.states.add(right.start);
        nfa.states.add(right.end);
        //left.states.add(right.end);
        //left.end = temp;
        return nfa;
    }
    public NFA starNFA(NFA first){
        NFA nfa = new NFA(first.states.size()+2);
        nfa.start.transitions.add(new Transition('$', first.end));
        nfa.start.transitions.add(new Transition('$', first.start));
        nfa.states.add(first.start);
        first.end.transitions.add(new Transition('$', nfa.end));
        first.end.transitions.add(new Transition('$', first.start));
        for (int i = 0; i < first.states.size(); i++) {
            nfa.states.add(first.states.get(i));
        }
        nfa.states.add(first.end);
        //nfa.end.transitions.add(new Transition('$', first.start));
        return nfa;
    }
    public void renameStates(NFA nfa){
        nfa.start.setName(0 + "");
        int name = 1;
        for(State s : nfa.states){
            s.setName(name + "");
            name++;
        }
        nfa.end.setName(name + "");

    }




}
