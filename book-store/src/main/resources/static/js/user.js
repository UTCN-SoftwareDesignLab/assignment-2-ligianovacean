function processSale(sale) {
    $.ajax('/sellBook', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(sale),
        dataType: 'json',
        success: function(result) {
            $('#quantity').val('');
            displayTotal();
        }
    });
}

function displayTotal(){
    $.get('/getTotal', function(result){
        $('#totalSum').val(result);
    })
}


$(function() {
    $('button').click(function() {
        if (this.id == "sellBook") {
            processSale({
                'title':        $('#saleTitle').val(),
                'authorId':     $('#saleAuthor').val(),
                'quantity':     $('#saleQuantity').val()
            });
        };
        return false;
    });
});