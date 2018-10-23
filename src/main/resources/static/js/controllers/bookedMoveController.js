movingApp
    .controller("bookedMoveController", ['$scope', '$http','$routeParams','$sessionStorage','Session','$modal', 'moment','calendarConfig','$location','bsLoadingOverlayService',   function($scope, $http,$routeParams,$sessionStorage,Session,$modal, moment,calendarConfig,$location,bsLoadingOverlayService) {

//	$scope.getBookedMoves = function() {
//		$http({
//			method: 'GET',
//			url: '/getBookedMoves',
//			headers:{'Content-Type': 'application/json'}
//		}).then(function successCallBack(response) {
//			$scope.moves = response.data;
//		});
//	}
	$scope.year = (new Date()).getFullYear();
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
        }).then(function(response) {
            $scope.moves = response.data.content;
			$scope.isLoading = false;
			$scope.noResultsFound = false;
			$scope.isError = false;
			tableState.pagination.numberOfPages = response.data.totalPages;
			if($scope.moves.length == 0) {
				$scope.noResultsFound = true;
			}
        }, function(response) {
        });
	}

	$scope.init = function() {
	    var userid = Session.id;
		bsLoadingOverlayService.start();
        $http({
			method: 'GET',
			url: '/getCalenderMoves',
            params: {userid : userid, year: new Date().getFullYear()},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
			$scope.moves = response.data;
        	for(var i = 0; i < $scope.moves.length; i++) {
        		$scope.moves[i].startsAt = new Date($scope.moves[i].startsAt);
        		$scope.moves[i].endsAt = new Date($scope.moves[i].endsAt);
        		$scope.moves[i].color = calendarConfig.colorTypes.info;
        	}
        	$scope.events = $scope.moves;
            bsLoadingOverlayService.stop();
        }, function(response) {
            bsLoadingOverlayService.stop();
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
    $scope.events = [];
    vm.isCellOpen = true;
    vm.eventClicked = function(event) {
        var path = "/editMove/" + event.id;
        $location.path(path);
        //$route.reload();
    };

    vm.eventEdited = function(event) {
      alert.show('Edited', event);
    };

    vm.eventDeleted = function(event) {
      alert.show('Deleted', event);
    };

    vm.eventTimesChanged = function(event) {
      alert.show('Dropped or resized', event);
    };

    vm.toggle = function($event, field, event) {
      $event.preventDefault();
      $event.stopPropagation();
      event[field] = !event[field];
    };

    vm.timespanClicked = function(date, cell) {

      if (vm.calendarView === 'month') {
        if ((vm.cellIsOpen && moment(date).startOf('day').isSame(moment(vm.viewDate).startOf('day'))) || cell.events.length === 0 || !cell.inMonth) {
          vm.cellIsOpen = false;
        } else {
          vm.cellIsOpen = true;
          vm.viewDate = date;
        }
      } else if (vm.calendarView === 'year') {
        if ((vm.cellIsOpen && moment(date).startOf('month').isSame(moment(vm.viewDate).startOf('month'))) || cell.events.length === 0) {
          vm.cellIsOpen = false;
        } else {
          vm.cellIsOpen = true;
          vm.viewDate = date;
        }
      }

    };

    $scope.calenderViewChange = function() {
        var userid = Session.id;
        if($scope.year != vm.viewDate.getFullYear()) {
            bsLoadingOverlayService.start();
            $http({
                method: 'GET',
                url: '/getCalenderMoves',
                params: {userid : userid, year: vm.viewDate.getFullYear()},
                headers:{'Content-Type': 'application/json'}
            }).then(function(response) {
                $scope.moves = response.data;
                for(var i = 0; i < $scope.moves.length; i++) {
                    $scope.moves[i].startsAt = new Date($scope.moves[i].startsAt);
                    $scope.moves[i].endsAt = new Date($scope.moves[i].endsAt);
                    $scope.moves[i].color = calendarConfig.colorTypes.info;
                }
                $scope.events = $scope.moves;
                $scope.year = vm.viewDate.getFullYear();
                bsLoadingOverlayService.stop();
            }, function(response) {
                bsLoadingOverlayService.stop();
            });
        }
    }
}]);