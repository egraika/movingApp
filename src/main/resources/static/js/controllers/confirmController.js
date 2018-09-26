movingApp.controller("confirmController", ['$scope', '$http','$window','$sessionStorage','$rootScope','bsLoadingOverlayService','$routeParams','$location','$uibModal','$timeout',  function($scope, $http, $window,$sessionStorage,$rootScope,bsLoadingOverlayService,$routeParams,$location,$uibModal,$timeout) {

    $scope.confirmed = false;
    $scope.passwordNotStrongEnough = false;
    $scope.alertData = {boldTextTitle: "", textAlert: "", mode: ''};

    $scope.user = {
            password : "",
            confirmPassword : "",
            confirmationToken : ""
        };

    $scope.confirm = function() {
        bsLoadingOverlayService.start();
		$http({
          	method: 'GET',
          	url: '/confirmToken',
          	params: {'token' : $routeParams.token},
          	headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.user.confirmationToken = $routeParams.token;
          	$scope.confirmed = true;
          	bsLoadingOverlayService.stop();
        }, function(response) {
            $scope.confirmed = false;
          	bsLoadingOverlayService.stop();
        });
	}

    $scope.savePassword = function() {
        bsLoadingOverlayService.start();
		$http({
          	method: 'POST',
          	url: '/confirm',
          	data: $scope.user,
          	headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.responseEntity = response.data;
          	$scope.passwordNotStrongEnough = false;
          	bsLoadingOverlayService.stop();
          	openAlert();
        }, function(response) {
            $scope.responseEntity = response.data;
            $scope.passwordNotStrongEnough = true;
          	bsLoadingOverlayService.stop();
            $timeout(function() {
                $scope.passwordNotStrongEnough = false;
		    }, 3000);
        });
	}

    function openAlert(){
		 $scope.alertData.mode = "success";
		 $scope.alertData.boldTextTitle = "Password Successfully Set!";
		 var modalInstance = $uibModal.open({
		     templateUrl: 'saveAlert',
		     backdrop: true,
		     keyboard: true,
		     backdropClick: true,
		     size: 'sm',
		     controller: function($scope, data){
		    	 $scope.alertData = data;
		    	 $scope.close = function(){
		    		 modalInstance.close();
				 };

				 $timeout(function() {
					 modalInstance.close();
					 $location.path("/login");
				 }, 2000);
		     },
		     resolve: {
		    	 data: function () {
		    		 return $scope.alertData;
				 }
		     }
		 });
	 }

}]);