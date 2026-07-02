package com.mysteryticking;

public record State (String name, int cost) {
    public State {
        if (name == null) throw new IllegalArgumentException("Name cannot be null.");
        if (cost < 1) throw new IllegalArgumentException("Cost cannot be less than 1.");
    }
}