
function displayBooks(books) {
    var $tbody = $('tbody');
    $tbody.empty();
    for(var i in books) {
        var book = books[i];
        var $row = $('<tr>');
        $('<td>').html(book.title).appendTo($row);
        $('<td>').html(book.author.name).appendTo($row);
        $('<td>').html(book.genre).appendTo($row);
        $('<td>').html(book.price).appendTo($row);
        $('<td>').html(book.quantity).appendTo($row);

        $row.appendTo($tbody);
    }
}

function refreshBooks() {
    $.get('/books', {}, function(result) {
        displayBooks(result);
    });
}

function addBook(book) {
    $.ajax('/createBooks', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(book),
        dataType: 'json',
        success: function() {
            refreshBooks();
            $('#title, #genre, #price, #quantity').val('');
        }
    });
}

function deleteBook(title, authorName) {
     $.ajax('/deleteBooks', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            data: JSON.stringify(title, authorName),
            dataType: 'json',
            success: function() {
                refreshBooks();
                $('#title, #genre, #price, #quantity').val('');
            }
        });
}


function updateBook(book) {
     $.ajax('/updateBooks', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            data: JSON.stringify(book),
            dataType: 'json',
            success: function() {
                refreshBooks();
                $('#title, #genre, #price, #quantity').val('');
            }
     });
}


function generateCSVReport(report) {
    $.ajax('/generateReport', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(report),
        dataType: 'json',
        success: function() {
            refreshBooks();
        }
    });
}

function generatePDFReport(report) {
    $.ajax('/generateReport', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(report),
        dataType: 'json',
        success: function() {
            refreshBooks();
        }
    });
}



$(function() {
    refreshBooks();
    $('button').click(function() {
        if (this.id == "createBook") {
            addBook({
                'title':     $('#title').val(),
                'authorId':   $('#author').val(),
                'genre':     $('#genre').val(),
                'price':    $('#price').val(),
                'quantity':     $('#quantity').val()
            });
        };
        if (this.id == "deleteBook") {
            deleteBook({
                'title':    $('#bookTitle').val(),
                'authorName':   $('#bookAuthor').val()
            })
        };
        if (this.id == "updateBook") {
            updateBook({
                'title':     $('#updateTitle').val(),
                'authorId':   $('#updateAuthor').val(),
                'genre':     $('#updateGenre').val(),
                'price':    $('#updatePrice').val(),
                'quantity':     $('#updateQuantity').val()
            })};
        if (this.id == "generateCsvReport") {
            generateCSVReport("CSV");
        };
        if (this.id == "generatePdfReport") {
            generatePDFReport("PDF");
        };
        return false;
    });
});