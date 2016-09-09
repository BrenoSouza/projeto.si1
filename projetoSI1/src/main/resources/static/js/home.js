var app = angular.module("listaArquivos", []);

app.controller("listaArquivosCtrl", function($scope, $http) {
    
    $scope.arquivos = [];
    
    $scope.myFilesAndFolders = [];
    
    $scope.mySharedFilesAndFolders = [];
    
    $scope.filesAndFoldersSharedWithMe = [];
    
    $scope.deletedFilesAndFolders = [];
    
    
    var loadingFolder = function() {
    	$http.get("http://localhost:8080/home/explorer").success(function(data, status) {

    		$scope.myFilesAndFolders = data.folderDirectory;

    		for (var i = data.fileDirectory.length - 1; i >= 0; i--) {
    			$scope.myFilesAndFolders.push(data.fileDirectory[i]);
    		}
    	});
    };
    
    $scope.showOnTable = function(list) {
    	for (var i = 0; i < list.length; i++) {
        	$scope.arquivos.push(lista[i]);
		}
    };


    loadingFolder();


    $scope.addFolder = function(nome) {
        var nome = prompt("Nome da pasta:");

        var folder = {
            	name:nome,
            	
            	permission: "private",
            	            	
            	dateCreation: new Date()
        };

        console.log(folder);
        
        $http.post('http://localhost:8080/home/newFolder', folder).success(function(data, status) {
        	
        	console.log(folder);
        });

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
    
    $scope.deleteFolder = function(name) {
    	
    	$http.post("http://localhost:8080/home/deleteFolder", name).success(function(data, status) {
			loadingFolder();
    	});
    }
    
    $scope.renameFolder = function(name, nName) {
    	
    	if (nName.length != 0) {
    		
    		data = {
    			oldName: name,
    			newName: nName
    		}
    		
    		$http.post("http://localhost:8080/home/renameFolder", data).success(function(data, status) {
    			loadingFolder();
        	});
    	}
    	else {
    		alert("Nome não pode ser vazio!");
    	}
    	
    }
    
    
    $scope.deleteFile = function(fileName, fileType) {
    	
    	var file = {
    		name: fileName,
    		type: fileType
    	}
    	
    	$http.post("http://localhost:8080/home/deleteFile", file).success(function(data, status) {
			loadingFolder();
    	});
    	
    }
    
    $scope.renameFile = function(name, nName, type, nType){
    	
    	if(nName.length != 0) {
    	
    		data = {
    			oldName: name,
    			newName: nName,
    			oldType: type,
    			newType: nType
    		}
    	
    		$http.post("http://localhost:8080/home/renameFile", data).success(function(data, status) {
    			loadingFolder();
    		});
    	}
    	else {
    		alert("Nome não pode ser vazio!!");
    		
    	}
    } 
    
    
    $scope.showSharedFilesWithMe = function() {
    	//Vai alterar o $scope.arquivos para a lista de arquivos que foram compartilhados com o usuario
    	$scope.showOnTable($scope.filesAndFoldersSharedWithMe);
    	
    }
    
    $scope.showMySharedFiles = function() {
    	//Vai alterar o $scope.arquivos para a lista de arquivos que o usuario compartilhou com outros usuarios    	
    	for (var i = 0; i < $scope.arquivos.length; i++) {
			if ($scope.arquivos[i].permission === "public") {
				$scope.mySharedFiles.push(arquivos[i]);
			}
		}
    	
    	$showOnTable($scope.mySharedFilesAndFolders);
    }
    
    $scope.showDeletedFilesAndFolders = function() {
    	$showOnTable($scope.deletedFilesAndFolders);
    }
    
});    
