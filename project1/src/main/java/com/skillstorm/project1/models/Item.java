package com.skillstorm.project1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int item_id;

    private String item_name;

    private String item_description;

    public Item() {
    }

    public Item(int item_id, String item_name, String item_description) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_description = item_description;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + item_id;
        result = prime * result + ((item_name == null) ? 0 : item_name.hashCode());
        result = prime * result + ((item_description == null) ? 0 : item_description.hashCode());
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
        if (item_name == null) {
            if (other.item_name != null)
                return false;
        } else if (!item_name.equals(other.item_name))
            return false;
        if (item_description == null) {
            if (other.item_description != null)
                return false;
        } else if (!item_description.equals(other.item_description))
            return false;
        return true;
    }

}
