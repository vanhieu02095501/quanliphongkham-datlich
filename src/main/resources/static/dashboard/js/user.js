import {App} from "./app.js";

const UserCreation = (function () {
    const module = {
        getAllUserRoleUrl: '/api/users/getAllRoles',
        usernameSelector: $('#username'),
        passwordSelector: $('#password'),
        fullNameSelector: $('#full-name'),
        roleCodeSelector: $('#role-code'),
    };

    module.init = () => {
        getAllUserRoles();
    }

    const getAllUserRoles = () => {
        $.ajax({
            url: module.getAllUserRoleUrl
        })
            .done((response) => {
                for (const role of response.data) {
                    module.roleCodeSelector.append(new Option(role.name, role.code))
                }
            })
            .fail((jqXHR) => {
                App.handleResponseMessageByStatusCode(jqXHR);
            })
    }

    return module;
}());

(function () {
    UserCreation.init();
}());
