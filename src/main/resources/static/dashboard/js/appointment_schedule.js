// Lấy tất cả các button mở modal bằng class
var openModalBtns = document.querySelectorAll('.openModalBtn');
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// Lặp qua từng button và thêm sự kiện click
openModalBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModal();
    });
});

// Hàm mở modal
function openModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("myModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    modal.style.display = "block";
    // Gán sự kiện click cho nút đóng modal
    closeBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeMyModal();
    });

}

// Lấy thẻ button bằng ID
var okButton = document.getElementById("okBtn");

// Thêm sự kiện click cho nút OK
okButton.addEventListener("click", function() {
    // Đóng modal bằng cách thêm hoặc loại bỏ class "modal-open" hoặc sử dụng các phương thức khác để điều khiển modal
    closeMyModal();
});












// Lấy phần tử nút đóng modal
var closeBtn = document.querySelector('.close');


// Hàm ẩn modal
function closeMyModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("myModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    modal.style.display = "none";
}
/* modal send mail*/
// Lấy tất cả các button mở modal bằng class

var openModalSendBtns = document.querySelectorAll('.openModalSendBtn');

// Lặp qua từng button và thêm sự kiện click
openModalSendBtns.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModalSend();
    });
});

function openModalSend() {
    // Lấy phần tử modal
    var modal = document.getElementById("sendModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    modal.style.display = "block";
    // Gán sự kiện click cho nút đóng modal
    closeSendBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closeSendModal();
    });

}



// Lấy phần tử nút đóng modal
var closeSendBtn = document.querySelector('.send-close');


// Hàm ẩn modal
function closeSendModal() {
    // Lấy phần tử modal
    var modal = document.getElementById("sendModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    modal.style.display = "none";

}

// Lấy thẻ button bằng ID
var cancelButton = document.getElementById("cancelBtn");

// Thêm sự kiện click cho nút OK
cancelButton.addEventListener("click", function() {
    // Đóng modal bằng cách thêm hoặc loại bỏ class "modal-open" hoặc sử dụng các phương thức khác để điều khiển modal
    closeSendModal();
});



//
// When the user clicks the button, open the modal and populate fields
// When the user clicks the button, open the modal and populate fields
document.addEventListener("DOMContentLoaded", function() {
    var btns = document.querySelectorAll(".openModalBtn"); // Corrected variable name from "btns" to "btn"

    btns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            var appointmentId = this.getAttribute("data-id");
            var appointmentRow = this.closest("tr");
            var modal = document.getElementById("myModal");

            if (appointmentRow) {
                document.getElementById("name").value = appointmentRow.querySelectorAll("td")[1].innerText;
                // Assuming the gender is in the 4th column
                document.getElementById("phone").value = appointmentRow.querySelectorAll("td")[3].innerText;
                document.getElementById("email").value = appointmentRow.querySelectorAll("td")[4].innerText;
                document.getElementById("date").value = appointmentRow.querySelectorAll("td")[5].innerText;
                document.getElementById("slotTime").value = appointmentRow.querySelectorAll("td")[6].innerText;
                document.getElementById("address").value = appointmentRow.querySelectorAll("td")[7].innerText;
                document.getElementById("symptoms").value = appointmentRow.querySelectorAll("td")[8].innerText;
                // You can similarly populate other fields
                modal.style.display = "block";
            }
        });
    });

    // Close the modal when the close button is clicked
    document.querySelector(".close").addEventListener("click", function() {
        document.getElementById("myModal").style.display = "none";
    });

    // Close the modal when the user clicks anywhere outside of it
    window.onclick = function(event) {
        var modal = document.getElementById("myModal");
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
});

//////////////// send modal
document.addEventListener("DOMContentLoaded", function() {
    var btns = document.querySelectorAll(".openModalSendBtn"); // Corrected variable name from "btns" to "btn"

    btns.forEach(function(btn) {
        btn.addEventListener("click", function() {
            var appointmentId = this.getAttribute("data-id");
            var appointmentRow = this.closest("tr");
            var modal = document.getElementById("sendModal");

            if (appointmentRow) {
                document.getElementById("gmail").value = appointmentRow.querySelectorAll("td")[4].innerText;

                // You can similarly populate other fields
                modal.style.display = "block";
            }
        });
    });

    // Close the modal when the close button is clicked
    document.querySelector(".close").addEventListener("click", function() {
        document.getElementById("sendModal").style.display = "none";
    });

    // Close the modal when the user clicks anywhere outside of it
    window.onclick = function(event) {
        var modal = document.getElementById("sendModal");
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };
});

// xóa appoinment
// Lấy tất cả các nút xóa
// Lấy tất cả các thẻ <a> có class "deleteBtn"
// Lấy tất cả các thẻ <a> có class "deleteBtn"
const deleteButtons = document.querySelectorAll('.deleteBtn');

// Lặp qua mỗi nút và thêm sự kiện click
deleteButtons.forEach(button => {
    button.addEventListener('click', function(event) {
        // Ngăn chặn hành động mặc định của liên kết
        event.preventDefault();

        // Lấy ID từ thuộc tính data-app-id
        const appId = this.getAttribute('data-app-id');

        // Gọi hàm xóa cuộc hẹn với ID đã lấy
        deleteAppointment(appId);
    });
});

// Hàm xóa cuộc hẹn
function deleteAppointment(appId) {
    if (confirm('Are you sure you want to delete this appointment?')) {
        // Thực hiện AJAX DELETE request
        fetch('/doctor/appointments/' + appId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to delete appointment');
                }
                // Xử lý phản hồi nếu cần
                // Ví dụ: cập nhật giao diện người dùng
            })
            .catch(error => {
                console.error('Error deleting appointment:', error);
                // Xử lý lỗi nếu cần
            });
    }
}

$(function() {
    // hiển thị nay hiện tại
    document.addEventListener('DOMContentLoaded', function() {
        var txtSearch = document.getElementById('txtSearch');
        var savedDate = localStorage.getItem('savedDate');
        var savedResults = JSON.parse(localStorage.getItem('savedResults'));

        if (savedDate) {
            txtSearch.value = savedDate;
        } else {
            var today = new Date();
            var day = String(today.getDate()).padStart(2, '0');
            var month = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
            var year = today.getFullYear();

            var todayDate = year + '-' + month + '-' + day;
            txtSearch.value = todayDate;
        }

        if (savedResults) {
            displayResults(savedResults);
        }

        txtSearch.addEventListener('change', function() {
            localStorage.setItem('savedDate', txtSearch.value);
        });

        document.getElementById('searchForm').addEventListener('submit', function(event) {
            event.preventDefault();
            var keyword = txtSearch.value;
            localStorage.setItem('savedDate', keyword);

            fetch(`/doctor/appointments?keyword=${keyword}`)
                .then(response => response.json())
                .then(data => {
                    localStorage.setItem('savedResults', JSON.stringify(data));
                    displayResults(data);
                });
        });
    });


    function getStatusColor(statusName) {
        if (statusName === 'Đang khám') return '#F9AA33';
        if (statusName === 'Hoàn thành') return 'green';
        if (statusName === 'Hủy khám') return 'red';
        return 'black';
    }

})

$(document).ready(function() {
    // Attach click event listener to elements with class 'hover-underline'
    $('.hover-underline').on('click', function() {
        // Get the appointment ID from the clicked element
        const appointmentId = $(this).attr("data-appointment-id");
        // Set the appointment ID in a data attribute of the submit button
        document.getElementById('btn-submit').setAttribute('data-id', appointmentId);
        var appointmentRow = this.closest("tr");



        // Show the modal
        $('#appointmentStatusModal').modal('show');
        //alert('appID: '+ appointmentId)
       // console.log('Click event fired!');
    });
});

function submitAppointmentStatusForm() {
    // Get the appointment ID from the data-id attribute of the submit button
    const appointmentId = document.getElementById('btn-submit').getAttribute('data-id');
    // Get the selected status ID from the select element
    const statusId = document.getElementById('appointmentStatusSelect').value;

    // URL for the PUT request
    const url = "/doctor/appointments/"+appointmentId+"/status1?statusId="+statusId;

    fetch(url, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Network response was not ok');
            }
        })
        .then(data => {
            $("#appointmentStatusModal").toggle()
            $('.modal-backdrop').remove();
            // update status in table

            //get status text
            var statusText = $("#appointmentStatusForm option:selected").text();

            // Đặt văn bản vào span
            var span = $("span[data-appointment-id='" + appointmentId + "']");
            span.text(statusText);

           updateAppointmentColor(appointmentId, statusId); // Đặt màu chữ
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });


}

function updateAppointmentText(appointmentId, statusId) {
    // Lấy văn bản từ option có giá trị statusId
    var statusText = $("#appointmentStatusForm option[value='" + statusId + "']").text();

    // Đặt văn bản vào span
    var span = $("span[data-appointment-id='" + appointmentId + "']");
    span.text(statusText);
}

function updateAppointmentColor(appointmentId, statusId) {
    // Chọn span có data-appointment-id tương ứng
    var span = $("span[data-appointment-id='" + appointmentId + "']");

    // Đặt màu chữ dựa trên statusId
    if (statusId === "1") { // Giả sử statusId của "Scheduled" là "1"
        span.css("color", "#F9AA33"); // Màu cam
    } else if (statusId === "3") { // Giả sử statusId của "Completed" là "2"
        span.css("color", "green"); // Màu xanh lá cây
    } else if (statusId === "4") { // Giả sử statusId của "Canceled" là "3"
        span.css("color", "red"); // Màu đỏ
    } else {
        span.css("color", "black"); // Màu mặc định
    }
}
/////////////////////Gửi mail hóa đơn
// Function to open modal and set appId
$(document).ready(function() {
    // Attach click event listener to elements with class 'button-send'
    $('.button-send').on('click', function () {
        // Get the appointment ID from the clicked element
        const appointmentId = $(this).data("appointment-id")

        // Set the appointment ID in a data attribute of the submit button
        document.getElementById('sendBtn').setAttribute('data-id', appointmentId);

        // Call the API to get the user email based on the appointment ID
        $.ajax({
            type: 'GET',
            url: '/doctor/appointments/email/' + appointmentId,
            success: function (response) {
                // Assuming the response contains the user's email address in the 'email' field
                const userEmail = response.email;

                // Set the fetched email address in the readonly input field
                $('#emaila').val(userEmail);

                // Show the modal
                $('#sendModal').show();
            },
            error: function (xhr, status, error) {
                console.error('Error fetching user details:', status, error);
            }
        });
    });
});


    function sendInvoice(button) {
        // Lấy appointmentId từ data-id của button
        var appointmentId = button.getAttribute("data-id");

        // Kiểm tra nếu có appointmentId
        if (appointmentId) {
            // Gọi đến đoạn mã JavaScript để gửi yêu cầu đến server
            fetch(`/doctor/appointments/invoice?appointmentId=${appointmentId}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to send invoice');
                    }
                    // Không cần xử lý phản hồi JSON, chỉ cần kiểm tra trạng thái
                    console.log('Invoice sent successfully');
                    // Thực hiện các hành động cần thiết sau khi gửi hoá đơn thành công
                    // Redirect lại trang để cập nhật thay đổi
                    // window.location.href = "/doctor/appointments/page/";
                    window.location.reload()
                })
                .catch(error => {
                    console.error('Error:', error);
                    // Xử lý lỗi nếu có
                });
        }
    }

// Hàm để đóng modal
    function closeModal() {
        $('#sendModal').hide();  // Thay thế .modal('hide') bằng .hide()
    }

// Đóng modal khi nhấn vào biểu tượng đóng
