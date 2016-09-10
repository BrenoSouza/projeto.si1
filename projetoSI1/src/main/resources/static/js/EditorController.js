var app = angular.module("editor", []);

app.controller("editorCtrl", function($scope, $http) {

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
	
	$scope.getTxt = function() {
        $http.get("http://localhost:8080/editor/viewFile").success(function(data, status) {
			$scope.file = data;
			console.log("heitor e seu consolo")
			console.log($scope.file);
    	});
        alert("nao funfaaaaaaa");
    }
		
});
