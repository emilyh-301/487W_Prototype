function deleteItem(itemid){
    fetch('/menu/deleteItem/' + itemid)
        .then(response => {
            location.reload();
        });
}

$(document).ready( function(){
    $(".download-excel-btn").on('click',function(){
        $("#log").table2excel({
            // exclude CSS class
            exclude: ".noExl",
            name: "report",
            filename: $(this).data("file-name"), //do not include extension
            fileext: ".xls" // file extension
        });
    });
});