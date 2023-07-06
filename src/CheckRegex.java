import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CheckRegex implements Cloneable {
    public CheckRegex(String regex1, String regex2) throws CloneNotSupportedException {
        REtoNFA REtoNFA1 = new REtoNFA();
        REtoNFA REtoNFA2 = new REtoNFA();
        NFA nfa1 = REtoNFA1.createNFAFromRE(regex1);
        NFA nfa2 = REtoNFA2.createNFAFromRE(regex2);
        NFAtoDFA NfAtoDFA1 = new NFAtoDFA();
        NFAtoDFA NfAtoDFA2 = new NFAtoDFA();
        //ArrayList<Character> symbols1 = (ArrayList<Character>) REtoNFA1.getNfaSymbols().clone();
        ArrayList<Character> symbols1 = new ArrayList<>();
        for (int i = 0; i < regex1.length(); i++) {
            if (regex1.charAt(i) != '$' && regex1.charAt(i) != '(' && regex1.charAt(i) != ')' && regex1.charAt(i) != '*' && regex1.charAt(i) != '+'
             && !symbols1.contains(regex1.charAt(i)))
                symbols1.add(regex1.charAt(i));
        }
        //ArrayList<Character> symbols2 = (ArrayList<Character>) REtoNFA2.getNfaSymbols().clone();
        ArrayList<Character> symbols2 = new ArrayList<>();
        for (int i = 0; i < regex2.length(); i++) {
            if (regex2.charAt(i) != '$' && regex2.charAt(i) != '(' && regex2.charAt(i) != ')' && regex2.charAt(i) != '*' && regex2.charAt(i) != '+'
                    && !symbols2.contains(regex2.charAt(i)))
                symbols2.add(regex2.charAt(i));
        }
        DFA dfa1 = NfAtoDFA1.createDFAfromNFA(nfa1, symbols1);
        DFA dfa2 = NfAtoDFA2.createDFAfromNFA(nfa2, symbols2);
        DFAClosure closureProperties1 = new DFAClosure();
        DFAClosure closureProperties2 = new DFAClosure();

        DFA L1,L2;
        L1 = dfa1.clone();
        L2 = dfa2.clone();

        if(symbols1.size() != symbols2.size()) {
            System.out.println("NOT EQUAL");
            return;
        }
        int count = 0;
        for (Character c : symbols1){
            if (symbols2.contains(c))
                count++;
        }
        if (count != symbols1.size()) {
            System.out.println("NOT EQUAL");
            return;
        }


        DFA L1_int_L2complement = closureProperties1.intersectionOfDFAs(dfa1, L2/*complement*/, symbols1, symbols2).clone();
        DFA L2_int_L1complement = closureProperties2.intersectionOfDFAs(dfa2, L1/*complement*/, symbols1, symbols2).clone();


        /*int counter1 = 0;
        int finalCounter1 = 0;
        for (int i = 0; i < L1_int_L2complement.DFAstates.size(); i++) {
            if (L1_int_L2complement.DFAstates.get(i).transitions.size() == 0)
                counter1++;
            if(!L1_int_L2complement.DFAstates.get(i).isFinal)
                finalCounter1++;
        }
        int counter2 = 0;
        int finalCounter2 = 0;
        for (int i = 0; i < L2_int_L1complement.DFAstates.size(); i++) {
            if (L2_int_L1complement.DFAstates.get(i).transitions.size() == 0)
                counter2++;
            if(!L2_int_L1complement.DFAstates.get(i).isFinal)
                finalCounter2++;
        }*/

        if (!closureProperties1.bfs2(L1_int_L2complement.finalsIndexForIntersection, 0,L1_int_L2complement.DFAstates, L2.DFAstates.size())
            &&
             (!closureProperties2.bfs2(L2_int_L1complement.finalsIndexForIntersection, 0, L2_int_L1complement.DFAstates, L1.DFAstates.size())))
                System.out.println("EQUAL");


        else System.out.println("NOT EQUAL");


    }


}
