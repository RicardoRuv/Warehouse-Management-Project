package com.skillstorm.project1.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {

    @Column(name = "warehouse_id")
    private int warehouse_id;

    @Column(name = "item_id")
    private int item_id;

    public CompositeKey() {
    }

    public CompositeKey(int warehouse_id, int item_id) {
        this.warehouse_id = warehouse_id;
        this.item_id = item_id;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + warehouse_id;
        result = prime * result + item_id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CompositeKey other = (CompositeKey) obj;
        if (warehouse_id != other.warehouse_id)
            return false;
        if (item_id != other.item_id)
            return false;
        return true;
    }

}
