package com.szczepix.myinvest.services.entityService;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object obj) {
        return this == obj;
    }

    protected String print(Object... arguments){
        return "["+getClass().getName()+"]=["+arguments+"]";
    }
}
