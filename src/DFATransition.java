public class DFATransition {
    char input;
    DFAState state;
    public DFATransition(char c, DFAState state){
        input = c;
        this.state = state;
    }
    public DFATransition(char input){
        this.input = input;
    }
    public void setDFAState(DFAState state){
        this.state = state;
    }
    public DFAState getDFAState(){
        return state;
    }public String getDFAStateName(){
        return state.name;
    }

}
