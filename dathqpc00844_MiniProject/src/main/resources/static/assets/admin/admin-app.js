app = angular.module("admin-app",["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider.when("/authorize", {
		templateUrl: "/assets/admin/authority/index.html",
		controller: "authority-ctrl"
	})
	.otherwise({
		template: "<h1 class='text-center'> FPT Polytechnic Administration </h1>"
	});
})