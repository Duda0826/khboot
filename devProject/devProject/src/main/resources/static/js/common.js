function checkData(selector, message){
    const  $element = $(selector);

    if($element.val().trim() === ""){
        alert(`${message} 입력해주세요`);
        element.val = "";
        element.focus();
        return false;
    }
    return true;
}

const actionProcess = (selector, method, action) => {
const $form = $(selector);
$form.attr({
    "method":method,
    "action":action
});
$form.submit();
};

const resetProcess = (selector) => {
const $form = $(selector);
$form[0].reset();
};

