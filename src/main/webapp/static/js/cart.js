init();

let userId = document.querySelector("table").dataset.userId;

function init() {
    addEventToMinusQuantityButton();
    addEventToPlusQuantityButton();
    addEventToDustbins();
}

function addEventToMinusQuantityButton() {
    let minusQuantityButtons = document.querySelectorAll(".minus-quantity");
    for (let minusButton of minusQuantityButtons) {
        minusButton.addEventListener("click", () => {
            let productId = minusButton.parentElement.dataset.productId;
            let userId = document.querySelector("table").dataset.userId;
            let quantity = parseInt(minusButton.parentElement.parentElement.children[2].innerText);
            if (quantity == 1) {
                let confirmation = confirm("Are you sure you want to delete this item from the cart?");
                if (confirmation == false) return;
            }
            modifyQuantity(productId, userId, "decrease", (response) => {
                // let quantity = response.quantity;
                // if (quantity == 0) {
                //     location.reload();
                // } else {
                //     let quantityTd = minusButton.parentElement.parentElement.children[2];
                //     quantityTd.innerText = quantity;
                // }
                location.reload();
            })
        })
    }
}

function addEventToPlusQuantityButton() {
    let plusQuantityButtons = document.querySelectorAll(".plus-quantity");
    for (let plusButton of plusQuantityButtons) {
        plusButton.addEventListener("click", () => {
            let productId = plusButton.parentElement.dataset.productId;
            modifyQuantity(productId, userId, "increase", (response) => {
                // let quantity = response.quantity;
                // let quantityTd = plusButton.parentElement.parentElement.children[2];
                // quantityTd.innerText = quantity;
                location.reload();
            })
        })
    }
}

function addEventToDustbins() {
    let dustbins = document.querySelectorAll(".dustbin");
    for (let dustbin of dustbins) {
        let productId = dustbin.parentElement.dataset.productId;
        dustbin.addEventListener("click", () => {
            let confirmation = confirm("Are you sure you want to delete this item from the cart?");
            if (confirmation == false) return;
            modifyQuantity(productId, userId, "deleteall", () => location.reload())
        });
    }
}