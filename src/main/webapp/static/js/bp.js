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
    $('#modalOrderForm').on('hidden.bs.modal', function () {
        document.getElementById("dayCount").value = "";
        document.getElementById("msg_o").innerText = "";
    });

    if (document.getElementById("vehicles-form")) document.getElementById("user-form").style.float = "right";
    if (document.getElementById('admin_bar')) {colorAdminButtons();}
});

function showModalByDefault(form_name) {
    $(form_name).modal(show = true);
}

function prepareGetRequest() {
    if (!document.getElementById('isModel').checked) {document.getElementById('modelId').name = "";}
    else {document.getElementById('isModel').name = "";}
    if (!document.getElementById('isManuf').checked) {document.getElementById('manufId').name = "";}
    else {document.getElementById('isManuf').name = "";}
    if (!document.getElementById('isColor').checked) {document.getElementById('colorId').name = "";}
    else {document.getElementById('isColor').name = "";}
    if (!document.getElementById('isFuel').checked) {document.getElementById('fuel').name = "";}
    else {document.getElementById('isFuel').name = "";}
    if (!document.getElementById('isMileage').checked) {document.getElementById('mileage').name = "";}
    else {document.getElementById('isMileage').name = "";}
    if (!document.getElementById('isNotOlder').checked) {document.getElementById('notOlder').name = "";}
    else {document.getElementById('isNotOlder').name = "";}
    if (!document.getElementById('isRent').checked) {document.getElementById('rent').name = "";}
    else {document.getElementById('isRent').name = "";}
    if (!document.getElementById('isPassNbus').checked) {document.getElementById('passNbus').name = "";}
    else {document.getElementById('isPassNbus').name = "";}
    if (!document.getElementById('isStandN').checked) {document.getElementById('standN').name = "";}
    else {document.getElementById('isStandN').name = "";}
    if (!document.getElementById('isDoorsBus').checked) {document.getElementById('doorsBus').name = "";}
    else {document.getElementById('isDoorsBus').name = "";}
    if (!document.getElementById('isPassNcar').checked) {document.getElementById('passNcar').name = "";}
    else {document.getElementById('isPassNcar').name = "";}
    if (!document.getElementById('isDoorsCar').checked) {document.getElementById('doorsCar').name = "";}
    else {document.getElementById('isDoorsCar').name = "";}
    if (!document.getElementById('isPayload').checked) {document.getElementById('payload').name = "";}
    else {document.getElementById('isPayload').name = "";}

    if (document.getElementById('car-tab').getAttribute('class') == 'tab-pane fade active in')
        document.getElementById('vhType').value = 'CAR';
    if (document.getElementById('bus-tab').getAttribute('class') == 'tab-pane fade active in')
        document.getElementById('vhType').value = 'BUS';
    if (document.getElementById('truck-tab').getAttribute('class') == 'tab-pane fade active in')
        document.getElementById('vhType').value = 'TRUCK';
}

function colorAdminButtons() {
    if (location.pathname == '/Autobase-1.0/do/admin_users'
        || location.pathname == '/Autobase-1.0/do/create_user'
        || location.pathname == '/Autobase-1.0/do/change_user') {
        document.getElementById('bt_users').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/Autobase-1.0/do/admin_cars'
        || location.pathname == '/Autobase-1.0/do/create_car'
        || location.pathname == '/Autobase-1.0/do/change_car') {
        document.getElementById('bt_cars').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/Autobase-1.0/do/admin_buses'
        || location.pathname == '/Autobase-1.0/do/create_bus'
        || location.pathname == '/Autobase-1.0/do/change_bus') {
        document.getElementById('bt_buses').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/Autobase-1.0/do/admin_trucks'
        || location.pathname == '/Autobase-1.0/do/create_truck'
        || location.pathname == '/Autobase-1.0/do/change_truck') {
        document.getElementById('bt_trucks').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/Autobase-1.0/do/admin_colors'
        || location.pathname == '/Autobase-1.0/do/create_color'
        || location.pathname == '/Autobase-1.0/do/change_color') {
        document.getElementById('bt_colors').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/Autobase-1.0/do/admin_models'
        || location.pathname == '/Autobase-1.0/do/create_model'
        || location.pathname == '/Autobase-1.0/do/change_model') {
        document.getElementById('bt_models').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/Autobase-1.0/do/admin_manufacturers'
        || location.pathname == '/Autobase-1.0/do/create_manufacturer'
        || location.pathname == '/Autobase-1.0/do/change_manufacturer') {
        document.getElementById('bt_manufacturers').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/Autobase-1.0/do/admin_orders'
        || location.pathname == '/Autobase-1.0/do/main'
        || location.pathname == '/Autobase-1.0/do/login') {
        document.getElementById('bt_orders').setAttribute('class', 'btn btn-info')
    }
}

function orderDataToModalForm(id, rent, balance) {
    document.getElementById('vhId').value = id;
    document.getElementById('vhRent').value = rent;
    document.getElementById('dayCount').max = (balance - balance % rent) / rent;
    document.getElementById('dayCount').placeholder = 'Max - '+ (balance - balance % rent) / rent;
}
