movingApp.controller("registrationController", ['$scope', '$http','$window','$sessionStorage','$rootScope','bsLoadingOverlayService',  function($scope, $http, $window,$sessionStorage,$rootScope,bsLoadingOverlayService) {

    $scope.registered = false;
    $scope.error = false;

		  $scope.register = function() {
		        bsLoadingOverlayService.start();
		        $http({
          			method: 'POST',
          			url: '/register',
          			data: $scope.user,
          			headers:{'Content-Type': 'application/json'}
          		}).then(function(response) {
                    $scope.responseEntity = response.data;
          			$scope.registered = true;
          			$scope.error = false;
          			bsLoadingOverlayService.stop();
                }, function(response) {
                    $scope.responseEntity = response.data;
          			$scope.error = true;
          			$scope.registered = false;
          			bsLoadingOverlayService.stop();
                });
		  }
}]);