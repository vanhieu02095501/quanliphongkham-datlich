export const App = (function () {
    const module = {
        errorToastSelector: $('#error-toast'),
        successToastSelector: $('#success-toast'),
    };

    module.handleResponseMessageByStatusCode = (jqXHR) => {
        let message = jqXHR.responseJSON.message;
        switch (jqXHR.status) {
            case 401:
            case 403:
                /* Do not use break to modify message in case 401 and 403 that can be null in response */
                message = message ?? ERROR_403;
            case 500:
                module.errorToastSelector.find('.toast-body').text('').text(message);
                module.errorToastSelector.toast('show');
                break;
            case 200:
                module.successToastSelector.find('.toast-body').text('').text(message);
                module.successToastSelector.toast('show');
                break;

        }
    }

    module.showSuccessMessage = (message) => {
        module.successToastSelector.find('.toast-body').text('').text(message);
        module.successToastSelector.toast('show');
    }

    module.showErrorMessage = (message) => {
        module.errorToastSelector.find('.toast-body').text('').text(message);
        module.errorToastSelector.toast('show');
    }

    return module;
}());

$(function () {
    $('.modal').on(' hidden.bs.modal', function () {
        $(this).find('.error-text').remove();
    });
});