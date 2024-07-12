$(document).ready(function() {
    assignDataToTable();

    $('.openDoctorModalBtn').on('click', function() {
        openModalDoctor();
    });

    function openModalDoctor() {
        var doctorModal = document.getElementById("doctorModal");
        doctorModal.style.display = "block";

        var closeDoctorBtn = document.querySelector('.doctorClose');
        closeDoctorBtn.addEventListener('click', function() {
            closeDoctorModal();
        });
    }

    function closeDoctorModal() {
        var doctorModal = document.getElementById("doctorModal");
        doctorModal.style.display = "none";
    }

    $('.button-container').on('click', '#cancelBtn', function() {
        closeDoctorModal();
        document.getElementById('doctor_name').value = '';
        document.getElementById('doctor_specialty').value = '';
        document.getElementById('doctor_workplace').value = '';
        document.getElementById('doctor_diploma').value = '';
        document.getElementById('doctor_introduction').value = '';
    })

    $('.button-container').on('click', '#cancelEditBtn', function() {
        closeEditDoctorModal();
    })

    $('table').on('click', '#openDoctorEditModal', function() {
        var id = $(this).data('id');
        $('#doctor_id_edit').val(id);
        openEditModalDoctor(id);
    });

    function openEditModalDoctor(id) {
        var doctorEditModal = document.getElementById("doctorEditModal");
        doctorEditModal.style.display = "block";

        var closeEditDoctorBtn = document.querySelector('.doctorEditClose');
        closeEditDoctorBtn.addEventListener('click', function() {
            closeEditDoctorModal();
        });

        $.ajax({
            type: "GET",
            url: "http://localhost:8082/api/doctors/get/" + id,
            success: function(data) {
                $('#doctor_name_edit').val(data.username);
                $('#doctor_specialty_edit').val(data.specialty);
                $('#doctor_workplace_edit').val(data.workplace);
                $('#doctor_diploma_edit').val(data.diploma);
                $('#doctor_introduction_edit').val(data.introduction);
                $('#changeAvatar').val(data.avatar);
            },
            error: function(err) {
                console.log("Error fetching doctor data:", err);
                alert("Error fetching doctor data: " + err.responseText);
            }
        });
    }

    function closeEditDoctorModal() {
        var doctorEditModal = document.getElementById("doctorEditModal");
        doctorEditModal.style.display = "none";
    }

    $('table').on('click', '#delete', function() {
        var deleteDoctorID = $(this).closest('tr').find('.doctor-id').text();;
        console.log("Doctor ID:", deleteDoctorID);
        openConfirmDeleteModal(deleteDoctorID);
    });

    function openConfirmDeleteModal(deleteDoctorID) {
        var confirmDeleteModal = document.getElementById("confirmDeleteModal");
        confirmDeleteModal.style.display = "block";

        $('#confirmDelete').on('click', function() {
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8082/api/doctors/delete/" + deleteDoctorID,
                success: function(data) {
                    alert("Delete success");
                    assignDataToTable();
                    closeConfirmDeleteModal();
                },
                error: function(err) {
                    console.log(err);
                }
            });
        });

        $('#cancelDelete, .close').on('click', function() {
                closeConfirmDeleteModal();
        });
    }

    function closeConfirmDeleteModal() {
        var confirmDeleteModal = document.getElementById("confirmDeleteModal");
        confirmDeleteModal.style.display = "none";
    }

    $('#create_Doctor_Btn').on('click', function() {
        var name = $("#doctor_name").val();
        var specialty = $("#doctor_specialty").val();
        var workplace = $("#doctor_workplace").val();
        var diploma = $("#doctor_diploma").val();
        var introduction = $("#doctor_introduction").val();

        if (!name || !specialty || !diploma || !workplace || !introduction) {
            alert("All fields are required.");
            return;
        }

        var jsonVar = {
            username: name,
            specialty: specialty,
            diploma: diploma,
            workplace: workplace,
            introduction: introduction
        };

        $.ajax({
            type: "POST",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/create",
            success: function(data) {
                closeDoctorModal();
                assignDataToTable();
            },
            error: function(err) {
                console.log(err);
                alert("Error: " + err.responseText);
            }
        });
    });

    $('#okEditBtn').on('click', function() {
        var id = $('#doctor_id_edit').val();
        var name = $("#doctor_name_edit").val();
        var specialty = $("#doctor_specialty_edit").val();
        var workplace = $("#doctor_workplace_edit").val();
        var diploma = $("#doctor_diploma_edit").val();
        var introduction = $("#doctor_introduction_edit").val();

        var jsonVar = {
            username: name,
            specialty: specialty,
            diploma: diploma,
            workplace: workplace,
            introduction: introduction
        };

        $.ajax({
            type: "PUT",
            data: JSON.stringify(jsonVar),
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/put/" + id,
            success: function(data) {
                $("#doctor_name_edit").val("");
                $("#doctor_specialty_edit").val("");
                $("#doctor_workplace_edit").val("");
                $("#doctor_diploma_edit").val("");
                $("#doctor_introduction_edit").val("");
                closeEditDoctorModal();
                assignDataToTable();
            },
            error: function(err) {
                console.log(err);
                alert("Error: " + err.responseText);
            }
        });
    });

    function assignDataToTable() {
        $("tbody").empty();
        $.ajax({
            type: "GET",
            contentType: "application/json",
            url: "http://localhost:8082/api/doctors/get",
            success: function(data) {
                var doctor = JSON.parse(JSON.stringify(data));
                for (var i in doctor) {
                    $("tbody").append("<tr>" +
                        "<td>" + (parseInt(i) + 1) + "</td>" +
                        "<td class='doctor-id' style='display:none;'>" + doctor[i].id + "</td>" +
                        "<td>" + doctor[i].username + "</td>" +
                        "<td>" + doctor[i].specialty + "</td>" +
                        "<td>" + doctor[i].diploma + "</td>" +
                        "<td>" + doctor[i].workplace + "</td>" +
                        "<td>" + doctor[i].introduction + "</td>" +
                        "<td class='button-cell'><div class='table-button-container'><button class='btn btn-primary' data-id='"
                        + doctor[i].id + "' id='openDoctorEditModal'>Edit</button>" +
                        "<button class='btn btn-danger' data-id='"+ doctor[i].id +"' id='delete'>Delete</button></div></td>" +
                    "</tr>");
                }
            },
            error: function(data) {
                console.log(data);
            }
        });
    }
});
