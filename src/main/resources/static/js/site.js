function setPicker(){
    $( "#datepicker" ).datepicker(
        { dateFormat: 'yy-mm-dd',
            onSelect: function(d,i) {
                if (d !== i.lastVal) {
                    reloadPageForDateSelection();
                }
            }
        });
};

function getRequestParam(p){
    return (window.location.search.match(new RegExp('[?&]' + p + '=([^&]+)')) || [, null])[1];
};

function setInitialDate(){
    var requestDate = getRequestParam('date');
    if(requestDate == null){
        requestDate = new Date();
    }else{
        requestDate = formatDate(requestDate);
    }
    $('#datepicker').datepicker('setDate', requestDate);

};

function reloadPageForDateSelection(){
    var selectedDate = document.getElementById('datepicker').value;
    var redirectLink = window.location.protocol + "//" + window.location.host + window.location.pathname + '?date=' + selectedDate;
    console.log('Redirecting to: ' + redirectLink);
    window.location.href = redirectLink;
};

function formatDate(input) {
    var dateFormat = 'yyyy-mm-dd';
    var parts = input.match(/(\d+)/g),
        i = 0, fmt = {};
    dateFormat.replace(/(yyyy|dd|mm)/g, function(part) { fmt[part] = i++; });

    return new Date(parts[fmt['yyyy']], parts[fmt['mm']]-1, parts[fmt['dd']]);
};

function selectPlace(placeId, placeName, placeLocation, placeUrl, actionType) {
    var redirectLink = window.location.protocol + "//" + window.location.host + "/findbus?" +
        "placeid=" + placeId +
        "&placename=" + placeName +
        "&placeLocation=" + placeLocation +
        "&placeurl=" + placeUrl +
        "&actiontype=" + actionType;
    window.location.href = redirectLink;
}


function bookTicket(busId) {
    var adults = document.getElementById('adults').value;
    var children = document.getElementById('children').value;
    var redirectLink = window.location.protocol + "//" + window.location.host + "/bookticket?" +
        "busid=" + busId +
        "&children=" + children +
        "&adults=" + adults;
    window.location.href = redirectLink;
}


function checkBusAvailability(placeId, placeName, placeLocation, placeUrl, actionType) {
    var source = document.getElementById('source').value;
    var redirectLink = window.location.protocol + "//" + window.location.host + "/findbus?" +
        "placeid=" + placeId +
        "&placename=" + placeName +
        "&placeLocation=" + placeLocation +
        "&placeurl=" + placeUrl +
        "&actiontype=" + actionType +
        "&source=" + source;
    window.location.href = redirectLink;
}


var popoverContent = `<form action="/seats" method="get">
                            <button class="btn btn-primary btn-sm" type="submit" name="count" value="1">1</button>
                            <button class="btn btn-primary btn-sm" type="submit" name="count" value="2">2</button>
                            <button class="btn btn-primary btn-sm" type="submit" name="count" value="3">3</button>
                            <button class="btn btn-primary btn-sm" type="submit" name="count" value="4">4</button>
                            <button class="btn btn-primary btn-sm" type="submit" name="count" value="5">5</button>
                      </form>`;

function setUpDatePicker() {
    $( ".datepicker" ).datepicker();
};

$(document).ready(function(){
    setUpDatePicker();
    setPicker();
    setInitialDate();
    $('[data-toggle="popover"]').popover({
                                             container: 'body',
                                             html:true,
                                             animation: true,
                                             content: popoverContent
                                           });
    //$(".bookButton").click(function() {

    //}
    //);
});