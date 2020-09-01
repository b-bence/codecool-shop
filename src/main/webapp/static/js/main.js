init();

function init() {
    initializeHeaders()
}

function initializeHeaders() {
    let categoryHeaders = document.querySelectorAll(".category");
    for (let categoryHeader of categoryHeaders) {
        categoryHeader.addEventListener("click", (event) => filterProducts());
    }
}

function filterProducts() {

}