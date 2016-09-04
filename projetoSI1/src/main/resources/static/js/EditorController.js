var appE = angular.module("editor", []);


appE.controller("editorCtrl", function($scope, $http) {

	$scope.saveFile = function(name, type, nName, nType, value) {

		var data = {
			oldName: name,
			oldType: type,
			newName: nName,
			newType: nType,
			newValue: value
		}

		$http.post("http://localhost:8080/home/", data).success(function(data, status) {
    		
    	});

	}

});
