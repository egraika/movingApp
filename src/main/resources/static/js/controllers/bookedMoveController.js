movingApp.controller("bookedMoveController", ['$scope', '$http','$routeParams','$sessionStorage',   function($scope, $http,$routeParams,$sessionStorage) {
	
//	$scope.getBookedMoves = function() {
//		$http({
//			method: 'GET',
//			url: '/getBookedMoves',
//			headers:{'Content-Type': 'application/json'}
//		}).then(function successCallBack(response) {
//			$scope.moves = response.data;
//		});
//	}
	
	$scope.itemsByPage = 10;
	$scope.isLoading = false;
	$scope.isError = false;
	$scope.noResultsFound = false;
	
	$scope.selectItem = function(value) {
		$scope.itemsByPage = value;
	}
	
	$scope.callServer = function getData(tableState, tableController) {
		$scope.isLoading = true;
		
		$http({
			method: 'GET',
			url: '/getBookedMoves',
			params: {tableState : tableState},
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$scope.isLoading = false;
			$scope.noResultsFound = false;
			$scope.isError = false;
			$scope.moves = response.data.content;
			tableState.pagination.numberOfPages = response.data.totalPages;
			if($scope.moves.length == 0) {
				$scope.noResultsFound = true;
			}
		});
	}
	
	$scope.getContacts = function() {

		$http({
			method: 'GET',
			url: '/getContact',
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$scope.contacts = response.data;
			
		});
	}
}]);