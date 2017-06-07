movingApp.controller("bookedMoveController", ['$scope', '$http','$routeParams','$sessionStorage',   function($scope, $http,$routeParams,$sessionStorage) {
	
	$scope.getBookedMoves = function() {
		$http({
			method: 'GET',
			url: '/getBookedMoves',
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$scope.moves = response.data;
		});
	}
}]);