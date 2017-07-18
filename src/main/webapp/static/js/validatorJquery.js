const ELEMENT_NAME_FIRST_NAME = 'first_name';
const ELEMENT_NAME_LAST_NAME = 'last_name';
const ELEMENT_NAME_EMAIL_ADDR = 'email_address';
const ELEMENT_NAME_USER_PASS = 'user_password';
const ELEMENT_NAME_RETYPE_PASS = 'retype_password';
const ELEMENT_NAME_AGREEMENT = 'agreement';

const DIV_ERROR_CLASS = '.error-message';
const BORDER_ERROR_CLASS = '.error-border';
const BORDER_ERROR = 'error-border';

const ERROR_MESSAGE_NAME = 'Name should contain only symbols and min length is 3';
const ERROR_MESSAGE_EMAIL = 'Email should have appropriate domain address';
const ERROR_MESSAGE_PASSWORD = 'Password should contains 8 characters (at least 1 Alphabet and 1 Number)';
const ERROR_MESSAGE_RETYPE_PASSWORD = 'Passwords should match';
const ERROR_MESSAGE_AGREEMENT = "You should confirm agreement!";

var validatorsMap = {};
validatorsMap[ELEMENT_NAME_FIRST_NAME] = _validateName;
validatorsMap[ELEMENT_NAME_LAST_NAME] = _validateName;
validatorsMap[ELEMENT_NAME_EMAIL_ADDR] = _validateEmail;
validatorsMap[ELEMENT_NAME_USER_PASS] = _validatePassword;
validatorsMap[ELEMENT_NAME_RETYPE_PASS] = _validateRetypePassword;
validatorsMap[ELEMENT_NAME_AGREEMENT] = _validateAgreement;

var userPassword;

$('#registration_form').on('submit', validateRegisterForm);

function validateRegisterForm(event) {
    var form = $('#registration_form');
    _removeErrorDiv(form);
    var valid = true;

    $(form).find(':input').each(function () {
        var element = $(this);
        var name = $(element).attr('name');
        if (!validatorsMap.hasOwnProperty(name)) {
            return;
        }
        if (msg = validatorsMap[name](element)) {
            _addErrorDiv(element.parent(), msg);
            valid = false;
            event.preventDefault();
        }
    });

    alert('isValid: ' + valid);
    return valid;
}

function _validateName(name) {
    var regex = /^[a-zA-Z\-]{3,}$/;
    if (!regex.test(name.val())) {
        return ERROR_MESSAGE_NAME;
    }
}

function _validateEmail(email) {
    var regex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!regex.test(email.val())) {
        return ERROR_MESSAGE_EMAIL;
    }
}

function _validatePassword(password) {
    userPassword = password.val();
    var regex = /^(?=.*\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{8,}$/;
    if (!regex.test(userPassword)) {
        return ERROR_MESSAGE_PASSWORD;
    }
}

function _validateRetypePassword(retypePassword) {
    if (userPassword != retypePassword.val()) {
        return ERROR_MESSAGE_RETYPE_PASSWORD;
    }
}

function _validateAgreement(agreement) {
    if (!$(agreement).is(':checked')) {
        return ERROR_MESSAGE_AGREEMENT;
    }
}

function _removeErrorDiv(form) {
    var errorDivs = $($(form).find(DIV_ERROR_CLASS));
    $($(form).find(BORDER_ERROR_CLASS)).removeClass(BORDER_ERROR);
    errorDivs.remove();
}

function _addErrorDiv(element, message) {
    var errorDiv = $("<div class='error-message'></div>");
    errorDiv.append($("<span class='error-message'></span>").text(message));
    $(element.find('input')).addClass(BORDER_ERROR);
    element.append(errorDiv);
}
