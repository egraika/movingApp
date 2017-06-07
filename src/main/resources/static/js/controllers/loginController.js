movingApp.controller("loginController", ['$scope', '$http','$window','$sessionStorage','$rootScope', "AuthSharedService",  function($scope, $http, $window,$sessionStorage,$rootScope, AuthSharedService) {
		  
		  $scope.rememberMe = true;
		  $scope.login = function() {
		   $rootScope.authenticationError = false;
		   AuthSharedService.login($scope.credentials);
		  }
}]);