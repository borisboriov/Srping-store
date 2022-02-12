angular.module('market-front').controller('ordersController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';


    $scope.loadOrders = function () {
        $http.get(contextPath + 'api/v1/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.checkOut = function () {
        $http({
            url: contextPath + 'api/v1/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response) {
            $scope.orderDetails = null
        });
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }


    $scope.loadOrders();
});