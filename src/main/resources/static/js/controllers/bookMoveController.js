movingApp.controller("bookMoveController", ['$scope', '$http','$sessionStorage','$uibModal','$window','$location',   function($scope, $http, $sessionStorage,$uibModal, $window,$location) {

	$scope.bookMove = function() {
		
		$scope.moveData.notes = [];
		var jsonString = JSON.stringify($scope.moveData);
		
		$scope.stripe.email = $scope.moveData.email;
		$http({
				method: 'POST',
				url: '/stripeDeposit',
				data: $scope.stripe,
				headers:{'Content-Type': 'application/json'}
			}).then(function successCallBack(response) {
				$scope.moveData.stripeCustomerID = response.data.stripeCustomerID;
				$scope.moveData.charges = [];
				
				$http({
					method: 'POST',
					url: '/bookMove',
					data: $scope.moveData,
					headers:{'Content-Type': 'application/json'}
				}).then(function successCallBack(response) {
					$location.path('/confirmation').replace();
				});
			});
	}
	
	// Create a Stripe client
	var stripe = Stripe('pk_live_MsyHxW1twGr0h9nPLzgZWQKP');
	// erics test key var stripe = Stripe('pk_test_QWfSfH0Sy1bTZvaVHiih9PrQ');

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
		  $scope.bookMove();
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