movingApp
    .controller("myMovesController", ['$scope', '$http','$routeParams','$sessionStorage','Session','$modal','$location',   function($scope, $http,$routeParams,$sessionStorage,Session,$modal,$location) {

	var userid = Session.id;
    $scope.creditCardSet = true;
    $scope.firstMoveNeedsInfo = false;

    $scope.init = function() {
        $http({
			method: 'POST',
			url: '/getMyProfile',
			params: {userid : userid},
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
		    if(response.data.ccLastFour == null) {
			    $scope.creditCardSet = false;
		    }
		});
    }

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
			url: '/getMyMoves',
			params: {tableState : tableState, userid : userid},
			headers:{'Content-Type': 'application/json'},
		}).then(function successCallBack(response) {
			$scope.moves = response.data.content;
			$scope.isLoading = false;
			$scope.noResultsFound = false;
			$scope.isError = false;
			tableState.pagination.numberOfPages = response.data.totalPages;
			if($scope.moves.length == 0) {
				$scope.noResultsFound = true;
			} else if($scope.moves[0].numberOfBoxes == 0) {
                $scope.firstMoveNeedsInfo = true;
			}
		});
	}
}]);