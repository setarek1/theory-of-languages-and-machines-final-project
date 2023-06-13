public class Transition {
    char input;
    State state;
    public Transition(char c, State state){
        input = c;
        this.state = state;
    }
}
