package com.skillstorm.project1.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "warehouse")
public class Warehouse {

    @Id
    @Column(name = "warehouse_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SPECIFY AUTO INCREMENT
    private int warehouse_id;

    @Column(name = "warehouse_name")
    private String warehouse_name;

    @Column(name = "maximum_capacity")
    private int warehouse_capacity;

    public Warehouse() {
    }

    public Warehouse(String warehouse_name, int warehouse_capacity) {
        this.warehouse_name = warehouse_name;
        this.warehouse_capacity = warehouse_capacity;
    }

    public Warehouse(int warehouse_id, String warehouse_name, int warehouse_capacity) {
        this.warehouse_id = warehouse_id;
        this.warehouse_name = warehouse_name;
        this.warehouse_capacity = warehouse_capacity;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public int getWarehouse_capacity() {
        return warehouse_capacity;
    }

    public void setWarehouse_capacity(int warehouse_capacity) {
        this.warehouse_capacity = warehouse_capacity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + warehouse_id;
        result = prime * result + ((warehouse_name == null) ? 0 : warehouse_name.hashCode());
        result = prime * result + warehouse_capacity;
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
        Warehouse other = (Warehouse) obj;
        if (warehouse_id != other.warehouse_id)
            return false;
        if (warehouse_name == null) {
            if (other.warehouse_name != null)
                return false;
        } else if (!warehouse_name.equals(other.warehouse_name))
            return false;
        if (warehouse_capacity != other.warehouse_capacity)
            return false;
        return true;
    }

}
