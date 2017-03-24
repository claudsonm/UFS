$('#multifield').multifield({
    section: '.group',
    btnAdd:'#btnAdd',
    btnRemove:'.btnRemove',
});

$(document).ready(function() {
    $('body').on('change', '.select-venda', function(e) {
        var select = $(this);
        var id = $(this).val();
        $.ajax({
            url: '/produtos/'+ id +'/preco',
            type: 'GET',
            success: function (data) {
                select.parent().parent().find('input.preco').val(data);
            }
        });
    });
});