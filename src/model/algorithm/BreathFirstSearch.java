package model.algorithm;

import model.state.State;
import model.state.StateGenerator;

import java.util.*;

public class BreathFirstSearch extends Algorithm {

    public BreathFirstSearch(State initialState, State goalState) {
        super(initialState, goalState);
    }

    @Override
    public Stack<State> solve() {
        int nodeExplored = 0;
        Stack<State> toReturn = new Stack<>();
        Queue<State> queue = new LinkedList<State>();
        Set<State> visited = new HashSet<State>();
        StateGenerator generator = new StateGenerator();

        queue.add(initialState);
        visited.add(initialState);
        while (!queue.isEmpty()) {
            nodeExplored++;
            //Updates View every 100 000 nodes explored
            if (nodeExplored % 100000 == 0)
                this.notifySubscriber(nodeExplored);
            State current = queue.poll();
            if (current.equals(goalState)) {
                this.numOfSteps = current.getDepth();
                this.nodeExplored = nodeExplored;
                toReturn = current.getPath();
                return toReturn;
            }

            List<State> nextStates = generator.generateStates(current);
            for (State state : nextStates) {
                state.setDepth();
                if (!visited.contains(state)){
                    queue.add(state);
                    visited.add(state);
                }

            }
        }
        return toReturn;
    }

}
