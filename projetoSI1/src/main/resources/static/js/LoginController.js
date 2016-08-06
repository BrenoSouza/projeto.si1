var appL = angular.module("inicial", []);

appL.controller("loginCtrl", function($scope) {

		$scope.login = "okk";
		$scope.email = "";
		$scope.password = "";

});

appL.controller("RegisterCtrl", function($scope) {

	$scope.login = "";
	$scope.email = "jose.souza";
	$scope.password = "";

});