
function loadNavbar() {
    $("#navbar-placeholder0").load("/navbar.html"); //load
}
$(document).ready(function(){
 // $("#navbar-placeholder").load("/navbar.html");
     loadNavbar();
    $('#registerBtn').on('click',function(){
        alert('Register Button clicked 123 ');
          window.location.href = '/register'
    });
    $('#usetDetailsBtn').on('click',function(){
            alert('Register Button clicked 123 ');
              window.location.href = '/userDetailsPage'
        });
         $('#menuCreate').on('click',function(){

                      window.location.href = '/manuCreate'
                });
                 $('#navbars').on('click',function(){
                 alert("nav click")
                   window.location.href = '/navbars'
                 });

    $('#loginBtn').on('click',function(){
        alert('Login Button clicked')
          window.location.href = '/login';
    });
});