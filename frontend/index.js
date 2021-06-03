var output = document.getElementById("page");
var maxPage = 0;

var currentPage = 1;
paginationNumber(10);


function paginationNumber(pageSize){

    $.ajax({
        type: "GET",
        url: 'http://192.168.43.117:8080/vehicles/count',
        data: JSON,
        success: function (data) {
            maxPage = Math.floor(data/pageSize) + 1;
            showPage(1, pageSize);
            var i = 0;
            var pages = document.getElementById("pages");
            while(i < maxPage){
                pages.innerHTML += '<li class="page-item"><a class="page-link textColor" href="javascript:showPage('+(i+1)+', 10);">'+(i+1)+'</a></li>';
                i++;
            }
            pages.innerHTML += '<li class="page-item"><a class="page-link textColor" href="javascript:showPage(currentPage+1, 10);">Следваща</a></li>'

        }


    });


}


function showPage(page, pageSize) {
    if(page <= maxPage && page > 0){

        currentPage = page;
        var output = document.getElementById("page");
        var url = 'http://192.168.43.117:8080/vehicles/?page=' + page - 1 + '&pageSize=' + pageSize;

        $.ajax({
            type: "GET",
            url: 'http://192.168.43.117:8080/vehicles/?page=0&size=10',
            data: JSON,
            success: function (data) {
                var json = data;

                output.innerHTML = '<ul id = "content">';
                var content = document.getElementById("content");
                var paginationContent = document.getElementById("paginationContent");


                var pageNum = 0;
                var count = Object.keys(json).length;
                console.log(count);
                $("#content").empty();
                for (var i = 0; i < count; i++) {
                    var id = json[i].id;
                    var type = json[i].type;
                    pageNum += 1;
                    if(type === "TRAM")content.innerHTML += '<li class="list-item" onclick="parseVehicleNum(this);" value="' + id + '"> <button type="button" class="btn btn-warning btn-lg btn-block tram"><p class="vehicle-num"> <i class="fa fa-bus" class="icon"></i>  </p> <span class="vehicle-num">' + id + '</span> </button></li>'
                    if(type === "BUS")content.innerHTML += '<li class="list-item" onclick="parseVehicleNum(this);" value="' + id + '"> <button type="button" class="btn btn-danger btn-lg btn-block bus"><p class="vehicle-num"> <i class="fa fa-bus" class="icon"></i>  </p> <span class="vehicle-num">' + id + '</span> </button></li>'
                    if(type === "SUBWAY")content.innerHTML += '<li class="list-item" onclick="parseVehicleNum(this);" value="' + id + '"> <button type="button" class="btn btn-primary btn-lg btn-block trol"><p class="vehicle-num"> <i class="fa fa-bus" class="icon"></i>  </p> <span class="vehicle-num">' + id + '</span> </button></li>'
                }
                output.innerHTML += '</ul>';
            }
        });

    }
}

showPage(1, 10);

function parseVehicleNum(param) {
    alert(param.value);
    var vehicleNum = param.value;
    window.location = './second.html?vehicleNum=' + vehicleNum;
}


function searchVehicle(){
    var output = document.getElementById("page");
    var vehicleNum = document.getElementById("searchBar").value;

    $.ajax({
        type: "GET",
        url: "http://192.168.43.117:8080/vehicles/" + vehicleNum,
        data: JSON,
        success: function (data) {
            var json = data;
            output.innerHTML = "";
            output.innerHTML += '<ul id = "content">';
            var content = document.getElementById("content");
            var paginationContent = document.getElementById("paginationContent");


            var pageNum = 0;
            var count = Object.keys(json).length;
            console.log(count);
            var id = json.id;
            var type = json.type;
            if(type === "TRAM")content.innerHTML += '<li class="list-item" onclick="parseVehicleNum(this);" value="' + id + '"> <button type="button" class="btn btn-warning btn-lg btn-block tram"><p class="vehicle-num"> <i class="fa fa-bus" class="icon"></i>  </p> <span class="vehicle-num">' + id + '</span> </button></li>'
            if(type === "BUS")content.innerHTML += '<li class="list-item" onclick="parseVehicleNum(this);" value="' + id + '"> <button type="button" class="btn btn-danger btn-lg btn-block bus"><p class="vehicle-num"> <i class="fa fa-bus" class="icon"></i>  </p> <span class="vehicle-num">' + id + '</span> </button></li>'
            if(type === "SUBWAY")content.innerHTML += '<li class="list-item" onclick="parseVehicleNum(this);" value="' + id + '"> <button type="button" class="btn btn-primary btn-lg btn-block trol"><p class="vehicle-num"> <i class="fa fa-bus" class="icon"></i>  </p> <span class="vehicle-num">' + id + '</span> </button></li>'
            output.innerHTML += '</ul>';
        }
    });


}