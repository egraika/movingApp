movingApp.controller("bookMoveController", ['$scope', '$http','$sessionStorage','$uibModal','$window','$location','bsLoadingOverlayService','$route','$timeout','$rootScope','Session',   function($scope, $http, $sessionStorage,$uibModal, $window,$location,bsLoadingOverlayService,$route,$timeout,$rootScope,Session) {

    $scope.userExists = false;
	$scope.isBooking = false;
	$scope.step = "type";

    $scope.init = function() {
        if($rootScope.authenticated) {
            $('#firstName').removeAttr('required');
            $('#lastName').removeAttr('required');
            $('#email').removeAttr('required');
            $('#phone').removeAttr('required');
        }
        $scope.moveData = {};
        $scope.moveData.type = 'withTruck';
        document.body.scrollTop = document.documentElement.scrollTop = 0;
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

    $scope.afterType = function() {
        if($rootScope.authenticated) {
            $scope.step = "move";
            document.body.scrollTop = document.documentElement.scrollTop = 0;
        } else {
            $scope.step = "personal";
            document.body.scrollTop = document.documentElement.scrollTop = 0;
        }
    }

    $scope.afterPersonal = function() {
        $scope.userExists = false;
        bsLoadingOverlayService.start();
        $http({
			method: 'GET',
			url: '/checkUser',
			params: {"email": $scope.moveData.user.email},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            bsLoadingOverlayService.stop();
            $scope.step = "move";
        }, function(response) {
            bsLoadingOverlayService.stop();
            $scope.userExists = true;
        });
    }

    $scope.beforeMove = function() {
        if($rootScope.authenticated) {
            $scope.step = "type";
            document.body.scrollTop = document.documentElement.scrollTop = 0;
        } else {
            $scope.step = "personal";
            document.body.scrollTop = document.documentElement.scrollTop = 0;
        }
    }

    $scope.beforePersonal = function() {
        $scope.step = "type";
        document.body.scrollTop = document.documentElement.scrollTop = 0;
    }
}]);