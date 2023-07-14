const URL = 'http://localhost:8080/warehouses';
let allWarehouses = [];

document.addEventListener('DOMContentLoaded', () => {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {

        if (xhr.readyState === 4) {

            let warehouses = JSON.parse(xhr.responseText);

            warehouses.forEach(newWarehouse => {
                addWarehouseToTable(newWarehouse);
            });
        }
    };
    xhr.open('GET', URL);
    xhr.send();
});

document.getElementById('warehouse-table-body').addEventListener('click', (event) => {
    const target = event.target;
    if (target.classList.contains('edit-btn')) {
        const row = target.parentNode.parentNode;
        toggleEditable(row);
    }
});
// Event listener for "Add New" button click
document.getElementById('add-warehouse-button').addEventListener('click', showAddWarehouseModal);

// Event listener for form submission
document.getElementById('save-warehouse-button').addEventListener('click', addWarehouse);

function showAddWarehouseModal() {
    $('#addWarehouseModal').modal('show');
}


function addWarehouseToTable(newWarehouse) {

    // DOM Manipulation - where we manually change the DOM

    // creting all necessary DOM elements
    let tr = document.createElement('tr');      // will create a <tr> tag
    let WhName = document.createElement('td');      // will create a <td> tag for warehouse name
    let WhCapacity = document.createElement('td');      // will create a <td> tag for warehouse capacity   
    let editBtn = document.createElement('td');      // will create a <td> tag for edit button
    let deleteBtn = document.createElement('td');      // will create a <td> tag for delete button


    WhName.innerText = newWarehouse.warehouse_name;
    WhCapacity.innerText = newWarehouse.warehouse_capacity;


    //editBtn.innerHTML = `<button class="btn btn-primary edit-btn" onclick="toggleEditable(this.parentNode.parentNode)">Edit</button>`;
    deleteBtn.innerHTML = `<button class="btn btn-danger" onclick="deleteWarehouse(this.parentNode.parentNode)">Delete</button>`;
    editBtn.innerHTML = `<button class="btn btn-primary edit-btn">Edit</button>`;




    tr.setAttribute('data-warehouse_id', newWarehouse.warehouse_id);
    tr.appendChild(WhName);
    tr.appendChild(WhCapacity);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);


    // adds the <tr> tag to the <tbody> tag
    document.getElementById('warehouse-table-body').appendChild(tr);

    // adding the new movie to the list of all the movies
    allWarehouses.push(newWarehouse);

}
function toggleEditable(row) {
    const isEditable = row.isContentEditable;
    row.contentEditable = !isEditable;

    //change color
    const elementsToToggle = row.querySelectorAll('.editable');
    elementsToToggle.forEach(element => element.classList.toggle('editable'));

    const editButton = row.querySelector('.edit-btn');
    if (isEditable) {
        // Change the button text to "Edit" and remove the save event listener
        editButton.innerText = 'Edit';
        editButton.removeEventListener('click', saveChanges);
    } else {
        // Change the button text to "Save" and add the save event listener
        editButton.innerText = 'Save';
        editButton.addEventListener('click', () => saveChanges(row));
    }
}


function deleteWarehouse(row) {
    let deletewarehouseId = row.getAttribute('data-warehouse_id');
    let warehouseName = row.cells[0].innerText;
    let warehouseCapacity = row.cells[1].innerText;

    let requestBody = {
        warehouse_id: deletewarehouseId,
        warehouse_name: warehouseName,
        capacity: warehouseCapacity
    }
    //Send an AJAX request using fetch to delete the warehouse
    fetch(URL + `/warehouse/delete`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
        .then(response => {
            if (response.ok) {
                // Remove the row from the table
                row.remove();
                // Handle successful response, e.g., display a success message
                console.log('Warehouse deleted successfully');
            } else {
                // Handle error response, e.g., display an error message
                console.error('Error deleting warehouse');
            }
        })
        .catch(error => {
            // Handle any network or fetch-related errors
            console.error('Network error:', error);
        });
}

function saveChanges(row) {
    let warehouseId = row.getAttribute('data-warehouse_id');

    // Capture the updated values from the editable fields
    let updatedName = row.cells[0].innerText;
    let updatedCapacity = row.cells[1].innerText;

    // Create an object with the updated data
    let updatedWarehouse = {
        warehouse_id: warehouseId,
        warehouse_name: updatedName,
        capacity: updatedCapacity
    };

    // Send an AJAX request using fetch to update the warehouse
    fetch(URL + `/warehouse/update?id=${updatedWarehouse.warehouse_id}&name=${updatedWarehouse.warehouse_name}&capacity=${updatedWarehouse.capacity}`, {   //'/warehouse/update',  
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedWarehouse)
    })
        .then((data) => {
            // this will handle all 100, 200, and 300 status code responses

            // we still need to serialize the response into JSON
            return data.json();
        })

        .catch(error => {
            // Handle any network or fetch-related errors
            console.error('Network error:', error);
        });

    // Disable editable mode
    toggleEditable(row);
}

// Function to add a new warehouse
function addWarehouse(event) {
    //avoid refresh
    event.preventDefault();

    const nameInput = document.getElementById('warehouse-name-input');
    const capacityInput = document.getElementById('warehouse-capacity-input');
    console.log("name " + nameInput.value + " capacity " + capacityInput.value);

    const newWarehouse = {
        warehouse_name: nameInput.value,
        warehouse_capacity: capacityInput.value
    };

    fetch(URL + `/warehouse/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newWarehouse)
    })
        .then(response => {
            if (response.ok) {
                // Reset the form
                addWarehouseToTable(newWarehouse);
                document.getElementById('add-warehouse-form').reset();

                //hide modal
                $('#addWarehouseModal').modal('hide');

            } else {
                console.error('Error adding new warehouse');
            }
        })
        .catch(error => {
            console.error('Network error:', error);
        });
}


