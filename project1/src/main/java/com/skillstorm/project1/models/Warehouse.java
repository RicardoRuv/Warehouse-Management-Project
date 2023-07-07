package com.skillstorm.project1.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity // tells jpa that this class relates to a db table
@Table(name = "WAREHOUSE") // tells jpa WHICH db table- name isisnt needed if your class name is the same
                           // as the table name
public class Warehouse {

    @Id
    @Column(name = "warehouse_id") // says this is a db column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SPECIFY AUTO INCREMENT
    private int warehouse_id;

    @Column(name = "warehouse_name")
    private String warehouse_name;

    @Column(name = "maximum_capacity")
    private int maximum_capacity;

    @JsonBackReference
    @OneToMany(targetEntity = WarehouseItem.class, mappedBy = "warehouse")
    private List<WarehouseItem> warehouseItems;

    public Warehouse() {
    }

    public Warehouse(String warehouse_name, int warehouse_capacity) {
        this.warehouse_name = warehouse_name;
        this.maximum_capacity = warehouse_capacity;
    }

    public Warehouse(int warehouse_id, String warehouse_name, int warehouse_capacity) {
        this.warehouse_id = warehouse_id;
        this.warehouse_name = warehouse_name;
        this.maximum_capacity = warehouse_capacity;
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
        return maximum_capacity;
    }

    public void setWarehouse_capacity(int warehouse_capacity) {
        this.maximum_capacity = warehouse_capacity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + warehouse_id;
        result = prime * result + ((warehouse_name == null) ? 0 : warehouse_name.hashCode());
        result = prime * result + maximum_capacity;
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
        if (maximum_capacity != other.maximum_capacity)
            return false;
        return true;
    }

}
