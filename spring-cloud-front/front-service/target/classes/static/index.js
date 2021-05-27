(function () {
    'use strict';

    angular
        .module('app', [])
        .config(config)
        .run(run);

    function config($httpProvider) {
    }

    function run() {}
})();

angular.module('app', []).controller('indexController', function ($scope, $http) {
    console.log(1);
    const contextPath = 'http://localhost:5555/cloud';

     $scope.fillTable = function (pageIndex = 1) {
         $http({
             url:contextPath + '/api/v1/books',
             method: 'GET',
             headers: {
                 'Content-Type': 'application/json',
             },
             params: {
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

    $scope.deleteProductById = function(id) {
        $http({
            url: contextPath + '/api/v1/books/' + id,
            params: id,
            method: 'DELETE',
        }).then(function (response) {
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});