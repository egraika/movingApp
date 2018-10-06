movingApp.controller("bookMoveController", ['$scope', '$http','$sessionStorage','$uibModal','$window','$location','bsLoadingOverlayService','$route','$timeout','$rootScope','Session',   function($scope, $http, $sessionStorage,$uibModal, $window,$location,bsLoadingOverlayService,$route,$timeout,$rootScope,Session) {

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
		bsLoadingOverlayService.start();
        $http({
			method: 'POST',
			url: '/bookMove',
			data: $scope.moveData,
			params: {"userId": Session.id},
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			bsLoadingOverlayService.stop();
			$location.path('/confirmation');
			$route.reload();
			$timeout( function(){
				window.location.reload();
			}, 20000 );
		});
	}

}]);