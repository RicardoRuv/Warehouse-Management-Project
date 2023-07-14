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
@Table(name = "ITEM") // tells jpa WHICH db table- name isisnt needed if your class name is the same
                      // as the table name
public class Item {

    @Id // SPECIFY primary key
    @Column(name = "item_id") // says this is a db column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // SPECIFY AUTO INCREMENT
    private Integer item_id;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @JsonBackReference // prevents json infinite recursion
    // specifies this is the one side of the one to many relationship
    @OneToMany(targetEntity = WarehouseItem.class, mappedBy = "item") // itm is the name of the field in the warehouse
                                                                      // item class
    private List<WarehouseItem> warehouseItems;

    public Item() {
    }

    public Item(String item_name, String model) {
        this.make = item_name;
        this.model = model;
    }

    public Item(Integer item_id, String item_name, String model) {
        this.item_id = item_id;
        this.make = item_name;
        this.model = model;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String item_name) {
        this.make = item_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + item_id;
        result = prime * result + ((make == null) ? 0 : make.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
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
        Item other = (Item) obj;
        if (item_id != other.item_id)
            return false;
        if (make == null) {
            if (other.make != null)
                return false;
        } else if (!make.equals(other.make))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        return true;
    }

}
