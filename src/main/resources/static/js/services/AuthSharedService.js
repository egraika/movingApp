	movingApp.service('AuthSharedService', function($rootScope, $http, authService, Session) {
	 return {
	  login: function(credentials) {
	    var config = {
                ignoreAuthModule: 'ignoreAuthModule',
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            };
            $http.post('authenticate', $.param({
            	username: credentials.username, 
            	password: credentials.password
                }), config)
                .then(function successCallback(response) {
	    		authService.loginConfirmed(response.data);

	    	  }, function errorCallback(response) {
	    		  $rootScope.authenticationError = true;
	    		  Session.invalidate();
	    	  });
	  },
	  
	  getAccount: function () {
          $rootScope.loadingAccount = true;
          $http.get('security/account')
            .then(function(response) {
                authService.loginConfirmed(response.data);
                if(response.data != "" && response.data.authorities[0].name == "user") {
                    $rootScope.isUser = true;
                } else if(response.data != "") {
                    $rootScope.isUser = false;
                } else {
                    return;
                }
            }, function(response) {
                //auth failed
            });
      },
      isAuthorized: function (authorizedRoles) {
          if (!angular.isArray(authorizedRoles)) {
              if (authorizedRoles == '*') {
                  return true;
              }
              authorizedRoles = [authorizedRoles];
          }
          var isAuthorized = false;
          angular.forEach(authorizedRoles, function (authorizedRole) {
              var authorized = (!!Session.login &&
              Session.userRoles.indexOf(authorizedRole) !== -1);
              if (authorized || authorizedRole == '*') {
                  isAuthorized = true;
              }
          });
          return isAuthorized;
      }
	 };
	});