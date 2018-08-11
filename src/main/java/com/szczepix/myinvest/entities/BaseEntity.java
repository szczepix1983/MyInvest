package com.szczepix.myinvest.entities;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    protected String print(Object... arguments) {
        StringBuilder stringBuilder = new StringBuilder("[" + getClass().getSimpleName() + "]:");
        for (Object argument : arguments) {
            stringBuilder.append("[").append(argument.toString()).append("]");
        }
        return stringBuilder.toString();
    }
}
