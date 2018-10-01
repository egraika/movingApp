movingApp.controller("passwordReset", ['$scope', '$http','$window','$sessionStorage','$rootScope','bsLoadingOverlayService','$routeParams','$location','$uibModal','$timeout',  function($scope, $http, $window,$sessionStorage,$rootScope,bsLoadingOverlayService,$routeParams,$location,$uibModal,$timeout) {

    $scope.isReset = false;

    $scope.reset = function() {
        bsLoadingOverlayService.start();
		$http({
          	method: 'POST',
          	url: '/sendResetToken',
          	data: $scope.email,
          	headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.isReset = true;
            bsLoadingOverlayService.stop();
        }, function(response) {
            $scope.isReset = true;
          	bsLoadingOverlayService.stop();
        });
	}

}]);