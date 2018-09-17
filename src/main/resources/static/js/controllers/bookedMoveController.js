movingApp
    .controller("bookedMoveController", ['$scope', '$http','$routeParams','$sessionStorage','Session','$modal', 'moment','calendarConfig',   function($scope, $http,$routeParams,$sessionStorage,Session,$modal, moment,calendarConfig) {

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
		var userid = Session.id;
		$http({
			method: 'GET',
			url: '/getBookedMoves',
			params: {tableState : tableState, userid : userid},
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

	$scope.isCalendar = true;
	$scope.bookedMovesView = "Switch to Table";
	$scope.changeView = function() {
	    if($scope.isCalendar) {
	        $scope.isCalendar = false;
	        $scope.bookedMovesView = "Switch to Calendar";
	    } else {
	        $scope.isCalendar = true;
	        $scope.bookedMovesView = "Switch to Table";
	    }
	}

	 var vm = this;
    vm.calendarView = 'month';
    vm.viewDate = new Date();
    vm.events = [
        {
            title: 'An event',
            type: 'warning',
            startsAt: moment().startOf('week').subtract(2, 'days').add(8, 'hours').toDate(),
            endsAt: moment().startOf('week').add(1, 'week').add(9, 'hours').toDate(),
            draggable: true,
            resizable: true
        }, {
            title: '<i class="glyphicon glyphicon-asterisk"></i> <span class="text-primary">Another event</span>, with a <i>html</i> title',
            type: 'info',
            startsAt: moment().subtract(1, 'day').toDate(),
            endsAt: moment().add(5, 'days').toDate(),
            draggable: true,
            resizable: true
        }, {
            title: 'This is a really long event title that occurs on every year',
            type: 'important',
            startsAt: moment().startOf('day').add(7, 'hours').toDate(),
            endsAt: moment().startOf('day').add(19, 'hours').toDate(),
            recursOn: 'year',
            draggable: true,
            resizable: true
        }
    ];
    vm.isCellOpen = true;
    vm.eventClicked = function (event) {
        alert.show('Clicked', event);
    };

    vm.toggle = function ($event, field, event) {
        $event.preventDefault();
        $event.stopPropagation();
        event[field] = !event[field];
    };
}]);