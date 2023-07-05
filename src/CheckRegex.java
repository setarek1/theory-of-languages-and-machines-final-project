import java.util.ArrayList;

public class CheckRegex {
    public CheckRegex(String regex1, String regex2){
        REtoNFA REtoNFA1 = new REtoNFA();
        REtoNFA REtoNFA2 = new REtoNFA();
        NFA nfa1 = REtoNFA1.createNFAFromRE(regex1);
        NFA nfa2 = REtoNFA2.createNFAFromRE(regex2);
        NFAtoDFA NfAtoDFA1 = new NFAtoDFA();
        NFAtoDFA NfAtoDFA2 = new NFAtoDFA();
        ArrayList<Character> symbols1 = REtoNFA1.getNfaSymbols();
        ArrayList<Character> symbols2 = REtoNFA1.getNfaSymbols();
        DFA dfa1 = NfAtoDFA1.createDFAfromNFA(nfa1, symbols1);
        DFA dfa2 = NfAtoDFA2.createDFAfromNFA(nfa2, symbols2);
        DFAClosure closureProperties = new DFAClosure();
        DFA L2complement = closureProperties.DFAComplement(dfa2);
        DFA L1_int_L2complement = closureProperties.intersectionOfDFAs(dfa1, L2complement, symbols1, symbols2);
        DFA L1complement = closureProperties.DFAComplement(dfa1);
        DFA L2_int_L1complement = closureProperties.intersectionOfDFAs(dfa2, L1complement, symbols1, symbols2);
        // what to do with the DFAs now ?
        int counter1 = 0;
        for (int i = 0; i < L1_int_L2complement.DFAstates.size(); i++) {
            if (!L1_int_L2complement.DFAstates.get(i).isFinal)
                counter1++;
        }
        int counter2 = 0;
        for (int i = 0; i < L2_int_L1complement.DFAstates.size(); i++) {
            if (!L2_int_L1complement.DFAstates.get(i).isFinal)
                counter2++;
        }
        /*System.out.println(symbols1);
        System.out.println(symbols2);*/
        if (L1_int_L2complement==null && L2_int_L1complement == null)
            System.out.println("NOT EQUAL");
        else if (counter1 == L1_int_L2complement.DFAstates.size() && counter2 == L2_int_L1complement.DFAstates.size())
            System.out.println("EQUAL");
        else System.out.println("NOT EQUAL");
    }

}
