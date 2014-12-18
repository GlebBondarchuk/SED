package com.bsu.sed.model;

/**
 * @author gbondarchuk
 */
public enum MessagePriority {
    HIGHEST(1),
    HIGH(2),
    NORMAL(3),
    LOW(4),
    LOWEST(5);

    private int priority;

    private MessagePriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
