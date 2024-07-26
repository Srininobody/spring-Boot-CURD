$(document).ready(function() {
    // Initialize Select2 on the country dropdown


    $('#country').select2({
        placeholder: 'Select a country',
        allowClear: true
    });

    var currentYear = new Date().getFullYear();
        $("#datepicker").datepicker({
            dateFormat: "dd/mm/yy",
            changeMonth: true,
            changeYear: true,
            yearRange: "1960:" + (currentYear + 20),  // Sets the year range from 1960 to current year + 20
            minDate: new Date(1960, 0, 1),  // Sets the minimum selectable date to January 1, 1960
            maxDate: new Date(currentYear + 20, 11, 31)  // Sets the maximum selectable date to December 31 of current year + 20
        });

    $('#imageUpload').change(function() {
        const file = this.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onload = function(event) {
                $('#imagePreview').attr('src', event.target.result).removeClass('d-none');
            };
            reader.readAsDataURL(file);
        }
    });
    $('#saveDatas').on('click',function(){
    alert("request sent");

        var name =$('#name').val();
        var email =$('#email').val();
        var dob =$('#datepicker').val();
        var gender = $("input[name='gender']:checked").val();
        var country =$('#country').val();
        var imageUpload = $('#imageUpload')[0].files[0];

        if(name == "" || name == null || name == undefined) {
            alert("Please Enter the employee name");
            $('#name').focus();
            return;
        }
      else if(email == "" || email == null || email == undefined) {
            alert("Please Enter the email ");
            $('#email').focus();
            return;
        }
        else if(dob == "" || dob == null || dob == undefined) {
            alert("Please select the date ");
            $('#datepicker').focus();
            return;
        }
        else if(gender == "" || gender == null || gender == undefined) {
            alert("please select the gender ");
            $('input[name="gender"]').first().focus();
            return;
        }
        else if(country == "" || country == null || country == undefined) {
            alert("Please select the country ");
            $('#country').focus();
            return;
        }
        else if(imageUpload == "" || imageUpload == null || imageUpload == undefined) {
            alert("Please select the image ");
            $('#imageUpload').focus();
            return;
        }
        else{
        alert("Gender = "+gender);
            alert("ajax send to the controller");
         var formData = new FormData();
                formData.append('name', name);
                formData.append('email',email);
                formData.append('dob', dob);
                formData.append('gender', gender);
                formData.append('country', country);
                formData.append('imageUpload', imageUpload);

                $.ajax({
                    url: '/saveData',
                    type: 'POST',
                    data: formData,
                    contentType: false,
                    processData: false,
                     dataType: 'text',
                    success: function (response) {
                      if(response == 'done')
                      {
                      alert('Success:', response);
                      }



                    },
                    error: function (error) {
                        alert('Error:', error);
                    }
                });
        }


    });
});