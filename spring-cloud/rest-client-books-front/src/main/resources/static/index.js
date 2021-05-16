angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8192/rest';

    $scope.fillTable = function (pageIndex = 1) {
        $http({
            url:contextPath + '/api/v1/books',
            method: 'GET',
            params: {
                title: $scope.filter ? $scope.filter.title: null,
                p: pageIndex
            }
        })
            .then(function (response) {
                $scope.ProductsPage = response.data;
                $scope.PaginationArray = $scope.generatePagesIndexes(1, $scope.ProductsPage.totalPages)
            });
    };

     $scope.generatePagesIndexes = function (startPage, endPage){
         let arr = [];
         for(let i = startPage; i<endPage + 1; i++){
             arr.push(i);
         }
         return arr;
      }


    $scope.submitCreateNewBook = function () {
        $http.post(contextPath + '/api/v1/books', $scope.newBook)
            .then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.deleteProductById = function (id) {
        $http({
            url: contextPath + '/api/v1/books',
            method: 'DELETE',
            params: {
            id:id
            }
        }).then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});