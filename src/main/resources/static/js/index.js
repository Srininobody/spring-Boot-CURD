$(document).ready(function(){
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

    $('#loginBtn').on('click',function(){
        alert('Login Button clicked')
          window.location.href = '/login';
    });
});