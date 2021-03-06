movingApp
    .controller("bookedMoveController", ['$scope', '$http','$routeParams','$sessionStorage','Session','$modal', 'moment','calendarConfig','$location','bsLoadingOverlayService',   function($scope, $http,$routeParams,$sessionStorage,Session,$modal, moment,calendarConfig,$location,bsLoadingOverlayService) {

	$scope.year = (new Date()).getFullYear();
	$scope.itemsByPage = 10;
	$scope.isLoading = false;
	$scope.isError = false;
	$scope.noResultsFound = false;
	$scope.userSearch = [];

	$scope.selectItem = function(value) {
		$scope.itemsByPage = value;
	}
	
	$scope.callServer = function getData(tableState, tableController) {
		$scope.isLoading = true;
		var userid = Session.id;
		if(tableState.search.predicateObject != undefined) {
		    tableState.search.predicateObject.userSearch = $scope.userSearch;
		}
		$http({
			method: 'GET',
			url: '/getBookedMoves',
			params: {tableState : tableState, userid : userid},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.tableMoves = response.data.content;
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
        		if($scope.moves[i].status == "unconfirmed user") {
        		    $scope.moves[i].color = calendarConfig.colorTypes.important;
        		} else if ($scope.moves[i].status == "unconfirmed move") {
        		    $scope.moves[i].color = calendarConfig.colorTypes.warning;
        		} else if ($scope.moves[i].status == "confirmed move" || $scope.moves[i].status == "admin created") {
        		    $scope.moves[i].color = calendarConfig.colorTypes.info;
        		} else {
        		    $scope.moves[i].color = calendarConfig.colorTypes.success;
        		}
        	}
        	$scope.events = $scope.moves;
            bsLoadingOverlayService.stop();
        }, function(response) {
            bsLoadingOverlayService.stop();
        });
        $scope.getUsers();
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

	$scope.getUsers = function() {
		$http({
			method: 'GET',
			url: '/getUsersFromAssignedLocations',
			params: {userid : Session.id},
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$scope.usersInAssignedLocations = response.data;
			$('#multiselect').multiselect({
			   allSelectedText: 'All',
               buttonWidth : '160px'
            });
		});
	}
        element = $('select[name="assignedUsersSelectBox"]');
         // Watch for any changes to the length of our select element
        $scope.$watch(function () {
            return element[0].length;
         }, function () {
            element.multiselect('setOptions', {
			   allSelectedText: 'All',
               buttonWidth : '160px'
            });
            element.multiselect('rebuild');
         });

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

    $scope.addMove = function() {
		bsLoadingOverlayService.start();
		$scope.newMove.status = "admin created";
		$scope.newMove.notes = [];
	    $scope.newMove.startsAt = $scope.startsAtPicker.date;
	    var tmp = moment($scope.newMove.startsAt);
        if($scope.newMove.startsAt <= $scope.newMove.endsAt) {
           $scope.newMove.endsAt = moment($scope.newMove.startsAt).add(1, 'hours');
        }
        $http({
			method: 'POST',
			url: '/adminAddMove',
            data: $scope.newMove,
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.newMove = response.data;
        	$scope.newMove.startsAt = new Date($scope.newMove.startsAt);
        	$scope.newMove.endsAt = new Date($scope.newMove.endsAt);
        	if($scope.newMove.status == "unconfirmed user") {
        		   $scope.newMove.color = calendarConfig.colorTypes.important;
        	} else if ($scope.newMove.status == "unconfirmed move") {
        		   $scope.newMove.color = calendarConfig.colorTypes.warning;
        	} else if ($scope.newMove.status == "confirmed move" || $scope.newMove.status == "admin created") {
        		   $scope.newMove.color = calendarConfig.colorTypes.info;
        	} else {
        		   $scope.newMove.color = calendarConfig.colorTypes.success;
        	}
			$scope.moves.push(response.data);
        	$scope.events = $scope.moves;
            bsLoadingOverlayService.stop();
        }, function(response) {
            bsLoadingOverlayService.stop();
        });
	}

     $scope.startsAtPicker = {
         date: new Date().setHours(8,0,0,0)
     };
     $scope.openCalendar = function(e, picker) {
        var tmp = $scope[picker];
        tmp.open = true;
     };

	 Date.prototype.addHours= function(h){
         this.setHours(this.getHours()+h);
         return this;
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

    vm.eventEdited = function(event) {getMove
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
                    if($scope.moves[i].status == "unconfirmed user") {
                        $scope.moves[i].color = calendarConfig.colorTypes.important;
                    } else if ($scope.moves[i].status == "unconfirmed move") {
                        $scope.moves[i].color = calendarConfig.colorTypes.warning;
                    } else if ($scope.moves[i].status == "confirmed move") {
                        $scope.moves[i].color = calendarConfig.colorTypes.info;
                    } else {
                        $scope.moves[i].color = calendarConfig.colorTypes.success;
                    }
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