
let plusQuantityButtons = document.querySelectorAll(".plus-quantity");

init();

function init() {
    addEventToMinusQuantityButton();
}

function addEventToMinusQuantityButton() {
    let minusQuantityButtons = document.querySelectorAll(".minus-quantity");
    for (let minusButton of minusQuantityButtons) {
        minusButton.addEventListener("click", () => {
            let productId = minusButton.parentElement.dataset.productId;
            let userId = document.querySelector("table").dataset.userId;
            modifyQuantity(productId, userId, "decrease", (response) => {
                let productId = response.productId;
                let quantity = response.quantity;
                if (quantity == 0) {
                    location.reload();
                } else {
                    let quantityTd = minusButton.parentElement.parentElement.children[2];
                    quantityTd.innerText = quantity;
                }
            })
        })
    }
}