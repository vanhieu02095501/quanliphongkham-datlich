$(function() {
    $('#loginForm').validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6
            }
        },
        messages: {
            email: {
                required: "Vui lòng nhập email.",
                email: "Email không hợp lệ."
            },
            password: {
                required: "Vui lòng nhập mật khẩu.",
                minlength: "Mật khẩu phải có ít nhất 6 ký tự."
            }
        },
        highlight: function(element) {
            $(element).addClass('input-error');
        },
        unhighlight: function(element) {
            $(element).removeClass('input-error');
        }
    });
})

  
