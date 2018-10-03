movingApp.controller("editMyMoveController", ['$scope','$rootScope', '$http','$routeParams','$uibModal','$timeout','$sessionStorage','Session', function($scope,$rootScope, $http, $routeParams,$uibModal, $timeout,$sessionStorage, Session) {
	
	var moveID = $routeParams.moveID;
	$scope.alertData = {boldTextTitle: "", textAlert: "", mode: ''};
    $scope.error = false;

	$scope.init = function() {
	    $scope.error = false;
		getMoveData();
	}
	
	function getMoveData() {
	    var userid = Session.id;
		$http({
			method: 'POST',
			url: '/getMyMove',
			params: {'id' : moveID, userid : userid},
			headers:{'Content-Type': 'application/json'}
         }).then(function(response) {
            $scope.move = response.data;
            $scope.startsAtPicker.date = $scope.move.startsAt;
         }, function(response) {
            $scope.error = true;
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
			url: '/updateMyMove',
			data: $scope.move,
			headers:{'Content-Type': 'application/json'}
         }).then(function(response) {
            openAlert();
         }, function(response) {
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
}]);