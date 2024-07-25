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
     alert('Save Button clicked')
        var name =$('#name').val();
        var email =$('#email').val();
        var dob =$('#datepicker').val();
        var radioValue = $("input[name='gender']:checked").val();
        var country =$('#country').val();
        var imageUpload = $('#imageUpload')[0].files[0];


    });
});