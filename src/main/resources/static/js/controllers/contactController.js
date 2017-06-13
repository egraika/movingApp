movingApp.controller("contactController", ['$scope', '$http','$window','$sessionStorage','$rootScope','$location',  function($scope, $http, $window,$sessionStorage,$rootScope,$location) {
		  
	$scope.init = function() {
		$scope.getContacts();
	}
	
	$scope.newContact = function() {
		var s = 0;
		$http({
			method: 'POST',
			url: '/newContact',
			data: $scope.contact,
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$location.path('/contactConfirmation').replace();
			
		});
	}
	
	$scope.getContacts = function() {
		$http({
			method: 'GET',
			url: '/getContacts',
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$scope.contacts = response.data;
			
		});
	}
	
}]);