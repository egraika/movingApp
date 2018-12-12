movingApp
    .controller("userTableController", ['$scope', '$http','$routeParams','$sessionStorage','Session','$modal', 'moment','calendarConfig','$location','bsLoadingOverlayService', function($scope, $http,$routeParams,$sessionStorage,Session,$modal, moment,calendarConfig,$location,bsLoadingOverlayService) {

	$scope.itemsByPage = 25;
	$scope.isLoading = false;
	$scope.isError = false;
	$scope.noResultsFound = false;

    $scope.init = function() {
        getLocations();
    }

    $scope.enabledOptions = [
        {value: false, label: 'False'},
        {value: true, label: 'True'},
    ];

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

     function getLocations() {
        var userid = Session.id;
		$http({
			method: 'GET',
			url: '/getLocations',
			params: {'userid' : userid},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.locations = response.data;
            $scope.locationOptions = {
                title: 'Assign Locations',
                filterPlaceHolder: 'Start typing to filter the locations below.',
                labelAll: 'Unselected Locations',
                labelSelected: 'Selected Locations',
                /* angular will use this to filter your lists */
                orderProperty: 'location',
                /* this contains the initial list of all items (i.e. the left side) */
                items: response.data,
                selectedItems: []
            };
        }, function(response) {
        });
     }
}]);