var openPatientModalBtn = document.querySelectorAll('.openPatientModal');

// Lặp qua từng button và thêm sự kiện click
openPatientModalBtn.forEach(function(btn) {
    btn.addEventListener('click', function() {
        // Mở modal tương ứng
        openModalPatient();
    });
});

// Hàm mở modal
function openModalPatient() {
    // Lấy phần tử modal
    var patientModal = document.getElementById("patientModal");

    // Hiển thị modal bằng cách đặt thuộc tính display thành "block"
    patientModal.style.display = "block";

    var closePatientBtn = document.querySelector('.patientClose');

    // Gán sự kiện click cho nút đóng modal
    closePatientBtn.addEventListener('click', function() {
        // Ẩn modal khi nút đóng được click
        closePatientModal();
    });

}

// Hàm ẩn modal
function closePatientModal() {
    // Lấy phần tử modal
    var patientModal = document.getElementById("patientModal");

    // Ẩn modal bằng cách đặt thuộc tính display thành "none"
    patientModal.style.display = "none";
}