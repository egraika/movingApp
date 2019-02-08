	// create the module and name it movingApp
	var movingApp = angular.module('movingApp', ['ngRoute', "ui.bootstrap",'ngStorage', 'angularPayments', 'mm.foundation', 'angularSpinner', 'ngAnimate', 'angular-stripe', 'http-auth-interceptor','smart-table','bsLoadingOverlay','mwl.calendar','ui.bootstrap.datetimepicker','dualmultiselect']);

	movingApp.config(['$locationProvider','$httpProvider','$windowProvider','stConfig','$compileProvider', function($locationProvider, $httpProvider,$windowProvider,stConfig, $compileProvider) {
	     $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|file|ftp|blob):|data:image\//);
		  $locationProvider.hashPrefix('');
		  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
		  stConfig.sort.delay = 100;
		  stConfig.pipe.delay = 200;
	}]);

	movingApp.constant('USER_ROLES', {
	    all: '*',
	    admin: 'admin',
	    user: 'user',
	    mover: 'mover'
	});
	
	// configure our routes
	movingApp.config(['$routeProvider', '$httpProvider', 'USER_ROLES', function($routeProvider, $httpProvider, USER_ROLES) {

			// route for the home page
			$routeProvider.when('/', {
				templateUrl : 'pages/home.html',
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
			.when('/editMove/:moveID', {
				templateUrl : 'pages/editMove.html',
				access: {
					loginRequired: true,
					authorizedRoles: [USER_ROLES.admin,USER_ROLES.mover]
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
			.when('/contact', {
				templateUrl : 'pages/contact.html',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			.when('/tables', {
				templateUrl : 'pages/tables.html',
				access: {
					loginRequired: true,
					authorizedRoles: [USER_ROLES.admin,USER_ROLES.mover]
				}
			})
			.when('/contactConfirmation', {
				templateUrl : 'pages/contactConfirmation.html',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			})
			.when('/register', {
            	templateUrl : 'pages/templates/registration.html',
            	access: {
            		loginRequired: false,
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/confirm', {
            	templateUrl : 'pages/templates/confirmationToken.html',
            	access: {
            		loginRequired: false,
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/privacy', {
            	templateUrl : 'pages/templates/PrivacyPolicy.html',
            	access: {
            		loginRequired: false,
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/reset/password', {
            	templateUrl : 'pages/templates/passwordReset.html',
            	access: {
            		loginRequired: false,
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/reset/password/confirm', {
            	templateUrl : 'pages/templates/confirmPasswordReset.html',
            	access: {
            		loginRequired: false,
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/myMoves', {
            	templateUrl : 'pages/user/myMoves.html',
            	access: {
            		loginRequired: true,
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/editMyMove/:moveID', {
            	templateUrl : 'pages/user/editMyMove.html',
            	access: {
            		loginRequired: true,
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/edit/user/:userId', {
            	templateUrl : 'pages/user/editUser.html',
            	access: {
            		loginRequired: true,
            		authorizedRoles: [USER_ROLES.admin]
            	}
            })
            .when('/profile', {
            	templateUrl : 'pages/user/profile.html',
            	access: {
            		loginRequired: true,
            		authorizedRoles: [USER_ROLES.all]
            	}
            })
            .when('/users', {
            	templateUrl : 'pages/user/usersTable.html',
            	access: {
            		loginRequired: true,
            		authorizedRoles: [USER_ROLES.admin]
            	}
            })
			.otherwise({
				redirectTo: '/error/404',
				access: {
					loginRequired: false,
					authorizedRoles: [USER_ROLES.all]
				}
			});
	}]);
	
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
	
	movingApp.run(['$rootScope', 'AuthSharedService', 'USER_ROLES', '$location', '$http', 'Session', '$q', '$timeout','bsLoadingOverlayService', function( $rootScope, AuthSharedService, USER_ROLES, $location, $http, Session, $q, $timeout,bsLoadingOverlayService) {

		$rootScope.isUser = false;
		$rootScope.authenticated = false;
		bsLoadingOverlayService.setGlobalConfig({
			templateUrl: 'pages/loading-overlay-template.html'
		});
		
		  // route change start code ...
		 $rootScope.$on('$routeChangeStart', function(event, next) {
			 $rootScope.path = $location.path();
			 $rootScope.currentPath = $location.path() != '/' || $rootScope.authenticated;
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
		  if(data != "" && data.authorities != undefined && data.authorities[0].name == "user") {
            $rootScope.isUser = true;
            $rootScope.authenticated = true;
          } else if(data != "") {
            $rootScope.isUser = false;
            $rootScope.loadingAccount = true;
          } else {
            return;
          }
		  if($rootScope.isUser) {
		    var nextLocation = "/myMoves";
		  } else {
		    var nextLocation = "/tables";
		  }
		  var delay = ($location.path() === "/loading" ? 1500 : 0);
		 
		  $timeout(function() {
		   Session.create(data);
		   $rootScope.account = Session;
		   $rootScope.authenticated = true;
		   bsLoadingOverlayService.stop();
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
		}]);