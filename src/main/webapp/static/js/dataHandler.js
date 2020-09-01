function apiGet(url, callback) {
    fetch(url, {method: 'GET'})
        .then(response => response.json())
        .then(jsonData => callback(jsonData));
}

function getProductsForCategory(categoryName, callback) {
    url = "/api/category?name=" + categoryName;
    this.apiGet(url, (response) => callback(response));
}