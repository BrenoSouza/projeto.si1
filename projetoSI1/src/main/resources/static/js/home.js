var app = angular.module("listaArquivos", []);

app.controller("listaArquivosCtrl", function($scope, $http) {
    $scope.app = "lista Arquivos";
    $scope.arquivos = [

    ];

    var loadingFolder = function() {
    	$http.get("http://localhost:8080/home/explorer").success(function(data, status) {

    		$scope.arquivos = data.folderDirectory;

    		for (var i = data.fileDirectory.length - 1; i >= 0; i--) {
    			$scope.arquivos.push(data.fileDirectory[i]);
    		}
    	});
    };


    loadingFolder();


    $scope.addFolder = function(nome) {
        var name = prompt("Nome da pasta:");

        if (name.length != 0) {
			$http.get("http://localhost:8080/home/newFolder/" + name).success(function(data, status) {
				loadingFolder();
    		});
    	}   
    	else {
    		alert("Nome não pode ser vazio!");
    	} 
	};


    $scope.addTxt = function() {
        var name = window.prompt("Nome da arquivo:");

        if(name.length === 0){
            alert("O arquivo nao pode ser criado, nome vazio!")
        } else {
        	$http.get("http://localhost:8080/home/newFile/" + name + "/" + "MD").success(function(data, status) {
				loadingFolder();
    		});
        }
        console.log($scope.arquivos);
    };

    $scope.sortBy = function(valor) {
        $scope.tipoOrdenacao = valor;
        $scope.direction = !$scope.direction;
    }

    $scope.isTxt = function(type) {
        if (type === "MD") {
            return true;
        }
        return false;
    }

    $scope.changeFolder = function(name) {
        $http.get("http://localhost:8080/home/explorer/" + name).success(function(data, status) {
			loadingFolder();
    	});
    }

    $scope.backFolder = function() {
    	$http.get("http://localhost:8080/home/explorer/back").success(function(data, status) {
			loadingFolder();
    	});
    }

});    
