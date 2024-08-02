$(document).ready(function() {

    $.ajax({
        url: '/getAllUser',
        method: 'GET',
        success: function(data) {
            var userDetails = JSON.parse(data);
            var users = userDetails.users;
            var tableBody = $('#tableBody');

            // Clear any existing rows in case of reloading
            tableBody.empty();

            if (users.length === 0) {
                // No data found
                tableBody.append('<tr><td colspan="7" class="text-center">No data found</td></tr>');
            } else {
                // Populate table with user data
                users.forEach(function(user) {
                    var row = $('<tr></tr>');
                    // Hidden input for the user ID
                    var hiddenId = $('<input>', {
                        type: 'hidden',
                        class: 'userId',
                        value: user.id
                    });
                    row.append(hiddenId);

                    row.append('<td>' + user.name + '</td>');
                    row.append('<td>' + user.email + '</td>');
                    row.append('<td>' + user.dob + '</td>');
                    row.append('<td>' + user.gender + '</td>');
                    row.append('<td>' + user.country + '</td>');

                    // Append image and download icon
                    var imageCell = $('<td></td>');
                    var img = $('<img>', {
                        class:'displayImg',
                        src: '/getProfilePhoto/' + user.photo,
                        alt: 'photo',
                        style: 'width:100px;height:100px;'
                    });
                    var downloadIcon = $('<i>', {
                        class: 'bi bi-download',
                        style: 'display: block; margin-top: 5px; cursor: pointer;',
                        click: function() {
                            // Trigger download
                            window.location.href = '/getProfilePhoto/' + user.photo;
                        }
                    });

                    imageCell.append(img);
                    //imageCell.append(downloadIcon);
                    row.append(downloadIcon)
                    row.append(imageCell);

                    row.append('<td><button class="btn btn-secondary updateBtn">Update</button></td>');

                    tableBody.append(row);
                });

                $('#userTables').DataTable();

                // Attach click event listener to each update button
                $('.updateBtn').on('click', function() {
                    var row = $(this).closest('tr');
                    var rowData = {
                         id: row.find('.userId').val(),
                        name: row.find('td:eq(0)').text(),
                        email: row.find('td:eq(1)').text(),
                        dob: row.find('td:eq(2)').text(),
                        gender: row.find('td:eq(3)').text(),
                        country: row.find('td:eq(4)').text(),
                        photo: row.find('td:eq(5)').find('img').attr('src')
                    };
                    console.log(rowData);
                });
            }
        },
        error: function() {
            alert('Failed to fetch users.');
        }
    });

});
