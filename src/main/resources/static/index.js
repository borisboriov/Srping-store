angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8090/app/api/v1';

    if ($localStorage.springWebUser) {
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springWebUser.token;
    }

    //Auth methods --------------------------------------------

    $scope.createNewUser = function () {
        console.log($scope.newUser);
        $http.post(contextPath + '/users', $scope.newUser)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.tryToAuth = function () {
        $http.post('http://localhost:8090/app/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springWebUser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        if ($scope.user.username) {
            $scope.user.username = null;
        }
        if ($scope.user.password) {
            $scope.user.password = null;
        }
    };

    $scope.clearUser = function () {
        delete $localStorage.springWebUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.isUserLoggedIn = function () {
        if ($localStorage.springWebUser) {
            return true;
        } else {
            return false;
        }
    };

    $scope.showCurrentUserInfo = function () {
        $http.get('http://localhost:8090/app/api/v1/profile')
            .then(function successCallback(response) {
                alert('MY NAME IS: ' + response.data.username);
            }, function errorCallback(response) {
                alert('UNAUTHORIZED');
            });
    }

    //Product methods --------------------------------------------

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_rate: $scope.filter ? $scope.filter.min_rate : null,
                max_rate: $scope.filter ? $scope.filter.max_rate : null
            }
        }).then(function (response) {
            $scope.ProductsList = response.data.content;
        });
    };

    $scope.deleteProduct = function (productId) {
        $http.delete(contextPath + '/products/' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.createProductJson = function () {
        console.log($scope.newProductJson);
        $http.post(contextPath + '/products', $scope.newProductJson)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    $scope.changeRate = function (productId, delta) {
        $http({
            url: contextPath + '/products',
            method: 'PUT',
            params: {
                productId: productId,
                delta: delta
            }
        }).then(function (response) {
            $scope.loadProducts();
        });
    }


//Cart methods --------------------------------------------
    $scope.addToCart = function (product) {
        $http({
            url: contextPath + '/carts/add/',
            method: 'POST',
            data: product
        })
            .then(function (response) {
                $scope.CartList = response.data;
            })
    }

    $scope.deleteProductFromCart = function (productId) {
        $http.delete(contextPath + '/carts/' + productId)
            .then(function (response) {
                $scope.CartList = response.data;
            });
    }

    $scope.clearCart = function () {
        $http.delete(contextPath + '/carts')
            .then(function (response) {
                $scope.CartList = response.data;
            });
    }
//Cart methods --------------------------------------------


    $scope.loadProducts();

});