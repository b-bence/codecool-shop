
addEventListenerToCheckbox();

function addEventListenerToCheckbox(){
    let checkbox = document.querySelector("#checkbox")
    let shipping = document.querySelector("#shipping")
    toggleDisplay(checkbox, shipping)

    checkbox.addEventListener("click", toggleDisplay.bind(this, checkbox, shipping))
}

function toggleDisplay(checkbox, shipping){
    if (checkbox.checked){
        shipping.style.display = "none";
    }else{
        shipping.style.display = "block"
    }
}

