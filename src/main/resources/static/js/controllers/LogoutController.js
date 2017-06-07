movingApp.controller('LogoutController', function ($scope, $http) {
	
    $scope.logout = function() {
    	$http({
    		  method: 'GET',
    		  url: '/logout'
    		}).then(function successCallback(response) {
    			location.reload();
    		  }, function errorCallback(response) {
    		    // called asynchronously if an error occurs
    		    // or server returns response with an error status.
    		  });
    }
});