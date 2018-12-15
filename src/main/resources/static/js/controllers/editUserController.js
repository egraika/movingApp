movingApp.controller("editUserController", ['$scope','$rootScope', '$http','$routeParams','$uibModal','$timeout','$sessionStorage','Session','bsLoadingOverlayService', function($scope,$rootScope, $http, $routeParams,$uibModal, $timeout,$sessionStorage, Session,bsLoadingOverlayService) {

	$scope.alertData = {boldTextTitle: "", textAlert: "", mode: ''};
	var userId = $routeParams.userId;

    $scope.init = function() {
        getUser();
    }

    $scope.enabledOptions = [
        {value: true, label: 'True'},
        {value: false, label: 'False'},
    ];

	function getUser() {

	    bsLoadingOverlayService.start();
		$http({
			method: 'GET',
			url: '/getUser',
			params: {'userid' : userId},
			headers:{'Content-Type': 'application/json', 'responseType': 'arrayBuffer'}
        }).then(function(response) {
            getLocations();
            bsLoadingOverlayService.stop();
            $scope.user = response.data;
            $scope.user.authority = $scope.user.authorities[0].name;
        }, function(response) {
            $scope.error = true;
            bsLoadingOverlayService.stop();
        });
	}

	 $scope.save = function() {
		 $http({
				method: 'POST',
				url: '/updateUser',
				data: $scope.user,
				headers:{'Content-Type': 'application/json'}
			}).then(function successCallBack(response) {
				openAlert();
			});
	 }

     function getLocations() {
		$http({
			method: 'GET',
			url: '/getAllOtherLocations',
			params: {'userid' : userId},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            $scope.locations = response.data;
            $scope.locationOptions = {
                title: 'Assigned Locations',
                filterPlaceHolder: 'Start typing to filter the locations below.',
                labelAll: 'Other Locations',
                labelSelected: 'Selected Locations',
                /* angular will use this to filter your lists */
                orderProperty: 'location',
                /* this contains the initial list of all items (i.e. the left side) */
                items: response.data,
                selectedItems: $scope.user.locations
            };
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

	 $scope.updatePicture = function() {
          var f = document.getElementById('file').files[0]; // name of image
          var files = document.getElementById('file').files;
          var r = new FileReader();

         r.onloadend = function(e) {
           var data = e.target.result;
		    $http({
				method: 'POST',
				url: '/updateProfilePicture',
				data: data,
				params: {'userid' : userId},
				headers:{'Content-Type': 'image/png', 'responseType': 'arrayBuffer'}
			}).then(function successCallBack(response) {
			    $scope.user.picture = response.data;
			});
         }

         r.readAsDataURL(f);
     }

     	function saveUser() {
     	    bsLoadingOverlayService.start();
     		$http({
     			method: 'POST',
     			url: '/updateUser',
     			data: $scope.user,
     			headers:{'Content-Type': 'application/json'}
             }).then(function(response) {
                 bsLoadingOverlayService.stop();
             }, function(response) {
                 $scope.error = true;
                 bsLoadingOverlayService.stop();
             });
     	}

}]);