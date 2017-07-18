const CART_SERVLET_URL = "cart";

var Cart = {

    getTotalCost: function () {
        $.ajax({
            type: "GET",
            url: CART_SERVLET_URL,
            success: function (data) {
                $(".simpleCart_total").text("$" + data['total'].toFixed(2));
                $(".total1").text("$" + data['total'].toFixed(2));
                $("#total__price").text("$" + data['total'].toFixed(2));
            },
            dataType: "json"
        });
    },

    getQuantity: function (vendorCode) {
        $.ajax({
            type: "GET",
            url: CART_SERVLET_URL,
            data: { code: vendorCode },
            success: function (data) {
                $("#item_qty").text("Quantity : " + data['quantity']);
            },
            dataType: "json"
        });
    },

    addToCart: function (vendorCode) {
        $.ajax({
            type: "POST",
            url: CART_SERVLET_URL,
            data: { code: vendorCode },
            success: function () {
                Cart.getTotalCost();
            },
            error: function () {
                alert("(ajax error) add to cart");
            }
        });
    },

    increaseAmount: function (vendorCode) {
        $.ajax({
            type: "PUT",
            url: "cart?code=" + vendorCode + "&value=1",
            success: function () {
                Cart.getTotalCost();
                Cart.getQuantity(vendorCode);
            },
            error: function () {
                alert("(ajax error) increase amount");
            }
        });
    },

    reduceAmount: function (vendorCode) {
        $.ajax({
            type: "PUT",
            url: "cart?code=" + vendorCode + "&value=-1",
            success: function () {
                Cart.getTotalCost();
                Cart.getQuantity(vendorCode);
            },
            error: function () {
                alert("(ajax error) reduce amount");
            }
        });
    },

    removeItem: function (vendorCode) {
        $.ajax({
            type: "DELETE",
            url: "cart?code=" + vendorCode,
            success: function () {
                Cart.getTotalCost();
            },
            error: function () {
                alert("(ajax error) remove item");
            }
        });
    },

    clearCart: function () {
        $.ajax({
            type: "DELETE",
            url: CART_SERVLET_URL,
            success: function () {
                Cart.getTotalCost();
            }
        });
    }
}

$(function () {
    Cart.getTotalCost();
    console.log("document is loaded");
});