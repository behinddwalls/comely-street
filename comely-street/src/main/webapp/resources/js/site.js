var siteJS = function() {

    var getCookie = function(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ')
                c = c.substring(1);
            if (c.indexOf(name) == 0)
                return c.substring(name.length, c.length);
        }
        return "";
    }

    function capitalize(s) {
        return s[0].toUpperCase() + s.substr(1);
    }

    return {
        getCookie : getCookie,
        capitalize : capitalize
    }

}();

function getInternalServiceItemHtmlEntity(servicecategories, serviceItemLength) {
    var serviceOptionHtml = "";
    $.each(servicecategories, function(index, item) {
        serviceOptionHtml = serviceOptionHtml + '<option value="' + item.id + '">' + item.serviceCategoryName + '</option>'
    });
    console.log(serviceOptionHtml);
    var index = serviceItemLength;

    return '<div class="service-item">' + '<div class="form-group col-sm-3">' + '<label>Name </label>' + '<input name="serviceItemDetails[' + index
            + '].name" class="form-control" type="text" value="">' + '</div>' + '<div class="form-group col-sm-2">' + '<label>Category </label>'
            + '<select name="serviceItemDetails[' + index + '].serviceCategory.id" class="form-control">' + serviceOptionHtml + '</select>'
            + '</div>' + '<div class="form-group col-sm-2">' + '<label>Price</label>' + '<input name="serviceItemDetails[' + index
            + '].price" class="form-control" type="text" value="99">' + '</div>' + '<div class="form-group col-sm-1">' + '<label>Currency</label>'
            + '<select name="serviceItemDetails[' + index + '].currency" class="form-control">' + '<option value=""></option>'
            + '<option value="INR" selected="selected">INR</option>' + '</select>' + '</div>' + '<div class="form-group col-sm-1">'
            + '<label>Time</label>' + '<input name="serviceItemDetails[' + index + '].time" class="form-control" type="text" value="20">' + '</div>'
            + '<div class="form-group col-sm-2">' + '<label>Time Unit</label>' + '<select name="serviceItemDetails[' + index
            + '].timeUnit" class="form-control">' + '<option value="">' + '</option>'
            + '<option value="MINUTES" selected="selected">Minutes</option>' + '</select>' + '</div>' + '<div class="form-group col-sm-1">'
            + '<label>Remove</label>' + '<button class="btn btn-warning form-control col-sm-6 remove-service-item" type="button">'
            + '<i class="fa fa-minus fa-2x"></i>' + '</button>' + '</div>' + '</div>';

}
