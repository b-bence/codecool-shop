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
<div class="card-body credit-card-body" style="height: 350px">
    <h2>Credit Card payment:</h2>
    <br>
    <div class="form-group"> <label for="cc-number" class="control-label">Card number</label> <input id="cc-number" type="tel" class="input-lg form-control cc-number" autocomplete="cc-number" placeholder="•••• •••• •••• ••••" required> </div>
<div class="row">
    <div class="col-md-6">
    <div class="form-group"> <label for="cc-exp" class="control-label">Card Expiry</label> <input id="cc-exp" type="tel" class="input-lg form-control cc-exp" autocomplete="cc-exp" placeholder="•• / ••" required> </div>
</div>
<div class="col-md-6">
    <div class="form-group"> <label for="cc-cvc" class="control-label">Card CVC</label> <input id="cc-cvc" type="tel" class="input-lg form-control cc-cvc" autocomplete="off" placeholder="••••" required> </div>
</div>
</div>
<div class="form-group">
    <label class="control-label">CARD HOLDER NAME</label>
<input type="text" class="input-lg form-control">
    </div>
    <div class="form-group">
    <a href="/confirmation"><input value="Pay" type="button" class="btn btn-success btn-lg form-control" style="font-size: .8rem;"></a>
    </div>
    </div>
`

    let cardWindow = document.querySelector(".col-sm-8");

    creditCard.addEventListener("click", e => {
        if(cardWindow.childElementCount === 2){
            cardWindow.innerHTML += creditCardWindow;
            toggleCreditCardWindow();
            togglePayPalWindow();
            document.querySelector("#credit-card").checked = true;
        }else {
            if (cardWindow.children[2].classList.contains("pay-pal-body")){
                cardWindow.removeChild(cardWindow.children[2]);
                cardWindow.innerHTML += creditCardWindow;
                toggleCreditCardWindow();
                togglePayPalWindow();
                document.querySelector("#credit-card").checked = true;

            }else {
                cardWindow.removeChild(cardWindow.children[2]);
                document.querySelector("#credit-card").checked = false;
            }
        }

    })

}


function togglePayPalWindow() {
    let payPal = document.querySelector("#pay-pal");
    let payPalWindow =
        `<div class="pay-pal-body card-body" style="height: 350px">
    <h2>PayPal payment:</h2>
    <h5>Please Log In or <a href="https://www.paypal.com/us/webapps/mpp/account-selection">Sign up</a></h5>
    <br>
    <div class="form-group">
    <label for="pp-number" class="control-label">Username</label>
    <input id="pp-number" type="text" class="input-lg form-control cc-number" placeholder="Username" required>
</div>
<div class="form-group">
    <label class="control-label">Password</label>
    <input type="password" class="input-lg form-control" placeholder="********">
    </div>
    <div class="form-group">
    <a href="/confirmation"><input value="Pay" type="button" class="btn btn-success btn-lg form-control" style="font-size: .8rem;"></a>
    </div>
    </div>`

    let cardWindow = document.querySelector(".col-sm-8");

    payPal.addEventListener("click", e => {
        if(cardWindow.childElementCount === 2){
            cardWindow.innerHTML += payPalWindow;
            togglePayPalWindow();
            toggleCreditCardWindow();
            document.querySelector("#pay-pal").checked = true;

        }else {
            if (cardWindow.children[2].classList.contains("credit-card-body")){
                cardWindow.removeChild(cardWindow.children[2]);
                cardWindow.innerHTML += payPalWindow;
                toggleCreditCardWindow();
                togglePayPalWindow();
                document.querySelector("#pay-pal").checked = true;

            }else {
                cardWindow.removeChild(cardWindow.children[2]);
                document.querySelector("#pay-pal").checked = false;
            }
        }
    })

}


