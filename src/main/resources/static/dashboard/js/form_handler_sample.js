import $ from "jquery";

/**
 * Convert form serialized data into JSON stringify
 *
 * @param data
 * @returns {string}
 */
function serializedArray2StringifyJson(data = []) {
    const result = {};

    data.forEach((item) => {
        result[item.name] = item.value;
    });

    return JSON.stringify(result);
}

/**
 * Process form's input to ajax data
 *
 * @param el
 * @returns {{data: (*|jQuery)}|{data: (*|jQuery), contentType: string}|{data: FormData, contentType: string}}
 */
export function makeAjaxData(el) {
    const form = $(el);
    const enctype = form.attr("enctype");

    switch (enctype) {
        case "application/json":
            return {
                data: serializedArray2StringifyJson(form.serializeArray()),
                contentType: "application/json",
            };
        case "multipart/form-data":
            return {
                data: new FormData(el),
                enctype: "multipart/form-data",
                processData: false,
                contentType: false,
            };
        default:
            return {
                data: form.serialize(),
                contentType: "application/x-www-form-urlencoded",
            };
    }
}

/**
 * Add listeners for making form calls with XHR requests
 * @note To make input show validation errors, it must be wrapped within a "with-validation" class
 * @events form.submit.begin, form.submit.success, form.submit.fail, form.submit.end
 *
 * @param el
 * @param options
 */
export function makeFormAjax(el, options = {}) {
    const { beforeSend } = options;

    $(el).on("submit", function (e) {
        e.preventDefault();
        e.stopPropagation();

        const form = $(this);
        const btn = form.find('input[type="submit"]');
        const action = form.attr("action");
        const method = form.attr("method");
        const data = makeAjaxData(this);

        $.ajax({
            url: action,
            method: method,
            ...data,
            dataType: "json",
            beforeSend: function () {
                if (beforeSend && !beforeSend(e)) {
                    return false;
                }

                form.trigger("form.submit.begin");

                btn.attr("disabled", true);
                form.addClass("loading");
                form.find(".form-error").remove();
            },
        })
            .done((response) => {
                form.trigger("form.submit.success", response);

                const { message, redirectTo } = response;

                alert(message || "Success");

                if (redirectTo) {
                    window.location.href = redirectTo;
                }
            })
            .fail((jqXHR) => {
                form.trigger("form.submit.fail", jqXHR.responseJSON);

                const { errors, message, redirect } = jqXHR.responseJSON;

                if (errors) {
                    for (const key in errors) {
                        const input = form.find('[name="' + makeInputName(key) + '"]').first();
                        const error = makeErrorMessage(errors[key]);
                        const group = input.closest(".with-validation");

                        if (group.length) {
                            group.append(error);
                        }
                    }
                    scrollToFirstError();
                }

                if (redirect) {
                    window.location.href = redirect;
                }
            })
            .always(() => {
                form.trigger("form.submit.end");

                btn.attr("disabled", false);
                form.removeClass("loading");
            });
    });
}

// TODO Cannot scroll to categories selection error
function scrollToFirstError() {
    let firstErrorElement = $(".form-error").first();
    let firstErrorInput = firstErrorElement.parents("div.with-validation").find("input");

    firstErrorInput.trigger("focus");

    $([document.documentElement, document.body]).animate(
        {
            scrollTop: firstErrorInput.offset().top,
        },
        100
    );
}

/**
 * Make div element for showing error message
 *
 * @param error
 * @returns {HTMLDivElement}
 */
export function makeErrorMessage(error) {
    const el = document.createElement("div");
    const icon = document.createElement("i");
    const text = document.createElement("span");
    const message = Array.isArray(error) ? error.join("\n") : error;

    el.classList.add("form-error", "text-danger", "mt-1");
    icon.classList.add("fa-solid", "fa-circle-exclamation");
    text.classList.add("ms-1");
    text.innerText = message;
    el.appendChild(icon);
    el.appendChild(text);

    return el;
}

/**
 * Convert validation attribute name to HTML input name
 *
 * @param validationName string, eg: 'supplier.0.supplier_id'
 * @returns {*}
 */
function makeInputName(validationName) {
    const attributes = validationName.split(".");
    return attributes.map((el, i) => (i ? "[" + el + "]" : el)).join("");
}
