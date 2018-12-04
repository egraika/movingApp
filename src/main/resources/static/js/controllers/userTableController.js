movingApp
    .controller("userTableController", ['$scope', '$http','$routeParams','$sessionStorage','Session','$modal', 'moment','calendarConfig','$location','bsLoadingOverlayService', function($scope, $http,$routeParams,$sessionStorage,Session,$modal, moment,calendarConfig,$location,bsLoadingOverlayService) {

	$scope.itemsByPage = 25;
	$scope.isLoading = false;
	$scope.isError = false;
	$scope.noResultsFound = false;

	$scope.selectItem = function(value) {
		$scope.itemsByPage = value;
	}

	$scope.callServer = function getData(tableState, tableController) {
		$scope.isLoading = true;
		var userid = Session.id;
		$http({
			method: 'GET',
			url: '/getUsers',
			params: {tableState : tableState, userid : userid},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.users = response.data.content;
			$scope.isLoading = false;
			$scope.noResultsFound = false;
			$scope.isError = false;
			tableState.pagination.numberOfPages = response.data.totalPages;
			if($scope.users.length == 0) {
				$scope.noResultsFound = true;
			}
        }, function(response) {
        });
	}
}]);