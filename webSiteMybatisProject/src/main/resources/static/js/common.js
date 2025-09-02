function chkData(selector, message) {
    const element = document.querySelector(selector);
    const value = element.value.trim();

    if (value === "" || value === "0") {
        alert(`${message} 입력해 주세요.`);
        element.value = "";
        element.focus();
        return false;
    }
    return true;
}

const formSubmit = (selector, method, action) => {
    const form = document.getElementById(selector);
    form.method = method;
    form.action = action;
    form.submit();
}

const formReset = (selector) => {
    const form = document.getElementById(selector);
    form.reset();
}

const locationProcess = (url) => {
    location.href = url;
}

function dataCheck(itemSelector, outSelector, msg) {
    const item = document.querySelector(itemSelector);
    const out = document.querySelector(outSelector);
    const value = item.value.trim();

    if (value === "") {
        out.textContent = `${msg} 입력해 주세요`;
        item.value = "";
        item.focus();
        return false;
    }
    return true;
}