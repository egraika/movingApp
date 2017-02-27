	// create the module and name it movingApp
	var movingApp = angular.module('movingApp', ['ngRoute']);

	// configure our routes
	movingApp.config(function($routeProvider) {
		$routeProvider

			// route for the home page
			.when('/', {
				templateUrl : 'pages/home.html',
				controller  : 'mainController'
			})

			// route for the about page
			.when('/free-quote', {
				templateUrl : 'pages/free-quote.html',
				controller  : 'freeQuote'
			})

			// route for the contact page
			.when('/contact', {
				templateUrl : 'pages/contact.html',
				controller  : 'contactController'
			});
	});

	// create the controller and inject Angular's $scope
	movingApp.controller('mainController', function($scope) {
		$('#swiper').show();
		$('#freeQuote').hide();
	});

	movingApp.controller('freeQuote', function($scope) {
		$('#swiper').hide();
		$('#freeQuote').show();
	});

	movingApp.controller('contactController', function($scope) {
		//$scope.message = 'Contact us! JK. This is just a demo.';
	});