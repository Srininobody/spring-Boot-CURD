$(document).ready(function() {


    $('#tableView').hide();

    $('#editBtn').on('click',function(){
        $('#tableView').show();
        $('#inputForm').hide();
         $.ajax({
            url: '/getALlManuList',
            method: 'GET',
            success: function(data) {
                var menuLists = JSON.parse(data);
                var menus = menuLists.manuList;
                alert("Manuu = "+menus)
                var tableBody = $('#tableBody');

                // Clear any existing rows in case of reloading
                tableBody.empty();

                if (menus.length === 0) {
                    // No data found
                    tableBody.append('<tr><td colspan="7" class="text-center">No data found</td></tr>');
                } else {
                    menus.forEach(function(menu){
                          var row = $('<tr></tr>');
                          row.append('<td>' + menu.id + '</td>');
                          row.append('<td>' + menu.menuName + '</td>');
                          row.append('<td>' + menu.parentId + '</td>');
                          row.append('<td>' + menu.parantName + '</td>');
                          row.append('<td>' + menu.status + '</td>');
                          row.append('<td><button class="btn btn-secondary updateBtn">Update</button></td>');
                          tableBody.append(row)
                    })
                    $('#userTables').DataTable({
                        "paging": true,
                        "lengthChange": true,
                        "searching": true,
                        "ordering": true,
                        "info": true,
                        "autoWidth": false,
                        "responsive": true,
                         "columnDefs": [
                                            { "className": "r1", "targets": 0},
                                            { "className": "r2", "targets": 1 },
                                            { "className": "r3", "targets": 2 },
                                            { "className": "r4", "targets": 3},
                                            { "className": "r5", "targets": 4 },
                                            { "className": "r6", "targets": 5 }
                                        ]
                    });
                    $('.updateBtn').on('click', function() {
                            var row = $(this).closest('tr');
                            var rowData = {
                                editId: row.find('td:eq(0)').text(),
                                editMenuName: row.find('td:eq(1)').text(),
                                editParentId: row.find('td:eq(2)').text(),
                                editParentName: row.find('td:eq(3)').text(),
                                status: row.find('td:eq(4)').text()
                            };

                            console.log(rowData);
                            alert(rowData.editId);
                            alert(rowData.status);

                            // Set the modal form values
                            $('#editId').val(rowData.editId);
                            $('#editMenuName').val(rowData.editMenuName);

                            // Set the select value and display text
                            var $editParent = $('#editParent');
                            $editParent.empty(); // Clear previous options

                            // Add "No Parent" option
                            $editParent.append(new Option("No Parent", "0"));

                            // Populate the select element and add the default option
                            if (rowData.editParentId !== "0" && rowData.editMenuName !== rowData.editParentName) {
                                $editParent.append(new Option(rowData.editParentName, rowData.editParentId));
                            }

                            // Show the modal
                            $('#updateModal').modal('show');
                        });

                        // Populate the select element when the modal is shown
                        $('#updateModal').on('shown.bs.modal', function() {
                            var $editParent = $('#editParent');

                            // Make an AJAX call to populate the select options
                            $.ajax({
                                url: '/getAllParent', // Adjust this URL based on your actual endpoint
                                type: 'GET',
                                dataType: 'json', // Expecting a JSON response
                                success: function(data) {
                                    var existingValues = $editParent.find('option').map(function() {
                                        return $(this).val();
                                    }).get();

                                    $.each(data, function(index, item) {
                                        // Add option only if it doesn't match `editMenuName` and is not already present
                                        if (item.name !== $('#editMenuName').val() && !existingValues.includes(item.code)) {
                                            var option = $('<option />').val(item.code).text(item.name);
                                            $editParent.append(option);
                                        }
                                    });

                                    // Ensure the default value is still selected
                                    $editParent.val($('#editParent').val());
                                },
                                error: function(jqXHR, textStatus, errorThrown) {
                                    console.error('Failed to fetch parent data:', textStatus, errorThrown);
                                }
                            });
                        });

                }
            }
         })
    })
     $('#editSave').on('click',function(){
        var id =$('#editId').val();
        var editMenuName =$('#editMenuName').val();
        var editParentId =$('#editParent').val();
         var editStatus =$('#editStatus').val();
         console.log("Edit value >>>>  id = "+id+" editMenuName = "+editMenuName+" editParentId= "+editParentId+"editStatus = "+editStatus)

            var formData = new FormData();
             formData.append('id', id);
            formData.append('menuName', editMenuName);
            formData.append('parantId',editParentId);
             formData.append('status', editStatus);
            $.ajax({
                url: '/updateMenu', // Assuming 'saveManuCreate' was a typo and should be 'saveMenuCreate'
                type: 'PUT',
                data: formData,
                dataType: 'text',
                processData: false,
                contentType: false,
                success: function(response) {

                    if(response == 'S')
                    {
                    alert("Menu Created Successfully.");
                      location.reload(); // Refresh the page
                    }
                    else{
                     alert("something Error...");
                    }
                },
                error: function(err) {
                    alert(err);
                }
            });

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

                    if(response == 'S')
                    {
                    alert("Menu Created Successfully.");
                      location.reload(); // Refresh the page
                    }
                    else{
                     alert("something Error...");
                    }
                },
                error: function(err) {
                    alert(err);
                }
            });
        }

    })
});