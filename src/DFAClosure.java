import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class DFAClosure implements Cloneable {
    /*public DFA intersectionOfDFAs(DFA dfa1, DFA dfa2,ArrayList<Character> symbols1, ArrayList<Character> symbols2){
        if(!symbols1.equals(symbols2)) return null;
        int numOfStatesNeeded = dfa1.DFAstates.size() * dfa2.DFAstates.size();
        ArrayList<DFAState> finalDFAStates = new ArrayList<>();
        DFA finalDfa = new DFA();
        //finalDfa.start.name = "0,0";
        //finalDFAStates.add(finalDfa.start);
        dfa1.DFAstates.add(0, dfa1.start);
        dfa2.DFAstates.add(0, dfa2.start);
        for (int i = 0; i < dfa1.DFAstates.size(); i++) {
            for (int j = 0; j < dfa2.DFAstates.size(); j++) {
                //if (i != 0 && j != 0 && i!=j){
                    String name = dfa1.DFAstates.get(i).name + "," + dfa2.DFAstates.get(j).name;
                    DFAState newState = new DFAState();
                    newState.name = name;
                    if (dfa1.DFAstates.get(i).isFinal && dfa2.DFAstates.get(j).isFinal)
                        newState.isFinal = true;
                    finalDFAStates.add(newState);
                    if (i ==0 && j==0) finalDfa.start = newState;
                }
            //}
        }
        for (Character c : symbols1){
            String s1 = dfa1.start.getStateNameFromInputName(c);
            String s2 = dfa2.start.getStateNameFromInputName(c);
            finalDfa.start.transitions.add(new DFATransition(c,getDFA(s1, s2, finalDFAStates)));
        }
        for (int i = 0; i < dfa1.DFAstates.size(); i++) {
            for (int j = 0; j < dfa2.DFAstates.size(); j++) {
                for ( Character c : symbols1){
                    //String s1, s2;
                   // s1 = String.valueOf(dfa1.DFAstates.get(i).getStateFromInputName(c));
                    //finalDFAStates.get(i + j).transitions.add(new DFATransition(c, dfa1.DFAstates.get(i).getStateFromInputName(c)));
                    //finalDFAStates.get(i + j).transitions.add(new DFATransition(c, dfa1.DFAstates.get(i).getStateFromInputName(c)));
                    //int s1, s2;
                    //s1 = dfa1.DFAstates.get(i).transitions.
                    String s1 = dfa1.DFAstates.get(i).getStateNameFromInputName(c);
                    String s2 = dfa2.DFAstates.get(j).getStateNameFromInputName(c);
                    finalDFAStates.get((dfa2.DFAstates.size()) * i + j).transitions.add(new DFATransition(c,getDFA(s1, s2, finalDFAStates)));
                                                            //+ 1 -> including start ?
                }
            }
        }
        finalDfa.DFAstates = new ArrayList<>();
        finalDfa.DFAstates = finalDFAStates;
        return finalDfa;
    }*/                                           //dfa2 = complement
    public DFA intersectionOfDFAs(DFA dfa1, DFA dfa2,ArrayList<Character> symbols1, ArrayList<Character> symbols2){
        int numOfStatesNeeded = dfa1.DFAstates.size() * dfa2.DFAstates.size();
        int dfa2size = dfa2.DFAstates.size();
        ArrayList<DFAState> finalDFAStates = new ArrayList<>();
        DFA finalDfa = new DFA();
        //finalDfa.start.name = "0,0";
        //finalDFAStates.add(finalDfa.start);
        dfa1.DFAstates.add(0, dfa1.start);
        dfa2.DFAstates.add(0, dfa2.start);
        for (int i = 0; i < dfa1.DFAstates.size(); i++) {
            for (int j = 0; j < dfa2.DFAstates.size(); j++) {
                //if (i != 0 && j != 0 && i!=j){
                int n1 =Integer.parseInt(dfa1.DFAstates.get(i).name);
                int n2 = Integer.parseInt(dfa2.DFAstates.get(j).name);
                String name = n1/*dfa1.DFAstates.get(i).name*/ + "," + n1 /*dfa2.DFAstates.get(j).name*/;
                DFAState newState = new DFAState();
                newState.name = name;
                if (dfa1.DFAstates.get(i).isFinal && !dfa2.DFAstates.get(j).isFinal) {
                    newState.isFinal = true;
                    finalDfa.finalsIndexForIntersection.add( n1 * dfa2size + n2);
                }
                finalDFAStates.add(newState);
                if (i ==0 && j==0) finalDfa.start = newState;
            }
            //}
        }
        for (Character c : symbols1){
            String s1 = dfa1.start.getStateNameFromInputName(c);
            String s2 = dfa2.start.getStateNameFromInputName(c);
            finalDfa.start.transitions.add(new DFATransition(c,getDFA(s1, s2, finalDFAStates)));
        }
        for (int i = 0; i < dfa1.DFAstates.size(); i++) {
            for (int j = 0; j < dfa2.DFAstates.size(); j++) {
                for ( Character c : symbols1){
                    //String s1, s2;
                    // s1 = String.valueOf(dfa1.DFAstates.get(i).getStateFromInputName(c));
                    //finalDFAStates.get(i + j).transitions.add(new DFATransition(c, dfa1.DFAstates.get(i).getStateFromInputName(c)));
                    //finalDFAStates.get(i + j).transitions.add(new DFATransition(c, dfa1.DFAstates.get(i).getStateFromInputName(c)));
                    //int s1, s2;
                    //s1 = dfa1.DFAstates.get(i).transitions.
                    String s1 = dfa1.DFAstates.get(i).getStateNameFromInputName(c);
                    String s2 = dfa2.DFAstates.get(j).getStateNameFromInputName(c);
                    finalDFAStates.get((dfa2.DFAstates.size()) * i + j).transitions.add(new DFATransition(c,getDFA(s1, s2, finalDFAStates)));
                    //+ 1 -> including start ?
                }
            }
        }
        finalDfa.DFAstates = new ArrayList<>();
        finalDfa.DFAstates = finalDFAStates;
        return finalDfa;
    }
    public DFA DFAComplement(DFA dfa){
        /*if (dfa.start.isFinal == true) dfa.start.isFinal = false;
        else dfa.start.isFinal = true;*/
        ArrayList<DFAState> s = new ArrayList<>();
        /*for (DFAState s : dfa.DFAstates){
            *//*if(s.isFinal) s.isFinal = false;
            else s.isFinal = true;*//*
            s.isFinal = !s.isFinal;
        }*/
        s.add(dfa.start);
        DFA result = new DFA();
        DFAState start = dfa.start;
        result.start = start;
        result.DFAstates.add(start);
        for (int i = 0; i < dfa.DFAstates.size(); i++) {
            result.DFAstates.add(dfa.DFAstates.get(i));
            if(dfa.DFAstates.get(i).isFinal)
                result.DFAstates.get(i).isFinal = false;
            else result.DFAstates.get(i).isFinal = true;
        }
        //dfa.start = s.get(0);
        //dfa.DFAstates = s;
        //DFA result = new DFA();
        //result.DFAstates = s;
       // result.start = s.get(0);
        return result;
    }
    public DFAState getDFA(String s1, String s2, ArrayList<DFAState> arrayList/*,  DFAState curr, char input*/){
        for (DFAState s : arrayList){
            if (s.name.equals(s1+","+s2) /*|| s.name.equals(s1 + ",n")|| s.name.equals("n," + s2)*/)
                return s;
        }
        /*for (int i = 0; i < curr.states.size(); i++) {
            curr.states.get(i).transitions.
        }*/
        return null;
    }
    public static boolean bfs(ArrayList<DFAState> states, int index, int t, int dfa2Size) {
        boolean[] visited = new boolean[states.size()];
        Queue<DFAState> q = new LinkedList<>();
        q.add(states.get(index));
        visited[index] = true;
        //parent[s] = -1;
        Integer indx = null;
        while (!q.isEmpty()) {
            DFAState v = q.poll();
            for (int i = 0; i < v.transitions.size() && v.transitions.get(i).state != null; i++) {
                //if ( v.transitions.get(i).state.isFinal) return  true;
                String[] name = v.transitions.get(i).state.name.split(",");
                indx = Integer.parseInt(name[0]) * dfa2Size + dfa2Size+Integer.parseInt(name[1]);
                //if(indx >= visited.length) return false;
                if (/*v.transitions.get(i).state != null && !v.transitions.get(i).state.isFinal &&*/ !visited[indx]){
                   /* String[] name = v.transitions.get(i).state.name.split(",");
                    indx = Integer.parseInt(name[0]) * Integer.parseInt(name[1]);*/
                    q.offer(states.get(indx));
                    visited[indx] = true;
                    //parent[i] = v;
                }
            }
        }
        return (visited[t] == true);
    }
    public boolean dfs(ArrayList<DFAState> states, /*DFAState start, */int index, boolean[] isVisited,/* ArrayList<Integer> path, */int ind, ArrayList<Integer> finals){
//        if (states.get(index).isFinal || states.get(index).hasTransitionToFinal())
        if(index == ind)
            return true;
        isVisited[index] = true;
        for (DFATransition t : states.get(index).transitions){
            if (/*t.state!=null&& !t.state.hasLoop()*/finals.contains(states.indexOf(t))) {
                //if (/*t.state.name != null*/) {
                    //path.add(index);
                    String[] name = t.state.name.split(",");
                    //if(t.state.name.equals(states.get(index).name)) break;
                    Integer v = Integer.parseInt(name[0]) * Integer.parseInt(name[1]);
                    if (dfs(states, v, isVisited, /*path,*/ ind, finals))
                        //path.remove(index);
                        return true;
               // }
            }
        }
        isVisited[index] = false;
        return false;
    }
    public boolean bfs2(ArrayList<Integer> finals, int s, ArrayList<DFAState> states,int dfa2size){

        //if(finals.size() == 0) System.out.println("size zero");
        for (int i = 0; i < finals.size(); i++) {
            System.out.print("i:"+i);
            System.out.println("-"+finals.get(i));
            boolean[] visited = new boolean[states.size()];
            //if(bfs(states, 0, finals.get(i)))
            if(bfs(states,0,finals.get(i),dfa2size)){
                System.out.println("D");
                return true;
            }

        }
        return false;

    }
}
