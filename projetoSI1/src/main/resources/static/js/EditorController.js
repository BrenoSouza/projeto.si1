var appE = angular.module("editor", []);


appE.controller("editorCtrl", function($scope, $http) {

	$scope.file = {
		
			
	}
	
	$scope.saveFile = function(name, type, nName, nType, value) {

		var arquivo = {
			oldName: name,
			oldType: type,
			newName: nName,
			newType: nType,
			newValue: value
		}

	}
	
	var getTxt = function() {
        $http.get("http://localhost:8080/editor/viewFile").success(function(data, status) {
			$scope.file = data;
			console.log("heitor e seu consolo")
			console.log($scope.file);
    	});
    }
		
});
