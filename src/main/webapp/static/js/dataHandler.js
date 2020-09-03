function apiGet(url, callback) {
    fetch(url, {method: 'GET'})
        .then(response => response.json())
        .then(jsonData => callback(jsonData));
}

function getProductsForCategory(categoryName, callback) {
    url = "/api/category?name=" + categoryName;
    this.apiGet(url, (response) => callback(response));
}

function apiPost(url, data, callback) {
    // it is not called from outside
    // sends the data to the API, and calls callback function
    fetch(url, {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    })

        .then(response => response.json())
        .then(json_response => callback(json_response))
        .catch(error => console.error(error))
}

function addLineItemToOrder(productId, callback) {
    url = "/api/add-new-line-item?id=" + productId;
    this.apiGet(url, (response) => {
        callback(response)
    })
}

function modifyQuantity(productId, userId, modification, callback) {
    url = "/api/quantity?productid=" + productId + "&userid=" + userId + "&modification=" + modification;
    this.apiGet(url, (response) => {
        callback(response)
    })
}