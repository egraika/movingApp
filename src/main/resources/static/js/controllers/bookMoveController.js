movingApp.controller("bookMoveController", ['$scope', '$http','$sessionStorage','$uibModal','$window','$location','bsLoadingOverlayService','$route','$timeout','$rootScope','Session',   function($scope, $http, $sessionStorage,$uibModal, $window,$location,bsLoadingOverlayService,$route,$timeout,$rootScope,Session) {

    $scope.userExists = false;
	$scope.isBooking = false;
    $scope.init = function() {
        if($rootScope.authenticated) {
            $('#firstName').removeAttr('required');
            $('#lastName').removeAttr('required');
            $('#email').removeAttr('required');
            $('#phone').removeAttr('required');
        }
    }
	$scope.bookMove = function() {
		$scope.moveData.notes = [];
		var jsonString = JSON.stringify($scope.moveData);
		$scope.moveData.startsAt = new Date($scope.moveData.moveStart);
		$scope.isBooking = true;
		bsLoadingOverlayService.start();
        $http({
			method: 'POST',
			url: '/bookMove',
			data: $scope.moveData,
			params: {"userId": Session.id},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.isBooking = false;
            bsLoadingOverlayService.stop();
			$location.path('/confirmation').replace();
        }, function(response) {
            $scope.isBooking = false;
            bsLoadingOverlayService.stop();
            $scope.userExists = true;
        });
    }
}]);