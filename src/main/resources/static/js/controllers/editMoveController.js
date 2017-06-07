movingApp.controller("editMoveController", ['$scope','$rootScope', '$http','$routeParams','$uibModal','$timeout','$sessionStorage','Session', function($scope,$rootScope, $http, $routeParams,$uibModal, $timeout,$sessionStorage, Session) {
	
	var moveID = $routeParams.moveID;
	$scope.alertData = {boldTextTitle: "", textAlert: "", mode: ''};
	
	$scope.init = function() {
		getMoveData();
	}	
	
	function getMoveData() {
		$http({
			method: 'POST',
			url: '/getMove',
			params: {'id' : moveID},
			headers:{'Content-Type': 'application/json'}
		}).then(function successCallBack(response) {
			$scope.move = response.data;
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
		 $http({
				method: 'POST',
				url: '/updateMove',
				data: $scope.move,
				headers:{'Content-Type': 'application/json'}
			}).then(function successCallBack(response) {
				openAlert();
			});
	 }
	 
	 $scope.addCharge = function() {
		 $http({
				method: 'POST',
				url: '/addCharge',
				params: {'customerID' : $scope.move.stripeCustomerID, 'amount' : $scope.amount, 'id' : moveID},
				headers:{'Content-Type': 'application/json'}
			}).then(function successCallBack(response) {
				$scope.move.charges.push(response.data);
			});
	 }
	 
	 function openAlert(){
		 $scope.alertData.mode = "success";
		 $scope.alertData.boldTextTitle = "Saved Successfully";
		 var modalInstance = $uibModal.open({
		     templateUrl: 'saveAlert',
		     backdrop: true,
		     keyboard: true,
		     backdropClick: true,
		     size: 'sm',
		     controller: function($scope, data){
		    	 $scope.alertData = data;
		    	 $scope.close = function(){
		    		 modalInstance.close();
				 };
				        	
				 $timeout(function() {
					 modalInstance.close();
				 }, 2000);
		     },
		     resolve: {
		    	 data: function () {
		    		 return $scope.alertData;
				 }
		     }
		 });
	 }
	 
	 function openChargeAlert(){
		 var modalInstance = $uibModal.open({
		     templateUrl: 'chargeAlert',
		     backdrop: true,
		     keyboard: true,
		     backdropClick: true,
		     size: 'sm',
		     controller: function($scope, data){
		    	 //$scope.alertData = data;
		    	 $scope.close = function(){
		    		 modalInstance.close();
				 };
		     },
		 });
	 }
	 
}]);