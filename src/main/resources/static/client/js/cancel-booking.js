$(".area-cancel").on("click", function () {
  let idAppointment = $(this).data("id");

  Swal.fire({
    title: "Bạn có chắc chắn muốn hủy lịch khám?",
    text: "Hành động này không thể hoàn tác!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#3085d6",
    cancelButtonText: "Bỏ qua!",
    confirmButtonText: "Hủy lịch khám!",
  }).then((result) => {
    if (result.isConfirmed) {
      cancelBooking(idAppointment);
    }
  });
});

function cancelBooking(appointmentID) {
  fetch("/client/appointments/cancel/" + appointmentID, {
    method: "POST",
  })
    .then((respone) => {
      if (respone.status == 200) {
        return respone.json();
      } else {
        Swal.fire({
          title: "LỖI HỆ THỐNG!",
          text: "VUI LÒNG THỬ LẠI SAU.",
          icon: "error",
        });
      }
    })
    .then((data) => {
      if (data.status === "NOT_FOUND") {
        Swal.fire({
          title: "HỦY LỊCH KHÁM THẤT BẠI!",
          text: "Không tìm thấy lịch khám.",
          icon: "error",
        });
      } else {
        $("#schedule_not_finish_" + appointmentID).remove();
        let unexaminedSchedules = $("#unexamined-schedules");
        Swal.fire({
          title: "HỦY LỊCH KHÁM THÀNH CÔNG!",
          icon: "success",
        }).then(() => {
          window.location.reload()
        });
      }
    });
}
