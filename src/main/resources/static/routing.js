	// create the module and name it movingApp
	var movingApp = angular.module('movingApp', ['ngRoute', "ui.bootstrap",'ngStorage', 'angularPayments', 'mm.foundation', 'angularSpinner', 'ngAnimate', 'angular-stripe', 'http-auth-interceptor']);

	movingApp.config(['$locationProvider','$httpProvider','$windowProvider', function($locationProvider, $httpProvider,$windowProvider) {
		  $locationProvider.hashPrefix('');
		  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
	}]);
	
	movingApp.constant('USER_ROLES', {
	    all: '*',
	    admin: 'admin',
	    user: 'user'
	});
	
	// configure our routes
	movingApp.config(function($routeProvider, $httpProvider, USER_ROLES) {

			// route for the home page
			$routeProvider.when('/', {
				templateUrl : 'pages/home.html',
				controller : 'loginController',
			    controllerAs: 'controller',
				access: {
					loginRequired: false,
			        authorizedRoles: [USER_ROLES.all]
			    }
			})

			.when('/bookmove', {
				templateUrl : 'pages/bookMove.html',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			
			.when('/bookedMoves', {
				templateUrl : 'pages/bookedMoves.html',
				access: {
					loginRequired: true,
					authorizedRoles: [USER_ROLES.admin]
				}
			})

			.when('/editMove/:moveID', {
				templateUrl : 'pages/editMove.html',
				access: {
					loginRequired: true,
					authorizedRoles: [USER_ROLES.admin]
				}
			})
			
			.when('/confirmation', {
				templateUrl : 'pages/confirmation.html',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			.when("/error/:code", {
				templateUrl: "pages/error.html",
			    controller: "ErrorController",
			    access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			.when('/login', {
				templateUrl : 'pages/login.html',
				controller : 'loginController',
			    controllerAs: 'controller',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			.when('/about', {
				templateUrl : 'pages/about.html',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			.when('/services', {
				templateUrl : 'pages/services.html',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			.when('/contact', {
				templateUrl : 'pages/contact.html',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			.otherwise({
				redirectTo: '/error/404',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			});
	});
	
	movingApp.directive('numbersOnly', function () {
	    return {
	        require: 'ngModel',
	        link: function (scope, element, attr, ngModelCtrl) {
	            function fromUser(text) {
	                if (text) {
	                    var transformedInput = text.replace(/[^0-9]/g, '');

	                    if (transformedInput !== text) {
	                        ngModelCtrl.$setViewValue(transformedInput);
	                        ngModelCtrl.$render();
	                    }
	                    return transformedInput;
	                }
	                return undefined;
	            }            
	            ngModelCtrl.$parsers.push(fromUser);
	        }
	    };
	});
	
	movingApp.directive('access', [
	    'AuthSharedService',
	    function (AuthSharedService) {
	        return {
	            restrict: 'A',
	            link: function (scope, element, attrs) {
	                var roles = attrs.access.split(',');
	                if (roles.length > 0) {
	                    if (AuthSharedService.isAuthorized(roles)) {
	                        element.removeClass('hide');
	                    } else {
	                        element.addClass('hide');
	                    }
	                }
	            }
	      };
	}]);
	
	movingApp.run(function($rootScope, AuthSharedService, USER_ROLES, $location, $http, Session, $q, $timeout) {
		 
		  // route change start code ...
		 $rootScope.$on('$routeChangeStart', function(event, next) {
		 if (next.originalPath === "/login" && $rootScope.authenticated) {
		   event.preventDefault();
		  } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
		   event.preventDefault();
		   $rootScope.$broadcast("event:auth-loginRequired", {});
		  }else if(next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
		   event.preventDefault();
		   $rootScope.$broadcast("event:auth-forbidden", {});
		  }
		 });
		 
			// Call when the 403 response is returned by the server
		 $rootScope.$on('event:auth-forbidden', function(rejection) {
		  $rootScope.$evalAsync(function() {
		   $location.path('/error/403').replace();
		  });
		 });
		 
		// Call when the the client is confirmed
		 $rootScope.$on('event:auth-loginConfirmed', function(event, data) {
		  $rootScope.loadingAccount = false;
		  var nextLocation = ($rootScope.requestedUrl ? $rootScope.requestedUrl : "/bookedMoves");
		  var delay = ($location.path() === "/loading" ? 1500 : 0);
		 
		  $timeout(function() {
		   Session.create(data);
		   $rootScope.account = Session;
		   $rootScope.authenticated = true;
		   $location.path(nextLocation).replace();
		  }, delay);
		 
		 });
		 
		 // Call when the 401 response is returned by the server
		 $rootScope.$on('event:auth-loginRequired', function(event, data) {
		  if ($rootScope.loadingAccount && data.status !== 401) {
		   $rootScope.requestedUrl = $location.path()
		   $location.path('/loading');
		  } else {
		   Session.invalidate();
		   $rootScope.authenticated = false;
		   $rootScope.loadingAccount = false;
		   $location.path('/');
		  }
		 });
		 
		 // Get already authenticated user account
		 AuthSharedService.getAccount();
		});