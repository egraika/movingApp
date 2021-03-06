movingApp.controller("editMoveController", ['$scope','$rootScope', '$http','$routeParams','$uibModal','$timeout','$sessionStorage','Session', function($scope,$rootScope, $http, $routeParams,$uibModal, $timeout,$sessionStorage, Session) {
	
	var moveID = $routeParams.moveID;
	$scope.refundError = false;
	$scope.chargeError = false;
	$scope.refundAmountError = false;
	$scope.alertData = {boldTextTitle: "", textAlert: "", mode: ''};
	
	$scope.init = function() {
	    $scope.isCharges = false;
		$scope.cardExpired = false;
		$scope.creditCardSet = false;
		getMoveData();
	}
	
	function getMoveData() {
		$http({
			method: 'POST',
			url: '/getMove',
			params: {'id' : moveID},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
			$scope.move = response.data;
			getAllNotAssignedUsers();
			$scope.charges = $scope.move.charges;
            if($scope.charges.length > 0) {
                $scope.isCharges = true;
            }
			$scope.startsAtPicker.date = $scope.move.startsAt;
			if($scope.move.user != null && $scope.move.user.expirationDate == null) {
			    $scope.creditCardSet = false;
			} else {
			    $scope.creditCardSet = true;
			    if($scope.move.user.expirationDate <= new Date()) {
			        $scope.cardExpired = true;
			    }
			}
        }, function(response) {
            $scope.getMoveDataError = true;
        });
	}

    $scope.isEmptyObject = function(obj) {
        return obj == null;
    };

     function getAllNotAssignedUsers() {
		$http({
			method: 'GET',
			url: '/getAllNotAssignedToMove',
			params: {'moveid' : $scope.move.id},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.userOptions = {
                title: 'Assigned Users',
                filterPlaceHolder: 'Start typing to filter the locations below.',
                labelAll: 'Other Users',
                labelSelected: 'Selected Users',
                /* angular will use this to filter your lists */
                orderProperty: 'location',
                /* this contains the initial list of all items (i.e. the left side) */
                items: response.data,
                selectedItems: $scope.move.assignedUsers
            };
        }, function(response) {
        });
     }

	 $scope.addNote = function() {
		 var name = Session.firstName + " " + Session.lastName;
			$http({
				method: 'POST',
				url: '/addNote',
				params: {'id' : moveID, 'username' : name},
				headers:{'Content-Type': 'application/json'}
			}).then(function successCallBack(response) {
				$scope.move.notes.push(response.data);
			});
	}

	 $scope.save = function() {
	     $scope.move.startsAt = $scope.startsAtPicker.date;
	     var tmp = moment($scope.move.startsAt);
	     $scope.move.startsAt = $scope.move.startsAt;
         if($scope.move.startsAt <= $scope.move.endsAt) {
            $scope.move.endsAt = moment($scope.move.startsAt).add(1, 'hours');
         }
		 $http({
				method: 'POST',
				url: '/updateMove',
				data: $scope.move,
				headers:{'Content-Type': 'application/json'}
			}).then(function successCallBack(response) {
				openAlert("Saved Successfully");
			});
	 }

	 $scope.addCharge = function() {
		 $http({
				method: 'POST',
				url: '/addCharge',
				params: {'amount' : $scope.amount, 'id' : moveID},
				headers:{'Content-Type': 'application/json'}
         }).then(function(response) {
			$scope.isCharges = true;
		    $scope.charges.push(response.data);
         }, function(response) {
            $scope.chargeError = true;
         });
	 }

	 $scope.addRefund = function() {
         $scope.refundAmountError = false;
         $scope.refundError = false;
         if($scope.amountToRefund > $scope.currentChargeAmount) {
            $scope.refundAmountError = true;
            return;
         }
		 $http({
				method: 'POST',
				url: '/refundCharge',
				params: {'amount' : $scope.amountToRefund, 'id' : $scope.currentChargeId},
				headers:{'Content-Type': 'application/json'}
         }).then(function(response) {
            $('#refund').modal('hide');
            $scope.charges.push(response.data);
         }, function(response) {
            $scope.refundError = true;
         });
	 }

	 $scope.setChargeId = function(id, amount) {
	    $scope.currentChargeId = id;
	    $scope.currentChargeAmount = amount;
	 }
	 
	 function openAlert(textTitle){
		 $scope.alertData.mode = "success";
		 $scope.alertData.boldTextTitle = textTitle;
		 var modalInstance = $uibModal.open({
		     templateUrl: 'saveAlert',
		     backdrop: true,
		     keyboard: true,
		     backdropClick: true,
		     size: 'sm',
		     controller: ['$scope', 'data', function($scope, data){
		    	 $scope.alertData = data;
		    	 $scope.close = function(){
		    		 modalInstance.close();
				 };
				        	
				 $timeout(function() {
					 modalInstance.close();
				 }, 2000);
		     }],
		     resolve: {
		    	 data: function () {
		    		 return $scope.alertData;
				 }
		     }
		 });
	 }

     $scope.startsAtPicker = {
         date: null
     };
     $scope.openCalendar = function(e, picker) {
        var tmp = $scope[picker];
        tmp.open = true;
     };

	 Date.prototype.addHours= function(h){
         this.setHours(this.getHours()+h);
         return this;
     }

     $scope.textMoveInformation = function() {
		$http({
			method: 'POST',
			url: '/textMoveInformation',
			params: {'moveid' : moveID},
            data: $scope.move.assignedUsers,
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            openAlert("Texts Sent");
        }, function(response) {
        });
     }
}]);