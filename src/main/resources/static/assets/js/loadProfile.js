$(document).ready(function () {
    $(loadProfileData());

    $('#edit').click(function () {
        $("#profile-information :input").attr("disabled", false);
        $("#edit").attr("hidden", true);
        $("#save").attr("hidden", false);
        $("#cancel").attr("hidden", false);
    });

    $('#cancel').click(function (event) {
        window.location.replace("/myProfile");
    });

    $('#save').click(function (event) {
        let object = new Object();
        object.firstname = $('input[id=firstname]').val();
        object.lastname = $('input[id=lastname]').val();
        object.email = $('input[id=email]').val();

        updateUser(JSON.stringify(object), function (result) {
            window.location.replace("/myProfile");
            alert("You successfully updated your user profile")
        }, function (error) {
            alert("there was an error: " + error);
        });
        event.preventDefault();
    })

    function loadProfileData() {
        $("#profile-information").empty();
        getProfileInfo(function (result) {
            $("#profile-information").append(
                $('<form method="post">').append(
                    $('<div class="mb-3">').append(
                        $('<label for="firstname">').text("Firstname"),
                        $('<input class="form-control" id="firstname" type="text" value="' + result.firstname + '" disabled required/>')
                    ),
                    $('<div class="mb-3">').append(
                        $('<label for="lastname">').text("Lastname"),
                        $('<input class="form-control" id="lastname" type="text" value="' + result.lastname + '" disabled required/>')
                    ),
                    $('<div class="mb-3">').append(
                        $('<label for="email">').text("Email"),
                        $('<input class="form-control" id="email" type="email" value="' + result.email + '" disabled required/>')
                    )
                )
            )
        });
    }
});