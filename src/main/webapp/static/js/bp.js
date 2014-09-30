$(document).ready(function () {
    //clearings of all modal form while hiding
    $('#modalRegForm').on('hidden.bs.modal', function () {
        document.getElementById("firstname").value = "";
        document.getElementById("lastname").value = "";
        document.getElementById("dob").value = "";
        document.getElementById("username").value = "";
        document.getElementById("email_r").value = "";
        document.getElementById("msg").innerText = "";
    });
    $('#modalCreateBus').on('hidden.bs.modal', function () {
        document.getElementById("year_prod_b").value = "";
        document.getElementById("mileage_b").value = "";
        document.getElementById("rentPrice_b").value = "";
        document.getElementById("passN_b").value = "";
        document.getElementById("standN_b").value = "";
        document.getElementById("msg_b").innerText = "";
    });
    $('#modalCreateCar').on('hidden.bs.modal', function () {
        document.getElementById("year_prod_c").value = "";
        document.getElementById("mileage_c").value = "";
        document.getElementById("rentPrice_c").value = "";
        document.getElementById("msg_c").innerText = "";
    });
    $('#modalCreateColor').on('hidden.bs.modal', function () {
        document.getElementById("valueRu").value = "";
        document.getElementById("valueEn").value = "";
        document.getElementById("msg_cl").innerText = "";
    });
    $('#modalCreateManufacturer').on('hidden.bs.modal', function () {
        document.getElementById("value_mn").value = "";
        document.getElementById("msg_mn").innerText = "";
    });
    $('#modalCreateModel').on('hidden.bs.modal', function () {
        document.getElementById("value_md").value = "";
        document.getElementById("msg_md").innerText = "";
    });
    $('#modalCreateTruck').on('hidden.bs.modal', function () {
        document.getElementById("year_prod_t").value = "";
        document.getElementById("mileage_t").value = "";
        document.getElementById("rentPrice_t").value = "";
        document.getElementById("maxPayload_t").value = "";
        document.getElementById("msg_t").innerText = "";
    });
    $('#modalCreateUser').on('hidden.bs.modal', function () {
        document.getElementById("firstname_u").value = "";
        document.getElementById("lastname_u").value = "";
        document.getElementById("dob_u").value = "";
        document.getElementById("username_u").value = "";
        document.getElementById("email_u").value = "";
        document.getElementById("balance_u").value = "";
        document.getElementById("msg_u").innerText = "";
    });
    // if driver-form isn't here but user-form is here, changing user-form' style to correct view at client's cabinet
    (document.getElementById("driver-form") == null) ? document.getElementById("user-form") && setUserStyleClient() : {};

});

function showModalByDefault(form_name) {
    $(form_name).modal(show = true);
}

function setUserStyleClient() {
    document.getElementById("user-form").style.float = "none";
    document.getElementById("user-form").style.margin = 0;
}