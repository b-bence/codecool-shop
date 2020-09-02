init();

function init() {
    initializeHeaders()
    addProductEventListener()
}

function initializeHeaders() {
    let categoryHeaders = document.querySelectorAll(".category");
    for (let categoryHeader of categoryHeaders) {
        let category = categoryHeader.dataset.category;
        categoryHeader.addEventListener("click", (event) => getProductsForCategory(category, response => filterProducts(response, category)));
    }
}

function filterProducts(response, categoryName) {
    let filteredCategory = document.querySelector("#filtered-category");
    filteredCategory.innerHTML = categoryName;
    generateNewCardContent(response);
}

function generateNewCardContent(productList) {
    let productsDiv = document.querySelector("#products");
    let content = "";
    productList.forEach(prod => {
        content += `<div class="col col-sm-12 col-md-6 col-lg-4">
            <div class="card">
                <img class="" src="/static/img/product_${prod.id}.jpg" src="http://placehold.it/400x250/000/fff" alt="" />
                <div class="card-header">
                    <h4 class="card-title">${prod.name}</h4>
                    <p class="card-text"">${prod.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${prod.defaultPrice} ${prod.defaultCurrency}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success" data-product-id="${prod.id}">Add to cart</a>
                    </div>
                </div>
            </div>
        </div>`
    })
    productsDiv.innerHTML = content;
    addProductEventListener()
}

function addProductEventListener() {
    let addButtons = document.querySelectorAll(".btn-success");
    addButtons.forEach(btn => {
        btn.addEventListener("click", e => {
            addProductToCart(btn.dataset.productId);
        })
    })
}

function addProductToCart(productId) {
    addLineItemToOrder(productId, (response) =>{
        console.log(productId);
        let number = parseInt(document.querySelector("#number-of-items").innerHTML) + 1;
        document.querySelector("#number-of-items").innerHTML = number.toString();
    })
}



