const URL = 'http://localhost:8080/cars';
let allItems = [];

document.addEventListener('DOMContentLoaded', () => {
    let xhr = new XMLHttpRequest();

    xhr.onreadystatechange = () => {

        if (xhr.readyState === 4) {

            let cars = JSON.parse(xhr.responseText);

            cars.forEach(newCar => {
                addItemToTable(newCar);
            });
        }
    };
    xhr.open('GET', URL);
    xhr.send();
});

document.getElementById('item-table-body').addEventListener('click', (event) => {
    const target = event.target;
    if (target.classList.contains('edit-btn')) {
        const row = target.parentNode.parentNode;
        toggleEditable(row);
    }
});

// Event listener for "Add New" button click
document.getElementById('add-item-button').addEventListener('click', showAddItemModal);

// Event listener for form submission
//document.getElementById('save-item-button').addEventListener('click', addItem);

function showAddItemModal() {
    $('#addItemModal').modal('show');
}

function addItemToTable(newItem) {

    // DOM Manipulation - where we manually change the DOM

    // creting all necessary DOM elements
    let tr = document.createElement('tr');      // will create a <tr> tag
    let ItemMake = document.createElement('td');      // will create a <td> tag for warehouse name
    let ItemModel = document.createElement('td');      // will create a <td> tag for warehouse capacity   
    let editBtn = document.createElement('td');      // will create a <td> tag for edit button
    let deleteBtn = document.createElement('td');      // will create a <td> tag for delete button

    ItemMake.innerText = newItem.make;
    ItemModel.innerText = newItem.model;



    editBtn.innerHTML = ` <button class="btn btn-primary edit-btn" onclick="toggleEditable(this.parentNode.parentNode)">Edit</button>`;

    deleteBtn.innerHTML = `<button class="btn btn-danger" onclick="deleteItem(this.parentNode.parentNode)">Delete</button>`;


    tr.setAttribute('data-item_id', newItem.item_id);
    tr.appendChild(ItemMake);
    tr.appendChild(ItemModel);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);

    // setting the idattribute for the <tr>
    //tr.setAttribute('id', 'TR' + newMovie.id);

    // adds the <tr> tag to the <tbody> tag
    document.getElementById('item-table-body').appendChild(tr);

    // adding the new movie to the list of all the movies
    allItems.push(newItem);

}
function toggleEditable(row) {
    const isEditable = row.isContentEditable;
    row.contentEditable = !isEditable;

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


function deleteItem(row) {

    let deleteItemId = row.getAttribute('data-item_id');
    let itemMake = row.cells[0].innerText;
    let itemModel = row.cells[1].innerText;
    console.log("++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
    console.log("deleteItemId:" + deleteItemId + " itemMake:" + itemMake + " itemModel:" + itemModel);
    let requestBody = {
        item_id: deleteItemId,
        make: itemMake,
        model: itemModel
    };
    //Send an AJAX request using fetch to delete the warehouse
    fetch(URL + `/car/delete`, {
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
            } else {
                // Handle error response, e.g., display an error message
                console.error('Error deleting car');
            }
        })
        .catch(error => {
            // Handle any network or fetch-related errors
            console.error('Network error:', error);
        });
}



function saveChanges(row) {
    let itemIddata = row.getAttribute('data-item_id');
    console.log("=========" + itemIddata)

    // Capture the updated values from the editable fields
    let updated_Make = row.cells[0].innerText;
    let updated_Model = row.cells[1].innerText;

    // Create an object with the updated data
    let updatedItem = {
        item_id: itemIddata,
        item_make: updated_Make,
        model: updated_Model
    };

    // Send an AJAX request using fetch to update the item
    fetch(URL + `/car/update?id=${updatedItem.item_id}&make=${updatedItem.item_make}&model=${updatedItem.model}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(updatedItem)
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

// Event listener for form submission
document.getElementById('save-item-button').addEventListener('click', addItem);
function addItem(event) {

    event.preventDefault();

    // Capture the values from the form
    const makeInput = document.getElementById('item-make-input');
    const modelInput = document.getElementById('item-model-input');

    const newItem = {
        make: makeInput.value,
        model: modelInput.value
    };
    fetch(URL + `/car/create`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newItem)
    })
        .then(response => {
            if (response.ok) {
                //add item to the form and reset so it shows up on the page
                addItemToTable(newItem);
                document.getElementById('add-item-form').reset();

                // Hide the modal
                $('#addItemModal').modal('hide');
            } else {
                console.error('Error adding item');
            }
        })
        .catch(error => {
            console.error('Network error:', error);
        });
}







