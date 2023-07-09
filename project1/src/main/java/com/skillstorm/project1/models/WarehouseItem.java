package com.skillstorm.project1.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WAREHOUSE_ITEM")
public class WarehouseItem {

    @EmbeddedId
    private CompositeKey id;

    @ManyToOne // links which warehouse has which items
    @JoinColumn(name = "warehouse_id", insertable = false, updatable = false) // specify foreign key
    private Warehouse warehouse;
    // private int warehouse_id;

    @ManyToOne // Links how many items / warehouse id
    @JoinColumn(name = "item_id", insertable = false, updatable = false) // specify foreign key
    private Item item;

    @Column
    private int quantity;

    public WarehouseItem() {
    }

    public WarehouseItem(Warehouse warehouse, Item item, int quantity) {
        this.warehouse = warehouse;
        this.item = item;
        this.quantity = quantity;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Item getItem() {
        return item;
    }

    public CompositeKey getId() {
        return id;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((warehouse == null) ? 0 : warehouse.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + quantity;
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
        WarehouseItem other = (WarehouseItem) obj;
        if (warehouse == null) {
            if (other.warehouse != null)
                return false;
        } else if (!warehouse.equals(other.warehouse))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (quantity != other.quantity)
            return false;
        return true;
    }

    public Integer getWarehouseId() {
        return this.id.getWarehouse_id();
    }

    public void setId(CompositeKey id) {
        this.id = id;
    }

}
