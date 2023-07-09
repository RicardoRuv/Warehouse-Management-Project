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
    private String warehouseName;

    @Column(name = "maximum_capacity")
    private int maximum_capacity;

    @JsonBackReference
    @OneToMany(targetEntity = WarehouseItem.class, mappedBy = "warehouse")
    private List<WarehouseItem> warehouseItems;

    public Warehouse() {
    }

    public Warehouse(String warehouseName, int warehouse_capacity) {
        this.warehouseName = warehouseName;
        this.maximum_capacity = warehouse_capacity;
    }

    public Warehouse(int warehouse_id, String warehouseName, int warehouse_capacity) {
        this.warehouse_id = warehouse_id;
        this.warehouseName = warehouseName;
        this.maximum_capacity = warehouse_capacity;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_name() {
        return warehouseName;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouseName = warehouse_name;
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
        result = prime * result + ((warehouseName == null) ? 0 : warehouseName.hashCode());
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
        if (warehouseName == null) {
            if (other.warehouseName != null)
                return false;
        } else if (!warehouseName.equals(other.warehouseName))
            return false;
        if (maximum_capacity != other.maximum_capacity)
            return false;
        return true;
    }
}
