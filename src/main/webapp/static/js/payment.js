$(function($) {
    $('[data-numeric]').payment('restrictNumeric');
    $('.cc-number').payment('formatCardNumber');
    $('.cc-exp').payment('formatCardExpiry');
    $('.cc-cvc').payment('formatCardCVC');
    $.fn.toggleInputError = function(erred) {
        this.parent('.form-group').toggleClass('has-error', erred);
        return this;
    };
    $('form').submit(function(e) {
        e.preventDefault();
        var cardType = $.payment.cardType($('.cc-number').val());
        $('.cc-number').toggleInputError(!$.payment.validateCardNumber($('.cc-number').val()));
        $('.cc-exp').toggleInputError(!$.payment.validateCardExpiry($('.cc-exp').payment('cardExpiryVal')));
        $('.cc-cvc').toggleInputError(!$.payment.validateCardCVC($('.cc-cvc').val(), cardType));
        $('.cc-brand').text(cardType);
        $('.validation').removeClass('text-danger text-success');
        $('.validation').addClass($('.has-error').length ? 'text-danger' : 'text-success');
    });
});

init();

function init() {
    toggleCreditCardWindow();
    togglePayPalWindow();
}

function toggleCreditCardWindow() {
    let creditCard = document.querySelector("#credit-card");
    let creditCardWindow = `
<div class="card-body " style="height: 350px">
    <div class="form-group"> <label for="cc-number" class="control-label">CARD NUMBER</label> <input id="cc-number" type="tel" class="input-lg form-control cc-number" autocomplete="cc-number" placeholder="•••• •••• •••• ••••" required> </div>
<div class="row">
    <div class="col-md-6">
    <div class="form-group"> <label for="cc-exp" class="control-label">CARD EXPIRY</label> <input id="cc-exp" type="tel" class="input-lg form-control cc-exp" autocomplete="cc-exp" placeholder="•• / ••" required> </div>
</div>
<div class="col-md-6">
    <div class="form-group"> <label for="cc-cvc" class="control-label">CARD CVC</label> <input id="cc-cvc" type="tel" class="input-lg form-control cc-cvc" autocomplete="off" placeholder="••••" required> </div>
</div>
</div>
<div class="form-group">
    <label class="control-label">CARD HOLDER NAME</label>
<input type="text" class="input-lg form-control">
    </div>
    <div class="form-group">
    <input value="MAKE PAYMENT" type="button" class="btn btn-success btn-lg form-control" style="font-size: .8rem;">
    </div>
    </div>
`

    let cardWindow = document.querySelector(".col-sm-8");

    creditCard.addEventListener("click", e => {
        if(cardWindow.childElementCount === 1){
            cardWindow.innerHTML += creditCardWindow;
            toggleCreditCardWindow();
            togglePayPalWindow();
            document.querySelector("#credit-card").checked = true;
        }else {
            if (cardWindow.children[1].classList.contains("pay-pal-body")){
                cardWindow.removeChild(cardWindow.children[1]);
                cardWindow.innerHTML += creditCardWindow;
                toggleCreditCardWindow();
                togglePayPalWindow();
                document.querySelector("#credit-card").checked = true;

            }else {
                cardWindow.removeChild(cardWindow.children[1]);
                document.querySelector("#credit-card").checked = false;
            }
        }

    })

}


function togglePayPalWindow() {
    let payPal = document.querySelector("#pay-pal");
    let payPalWindow =
        `<div class="pay-pal-body " style="height: 350px">
    <div class="form-group">
    <label for="pp-number" class="control-label">Username</label>
    <input id="pp-number" type="text" class="input-lg form-control cc-number" placeholder="Username" required>
</div>
<div class="form-group">
    <label class="control-label">Password</label>
    <input type="password" class="input-lg form-control">
    </div>
    <div class="form-group">
    <input value="MAKE PAYMENT" type="button" class="btn btn-success btn-lg form-control" style="font-size: .8rem;">
    </div>
    </div>`

    let cardWindow = document.querySelector(".col-sm-8");

    payPal.addEventListener("click", e => {
        if(cardWindow.childElementCount === 1){
            cardWindow.innerHTML += payPalWindow;
            togglePayPalWindow();
            toggleCreditCardWindow();
            document.querySelector("#pay-pal").checked = true;

        }else {
            if (cardWindow.children[1].classList.contains("card-body")){
                cardWindow.removeChild(cardWindow.children[1]);
                cardWindow.innerHTML += payPalWindow;
                toggleCreditCardWindow();
                togglePayPalWindow();
                document.querySelector("#pay-pal").checked = true;

            }else {
                cardWindow.removeChild(cardWindow.children[1]);
                document.querySelector("#pay-pal").checked = false;
            }
        }
    })

}


