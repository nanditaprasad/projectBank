var app = angular.module('demoApp',['ngMessages']);

app.controller('demoCtrl',function($scope){
  $scope.newUser = {
    userPassword:''
  };
});
