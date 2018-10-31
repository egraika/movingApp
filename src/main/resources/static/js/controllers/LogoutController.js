movingApp.controller('LogoutController',  ['$scope', '$http', '$location','$rootScope',  function ($scope, $http, $location,$rootScope) {
	
    $scope.logout = function() {
    	$http({
    		  method: 'GET',
    		  url: '/logout'
    		}).then(function successCallback(response) {
                $rootScope.isUser = false;
                $rootScope.authenticated = false;
    			$location.path("/").replace();
    		  }, function errorCallback(response) {
    		    // called asynchronously if an error occurs
    		    // or server returns response with an error status.
    		  });
    }
}]);