$(document).ready(function(){
    $('#registerBtn').on('click',function(){
        alert('Register Button clicked');
          window.location.href = '/register'
    });

    $('#loginBtn').on('click',function(){
        alert('Login Button clicked')
          window.location.href = '/login';
    });
});