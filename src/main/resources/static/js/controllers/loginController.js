movingApp.controller("loginController", ['$scope', '$http','$window','$sessionStorage','$rootScope', "AuthSharedService",'bsLoadingOverlayService',  function($scope, $http, $window,$sessionStorage,$rootScope, AuthSharedService,bsLoadingOverlayService) {
		  
		  $scope.rememberMe = true;
		  $scope.login = function() {
		       bsLoadingOverlayService.start();
               $rootScope.authenticationError = false;
               $scope.credentials.username = $scope.credentials.username.toLowerCase();
               AuthSharedService.login($scope.credentials);
          }
}]);