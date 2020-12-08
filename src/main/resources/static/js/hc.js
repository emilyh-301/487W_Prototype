function deleteItem(itemid){
    fetch('/menu/deleteItem/' + itemid)
        .then(response => {
            location.reload();
        });
}