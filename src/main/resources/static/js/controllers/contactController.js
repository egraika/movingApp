movingApp.controller("contactController", ['$scope', '$http','$window','$sessionStorage','$rootScope','$location',  function($scope, $http, $window,$sessionStorage,$rootScope,$location) {
		  
	$scope.init = function() {
		$scope.getContacts();
	}
	
	$scope.newContact = function() {
		$http({
			method: 'POST',
			url: '/newContact',
			data: $scope.contact,
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$location.path("/contactConfirmation");
		});
	}
	
	$scope.getContacts = function() {
		$http({
			method: 'GET',
			url: '/getContacts',
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$scope.contacts = response.data;
			$scope.isLoading = false;
			$scope.isError = false;
			if($scope.contacts.length == 0) {
			    $scope.noResultsFound = true;
			}
		});
	}
	
}]);