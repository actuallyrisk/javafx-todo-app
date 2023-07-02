package de.todoapp.reward;

import javafx.scene.image.Image;

public abstract class BaseReward {

    protected Image image;
    protected String name;

    public abstract boolean gainedReward(int points);

}
