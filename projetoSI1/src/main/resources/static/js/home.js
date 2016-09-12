var app = angular.module("listaArquivos", []);

app.controller("listaArquivosCtrl", function($scope, $http) {
    
    $scope.arquivos = [];
    
    $scope.myFilesAndFolders = [];
    
    $scope.mySharedFilesAndFolders = [];
    
    $scope.filesAndFoldersSharedWithMe = [];
    
    $scope.deletedFilesAndFolders = [];
    
    window.onload = function() {
        loadingFolder();
    }
    
    var loadingFolder = function() {
    	$http.get("/home/explorer").success(function(data, status) {
    		
    		$scope.arquivos = [];
    		
    		for (var int = 0; int < data.folderDirectory.length; int++) {
				$scope.arquivos.push(data.folderDirectory[int]);
			}
    		
    		for (var int = 0; int < data.fileDirectory.length; int++) {
				$scope.arquivos.push(data.fileDirectory[int]);
			}
    		
    	});
    };
    
    $scope.showOnTable = function(lista) {
    	for (var i = 0; i < lista.length; i++) {
        	$scope.arquivos.push(lista[i]);
		}
    };


    $scope.addFolder = function() {

    	var nome = document.getElementById("newFolderName").value;
        var date = new Date();

        var folder = {
            	name:nome,
            	
            	Permission: "private",
            	            	
            	dateCreation: date.toISOString().substring(0, 10) + " " + date.toString().substring(16, 24)
         };
        
        $http.post('/home/newFolder', folder).success(function(data, status) {
            loadingFolder();

        });
        document.getElementById("newFolderName").value = "";


	};


    $scope.addTxt = function() {
        var nome = window.prompt("Nome da arquivo:");

        if(nome.length === 0){
        	Alert.render("Nome inválido", "Nome não pode ser vazio!");
        } else {
            var date = new Date();
        	
        	var file = {
        		name: nome,
        		
        		type: "MD",
        		
        		Permission: "private",
        		
            	dateCreation: date.toISOString().substring(0, 10) + " " + date.toString().substring(16, 24)
        			
        	}

        	
        	$http.post("/home/newFile", file).success(function(data, status) {
        		loadingFolder();
        		if ($scope.arquivos.length === 0) {
        			$scope.viewFile(file);
        		}
    		});
        }
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
        $http.get("/home/explorer/" + name).success(function(data, status) {
			loadingFolder();
    	});
    }

    $scope.backFolder = function() {
    	$http.get("/home/explorer/back").success(function(data, status) {
			loadingFolder();
    	});
    }
    
    $scope.deleteFolder = function(file) {
    	
    	$http.post("/home/deleteFolder", file).success(function(data, status) {

    	});
    }
    
    $scope.renameFolder = function(name, nName) {
    	
    	if (nName.length != 0) {
    		
    		data = {
    			oldName: name,
    			newName: nName
    		}
    		
    		$http.post("/home/renameFolder", data).success(function(data, status) {
    			loadingFolder();
        	});
    	}
    	else {
    		Alert.render("Nome inválido", "Nome não pode ser vazio!");
    	}
    	
    }
    
    
    $scope.deleteFile = function(file) {
    		
   	    $http.post("/home/deleteFile", file).success(function(data, status) {

   	    });

    }
    
    $scope.renameFile = function(){
    	
    	var nName = Prompt.render("Renomear", "Novo Nome:");

    	console.log(nName);
    	
    	if(nName.length != 0) {
    	
    		data = {
    			oldName: name,
    			newName: nName,
    			oldType: type,
    			newType: nType
    		}
    	
    		$http.post("/home/renameFile", data).success(function(data, status) {
    			loadingFolder();
    		});
    	}
    	else {
    		Alert.render("Nome inválido", "Nome não pode ser vazio!");
    		
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
    
    $scope.viewFile = function(arquivo) {

    	var file = {
    		name: arquivo.name,
    		type: arquivo.type
    	}
    	
    	$http.post("/editor/viewFile", file).success(function(data, status) {

    	});
    	
    }
    
    $scope.deleteL = function(arquivo) {
    	
		if (arquivo.type != "folder") {
			file = {
				name: arquivo.name,
        		type: arquivo.type
			}
			
			$scope.deleteFile(file);
		} else {
			file = {
    			name: arquivo.name
    		}  
			
			$scope.deleteFolder(file);
		}
	    loadingFolder();
    }
    
    
    $scope.blankInput = function(id) {
    	document.getElementById(id).value = "";
    }
    
});    
