angular.module('market-front').controller('cartController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';

    $scope.loadCart = function () {
        $http({
            url: contextPath + 'api/v1/cart',
            method: 'GET'
        }).then(function (response) {
            $scope.cart = response.data;
        });
    };


    $scope.clearCart = function () {
        $http.get(contextPath + 'api/v1/cart/clear')
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.loadCart();
});