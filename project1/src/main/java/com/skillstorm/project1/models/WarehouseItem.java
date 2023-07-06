package com.skillstorm.project1.models;

public class WarehouseItem {

    private int warehouseItem_id;

    private Warehouse warehouse;

    private Item item;

    private int quantity;

    public WarehouseItem() {
    }

    public WarehouseItem(int warehouseItem_id, Warehouse warehouse, Item item, int quantity) {
        this.warehouseItem_id = warehouseItem_id;
        this.warehouse = warehouse;
        this.item = item;
        this.quantity = quantity;
    }

    public int getWarehouseItem_id() {
        return warehouseItem_id;
    }

    public void setWarehouseItem_id(int warehouseItem_id) {
        this.warehouseItem_id = warehouseItem_id;
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
        result = prime * result + warehouseItem_id;
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
        if (warehouseItem_id != other.warehouseItem_id)
            return false;
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

}
