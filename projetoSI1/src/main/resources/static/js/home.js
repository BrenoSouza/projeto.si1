var app = angular.module("listaArquivos", []);

app.controller("listaArquivosCtrl", function($scope) {
    $scope.local = [];
    $scope.app = "lista Arquivos";
    $scope.arquivos = [

    ];

    $scope.adicionaPasta = function(nome) {
        var name = prompt("Nome da pasta:");

        if(name.length === 0){
            alert("A pasta nao pode ser criada, nome vazio!")
        } else {
            $scope.getLocal().push({nome: name, tipo: "pasta", dataCreat: ((new Date()).toString()).substring(0, 24) , ultimaModif:"--", folder:"../static/images/pasta.png", lista: [] });
            delete $scope.nome;
        }
    };

    $scope.adicionaTxt = function() {
        var name = window.prompt("Nome da arquivo:");

        if(name.length === 0){
            alert("O arquivo nao pode ser criado, nome vazio!")
        } else {

            $scope.getLocal().push({nome: name, tipo: ".txt", dataCreat: ((new Date()).toString()).substring(0, 24) , ultimaModif:"--", folder:"../static/images/.txt.png" });
            delete $scope.nome;
        }
    };

    $scope.sortBy = function(valor) {
        $scope.tipoOrdenacao = valor;
        $scope.direction = !$scope.direction;
    }

    $scope.isTxt = function(tipo) {
        if (tipo === ".txt") {
            return true;
        }
        return false;
    }

    $scope.getLocal = function() {
        if ($scope.local.length === 0) {
            return $scope.arquivos;
        }
        else {
            var lista = $scope.arquivos;
            for (var i = 0; $scope.local.length - 1 >= i; i++) {
                for (var j = 0; $scope.arquivos.length - 1 >= j; j++) {
                    if(lista[j].nome === $scope.local[i]) {
                        lista = lista[j].lista;
                    }
                }
            }
            return lista;
        }
    }

    $scope.changeFolder = function(nome) {
        $scope.local.push(nome);
    }

    $scope.initialFolder = function() {
        for (var i = $scope.local.length - 1; i >= 0; i--) {
            $scope.local.pop();
        }        
    }

    $scope.backFolder = function() {
        $scope.local.pop();
    }

});    
