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

                    row.append('<td>' + user.name + '</td>');
                    row.append('<td>' + user.email + '</td>');
                    row.append('<td>' + user.dob + '</td>');
                    row.append('<td>' + user.gender + '</td>');
                    row.append('<td>' + user.country + '</td>');
                    row.append('<td>' + user.photo + '</td>');
                    row.append('<td><button class="btn btn-secondary updateBtn">Update</button></td>');

                    tableBody.append(row);
                });
                $('#userTables').DataTable();

                // Attach click event listener to each update button
                $('.updateBtn').on('click', function() {
                    var row = $(this).closest('tr');
                    var rowData = {
                        name: row.find('td:eq(0)').text(),
                        email: row.find('td:eq(1)').text(),
                        dob: row.find('td:eq(2)').text(),
                        gender: row.find('td:eq(3)').text(),
                        country: row.find('td:eq(4)').text(),
                        photo: row.find('td:eq(5)').text()
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
