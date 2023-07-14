const URL = 'http://localhost:8080/inventory';
let allItems = [];

document.addEventListener('DOMContentLoaded', () => {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {

        if (xhr.readyState === 4) {

            let cars = JSON.parse(xhr.responseText);

            cars.forEach(newInventory => {
                addInventoryToTable(newInventory);
            });
        }
    };
    xhr.open('GET', URL);
    xhr.send();
});

document.getElementById('inventory-table-body').addEventListener('click', (event) => {
    const target = event.target;
    if (target.classList.contains('edit-btn')) {
        const row = target.parentNode.parentNode;
        toggleEditable(row);
        const editButton = row.querySelector('.edit-btn');
        if (!row.isContentEditable) {
            // Change the button text to "Save" and add the save event listener
            editButton.innerText = 'Save';
            editButton.addEventListener('click', () => saveChanges(row));
        }
    }
});

// Event listener for "Add New" button click
document.getElementById('add-inventory-button').addEventListener('click', showAddInventoryModal);

// Event listener for form submission
document.getElementById('save-inventory-button').addEventListener('click', addInventory);

function showAddInventoryModal() {
    $('#addInventoryModal').modal('show');
}

function addInventoryToTable(newInventory) {

    // DOM Manipulation - where we manually change the DOM

    // creting all necessary DOM elements
    let tr = document.createElement('tr');      // will create a <tr> tag
    let warehouse = document.createElement('td');      // will create a <td> tag for warehouse name
    let make = document.createElement('td');      // will create a <td> tag for warehouse capacity 
    let model = document.createElement('td');      // will create a <td> tag for warehouse capacity 
    let quantity = document.createElement('td');
    let quantityToAdd = document.createElement('td');
    let editBtn = document.createElement('td');      // will create a <td> tag for edit button
    let deleteBtn = document.createElement('td');      // will create a <td> tag for delete button

    warehouse.innerText = newInventory.warehouse.warehouse_name;
    make.innerText = newInventory.item.make;
    model.innerText = newInventory.item.model;

    quantity.innerText = newInventory.warehouse.warehouse_capacity;
    quantityToAdd.innerText = newInventory.quantity;



    editBtn.innerHTML = ` <button class="btn btn-primary edit-btn" onclick="toggleEditable(this.parentNode.parentNode)">Edit</button>`;

    deleteBtn.innerHTML = `<button class="btn btn-danger" onclick="deleteItem(this.parentNode.parentNode)">Delete</button>`;

    tr.setAttribute('data-warehouse_id', newInventory.id.warehouse_id);
    tr.setAttribute('data-item_id', newInventory.id.item_id);
    tr.appendChild(warehouse);
    tr.appendChild(make);
    tr.appendChild(model);
    tr.appendChild(quantity);
    tr.appendChild(quantityToAdd);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    // setting the idattribute for the <tr>

    // adds the <tr> tag to the <tbody> tag
    document.getElementById('inventory-table-body').appendChild(tr);

    // adding the new movie to the list of all the movies
    allItems.push(newInventory);

}

function toggleEditable(row) {
    const isEditable = row.isContentEditable;

    if (!isEditable) {
        // Enable editing of the row
        row.querySelectorAll('td:not(:last-child)').forEach((cell) => {
            cell.contentEditable = 'true';
        });
    } else {
        // Disable editing of the row
        row.querySelectorAll('td:not(:last-child)').forEach((cell) => {
            cell.contentEditable = 'false';
        });
    }

    const editButton = row.querySelector('.edit-btn');
    if (isEditable) {
        // Change the button text to "Edit" and remove the save event listener
        editButton.innerText = 'Edit';
        editButton.removeEventListener('click', () => saveChanges(row));
    } else {
        // Change the button text to "Save" and add the save event listener
        editButton.innerText = 'Save';
        editButton.addEventListener('click', () => saveChanges(row));
    }
}

function saveChanges(row) {
    let warehouseDataID = row.getAttribute('data-warehouse_id');
    let itemId = row.getAttribute('data-item_id');
    console.log("warehouseDataID: " + warehouseDataID);
    console.log("itemDataID: " + itemId);

    // Capture the updated values from the editable fields
    //let updated_Quantity = row.cells[3].innerText;
    let quantityCell = row.querySelector('td:nth-child(5)');
    let updated_Quantity = quantityCell.innerText;

    console.log("updated_Quantity: " + updated_Quantity);

    // Create an object with the updated data
    let updatedInventory = {
        warehouse_id: warehouseDataID,
        item_id: itemId,
        quantity: updated_Quantity
    }

    // Send an AJAX request using fetch to update the item
    fetch(URL + `/update/${warehouseDataID}/${itemId}/${updated_Quantity}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedInventory)
    })
        .then(() => {
            console.log("Inventory updated successfully")
        })

        .catch(error => {
            // Handle any network or fetch-related errors
            console.error('Network error:', error);
        });

    // Disable editable mode
    toggleEditable(row);
}

function addInventory(event) {
    event.preventDefault();

    // Capture the values from the form
    const warehouseName = document.getElementById('warehouse-name-input').value;
    const carModel = document.getElementById('item-model-input').value;
    const quantity = document.getElementById('inventory-quantity-input').value;

    const newInventory = {
        warehouseName: warehouseName,
        item_id: carModel,
        quantity: quantity
    };

    console.log("warehouseName: " + warehouseName + " carModel: " + carModel + " quantity: " + quantity);

    fetch(`${URL}/create/${warehouseName}/${carModel}/${quantity}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newInventory)
    })
        .then(response => response.json())
        .then(data => {
            // Handle the response data as needed
            console.log(data);
        })
        .catch(error => {
            console.error('Network error:', error);
        });
}




function deleteItem(row) {
    let warehouseDataID = row.getAttribute('data-warehouse_id');
    let itemId = row.getAttribute('data-item_id');
    console.log("warehouseDataID: " + warehouseDataID);
    console.log("itemId: " + itemId);

    // Send an AJAX request using fetch to delete the item
    fetch(URL + `/delete/${warehouseDataID}/${itemId}`, {
        method: 'DELETE'
    })
        .then(() => {
            console.log("Item deleted successfully");

            // Remove the table row from the DOM
            row.remove();
        })
        .catch(error => {
            // Handle any network or fetch-related errors
            console.error('Network error:', error);
        });
}

function getWarehouseID(warehouseNameInput) {
    //getting the id associated with the warehouse name
    return fetch(`http://localhost:8080/warehouses/name/${warehouseNameInput.value}`)
        .then(response => response.json())
        .then(data => {
            // Extract the warehouse_id from the response
            const warehouseId = data.warehouse_id;

            // Use the warehouseId as needed
            console.log('Warehouse ID:', warehouseId);
            return warehouseId;

        })
        .catch(error => {
            console.error('Network error:', error);
        });
}
function getCarID(modelInput) {
    //getting the id associated with the car model
    //asumption there the model in unique, so it links it with the make.
    return fetch(`http://localhost:8080/car/model/${modelInput.value}`)
        .then(response => response.json())
        .then(data => {
            // Extract the warehouse_id from the response
            const carId = parseInt(data.item_id);

            // Use the warehouseId as needed
            console.log('Car ID:', carId);
            return carId;

        })
        .catch(error => {
            console.error('Network error:', error);
        });
}







