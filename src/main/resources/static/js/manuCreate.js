$(document).ready(function() {
    $('#userTables').DataTable({
        "paging": true,
        "lengthChange": true,
        "searching": true,
        "ordering": true,
        "info": true,
        "autoWidth": false,
        "responsive": true
    });

    $('#tableView').hide();

    $('#editBtn').on('click',function(){
        $('#tableView').show();
        $('#inputForm').hide();
    })
    $('#backBtns').on('click',function(){
        $('#tableView').hide();
        $('#inputForm').show();
    })
$.ajax({
            url: '/getAllParent', // Adjust this URL based on your actual endpoint
            type: 'GET',
            dataType: 'json', // Expecting a JSON response
            success: function(data) {
                var $dropdown = $('#parent');
                $.each(data, function(index, trip) {
                    var option = $('<option />').val(trip.code).text(trip.name);
                    $dropdown.append(option);
                });
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error('Failed to fetch trip codes:', textStatus, errorThrown);
            }
        });
    $('#submitBtn').on('click',function(){
        alert('this submit called..')
        var menuName = $('#menuName').val();
        var parentID = $('#parent').val();
         alert('name = '+menuName +' ParentID = '+parentID);
       // alert('name = '+menuName +' ParentID = '+ParentID);
        if(menuName ==null || menuName == undefined || menuName==''){
            alert('Please Enter the Menu Name');
            $('#menuName').focus();

        }
        else if(parentID ==null || parentID == undefined || parentID=='' || parentID == '-1'){
            alert('Please Select the Parent Id');
            $('#parent').focus();

        }
        else{
            alert('name = '+menuName +' ParentID = '+parentID);

            var formData = new FormData();

            formData.append('menuName', menuName);
            formData.append('parentID',parentID);
            $.ajax({
                url: '/saveMenuCreate', // Assuming 'saveManuCreate' was a typo and should be 'saveMenuCreate'
                type: 'POST',
                data: formData,
                dataType: 'text',
                processData: false,
                contentType: false,
                success: function(response) {
                    alert(response);
                },
                error: function(err) {
                    alert(err);
                }
            });
        }

    })
});