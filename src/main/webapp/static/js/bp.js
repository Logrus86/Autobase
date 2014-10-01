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

    if ( window.Node )
        Node.prototype.removeNode = function( removeChildren )
        {
            var self = this;
            if ( Boolean( removeChildren ) )
            {
                return this.parentNode.removeChild( self );
            }
            else
            {
                var range = document.createRange();
                range.selectNodeContents( self );
                return this.parentNode.replaceChild( range.extractContents(), self );
            }
        };
    if (document.getElementById('admin_bar')) {colorAdminBtns();}
});

function showModalByDefault(form_name) {
    $(form_name).modal(show = true);
}

function setUserStyleClient() {
    document.getElementById("user-form").style.float = "none";
    document.getElementById("user-form").style.margin = 0;
}

function prepareGetRequest() {
    if (!document.getElementById('isModel').checked) {document.getElementById('modelId').removeNode();}
    else {document.getElementById('isModel').removeNode();}
    if (!document.getElementById('isManuf').checked) {document.getElementById('manufId').removeNode();}
    else {document.getElementById('isManuf').removeNode();}
    if (!document.getElementById('isColor').checked) {document.getElementById('colorId').removeNode();}
    else {document.getElementById('isColor').removeNode();}
    if (!document.getElementById('isFuel').checked) {document.getElementById('fuel').removeNode();}
    else {document.getElementById('isFuel').removeNode();}
    if (!document.getElementById('isMileage').checked) {document.getElementById('mileage').removeNode();}
    else {document.getElementById('isMileage').removeNode();}
    if (!document.getElementById('isNotOlder').checked) {document.getElementById('notOlder').removeNode();}
    else {document.getElementById('isNotOlder').removeNode();}
    if (!document.getElementById('isRent').checked) {document.getElementById('rent').removeNode();}
    else {document.getElementById('isRent').removeNode();}
    if (!document.getElementById('isPassNbus').checked) {document.getElementById('passNbus').removeNode();}
    else {document.getElementById('isPassNbus').removeNode();}
    if (!document.getElementById('isStandN').checked) {document.getElementById('standN').removeNode();}
    else {document.getElementById('isStandN').removeNode();}
    if (!document.getElementById('isDoorsBus').checked) {document.getElementById('doorsBus').removeNode();}
    else {document.getElementById('isDoorsBus').removeNode();}
    if (!document.getElementById('isPassNcar').checked) {document.getElementById('passNcar').removeNode();}
    else {document.getElementById('isPassNcar').removeNode();}
    if (!document.getElementById('isDoorsCar').checked) {document.getElementById('doorsCar').removeNode();}
    else {document.getElementById('isDoorsCar').removeNode();}
    if (!document.getElementById('isPayload').checked) {document.getElementById('payload').removeNode();}
    else {document.getElementById('isPayload').removeNode();}

    if (document.getElementById('car-tab').getAttribute('class') == 'tab-pane fade active in')
        document.getElementById('vhType').value = 'Car';
    if (document.getElementById('bus-tab').getAttribute('class') == 'tab-pane fade active in')
        document.getElementById('vhType').value = 'Bus';
    if (document.getElementById('truck-tab').getAttribute('class') == 'tab-pane fade active in')
        document.getElementById('vhType').value = 'Truck';
}

function colorAdminBtns() {
    if (location.pathname == '/do/login' || location.pathname == '/do/main') {
        document.getElementById('bt_users').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/do/admin-cars') {
        document.getElementById('bt_cars').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/do/admin-buses') {
        document.getElementById('bt_buses').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/do/admin-trucks') {
        document.getElementById('bt_trucks').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/do/admin-colors') {
        document.getElementById('bt_colors').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/do/admin-models') {
        document.getElementById('bt_models').setAttribute('class', 'btn btn-info')
    }
    if (location.pathname == '/do/admin-manufacturers') {
        document.getElementById('bt_manufacturers').setAttribute('class', 'btn btn-info')
    }
}

