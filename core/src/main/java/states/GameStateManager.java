package states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import websockets.websocket.handlerContext;

import java.util.Stack;

public class GameStateManager {

    private handlerContext controller;

    private Stack<State> states;

    public GameStateManager()
    {
        states = new Stack<>();
        controller = new handlerContext(this);
    }

    public void push(State state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop();
    }

    public void set(State state)
    {
        states.pop();
        states.push(state);
    }

    public void update(float dt)
    {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }

    public State getCurrentState() { return states.peek(); }
}
