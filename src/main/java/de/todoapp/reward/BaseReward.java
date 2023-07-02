package de.todoapp.reward;

public abstract class BaseReward {

    protected String id;

    public abstract boolean gainedReward(int points);

    public final String getId() {
        return this.id;
    }

}
