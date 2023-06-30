public class Test {
    public static void main(String[] args) {
        REtoNFA reToNfa = new REtoNFA();
        reToNfa.createNFAFromRE("(a.b)+cdefaabcv");
        //System.out.println(reToNfa.computePostfix(reToNfa.addConcat("a(b(cd(ef)))(g+h)")));
    }
}
