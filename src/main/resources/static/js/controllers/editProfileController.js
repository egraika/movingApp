movingApp.controller("editProfileController", ['$scope','$rootScope', '$http','$routeParams','$uibModal','$timeout','$sessionStorage','Session','bsLoadingOverlayService', function($scope,$rootScope, $http, $routeParams,$uibModal, $timeout,$sessionStorage, Session,bsLoadingOverlayService) {

	$scope.alertData = {boldTextTitle: "", textAlert: "", mode: ''};
	
	$scope.init = function() {
		getUser();
	}
	
	function getUser() {
	    var userid = Session.id;
	    bsLoadingOverlayService.start();
		$http({
			method: 'POST',
			url: '/getMyProfile',
			params: {'userid' : userid},
			headers:{'Content-Type': 'application/json'}
        }).then(function(response) {
            bsLoadingOverlayService.stop();
            $scope.user = response.data;
            if($scope.user.ccLastFour != null) {
                $scope.creditCardSet = true;
            } else {
                $scope.creditCardSet = false;
            }
            if($scope.user.charges != null) {
                $scope.isCharges = true;
            } else {
                $scope.isCharges = false;
            }
        }, function(response) {
            $scope.error = true;
            bsLoadingOverlayService.stop();
        });
	}

	 $scope.save = function() {
		 $http({
				method: 'POST',
				url: '/updateMyProfile',
				data: $scope.user,
				headers:{'Content-Type': 'application/json'}
			}).then(function successCallBack(response) {
				openAlert();
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

	// Create a Stripe client
	//var stripe = Stripe('pk_live_MsyHxW1twGr0h9nPLzgZWQKP');
    var stripe = Stripe('pk_test_QWfSfH0Sy1bTZvaVHiih9PrQ');

	// Create an instance of Elements
	var elements = stripe.elements();

	// Custom styling can be passed to options when creating an Element.
	// (Note that this demo uses a wider set of styles than the guide below.)
	var style = {
	  base: {
	    color: '#32325d',
	    lineHeight: '24px',
	    fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
	    fontSmoothing: 'antialiased',
	    fontSize: '16px',
	    '::placeholder': {
	      color: '#aab7c4'
	    }
	  },
	  invalid: {
	    color: '#fa755a',
	    iconColor: '#fa755a'
	  }
	};

	$scope.updateCreditCardInfo = function() {
	    bsLoadingOverlayService.start();
	    $scope.stripe.email = $scope.user.email;
	    var userid = Session.id;
        $http({
        		method: 'POST',
        				url: '/setCreditCard',
        				data: $scope.stripe,
        				params: {'userid': userid },
        				headers:{'Content-Type': 'application/json'}
        			}).then(function successCallBack(response) {
        			    $scope.ccLastFour = response.data.ccLastFour;
        			    $scope.cardType = response.data.cardType;
        				bsLoadingOverlayService.stop();
        				$('#checkout').modal('hide');
        			}, function errorCallback(response) {
        				var errorElement = document.getElementById('card-errors');
        				bsLoadingOverlayService.stop();
        				errorElement.style.color = "Red";
        				errorElement.style.fontSize = "large";
        				errorElement.textContent = response.data.message;
        			});
	}

	// Create an instance of the card Element
	var card = elements.create('card', {style: style});

	// Add an instance of the card Element into the `card-element` <div>
	card.mount('#card-element');

	// Handle real-time validation errors from the card Element.
	card.addEventListener('change', function(event) {
	  var displayError = document.getElementById('card-errors');
	  if (event.error) {
	    displayError.textContent = event.error.message;
	  } else {
	    displayError.textContent = '';
	  }
	});

	function stripeTokenHandler(token) {
		  // Insert the token ID into the form so it gets submitted to the server
		  var form = document.getElementById('payment-form');
		  var hiddenInput = document.createElement('input');
		  hiddenInput.setAttribute('type', 'hidden');
		  hiddenInput.setAttribute('name', 'stripeToken');
		  hiddenInput.setAttribute('value', token.id);
		  form.appendChild(hiddenInput);

		  $scope.stripe.token = token.id;
		  // Submit the form
		  $scope.updateCreditCardInfo();
	}

	function createToken() {
		  stripe.createToken(card).then(function(result) {
		    if (result.error) {
		      // Inform the user if there was an error
		      var errorElement = document.getElementById('card-errors');
		      errorElement.textContent = result.error.message;
		    } else {
		      // Send the token to your server
		      stripeTokenHandler(result.token);
		    }
		  });
	};

		$scope.submit = function() {
			  stripe.createToken(card).then(function(result) {
				    if (result.error) {
				      // Inform the user if there was an error
				      var errorElement = document.getElementById('card-errors');
				      errorElement.textContent = result.error.message;
				    } else {
				      // Send the token to your server
				      stripeTokenHandler(result.token);
				    }
			  });
		}
}]);