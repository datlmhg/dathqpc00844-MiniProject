const app = angular.module("forgot-app", []);
app.controller("forgot-ctrl", function($scope, $http){
	$scope.items=[];
	$scope.user=[];
	
	$scope.initialize = function(){
		$http.get("/rest/accounts/signup").then(resp => {
			$scope.items = resp.data;
		});
	}
	
	$scope.forgotPassword = function(){
		var item = $scope.items.find(ac => ac.username==$scope.user.username);
		if($scope.user.username == null){
			Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập tài khoản!'
				})
		}else if($scope.user.email==null){
			Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập email!'
				})
		} else if(!item){
			Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Tài khoản không tồn tại!'
				})
		} else if(item.email != $scope.user.email){
			Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Email không chính xác!'
				})
		} else {
			$http.put("/rest/accounts/forgotpassword", item).then(resp => {
				var index = $scope.items.findIndex(u => u.username==item.username);
				$scope.items[index]=item;
				Swal.fire({
					  position: 'top-end',
					  icon: 'success',
					  title: 'Mật khẩu đã được gửi vào email của bạn',
					  showConfirmButton: false,
					  timer: 1500
					})
			}).catch(error => {
				alert("Lỗi");
				console.log("Error", error)
			})
		}
	}
	
	$scope.initialize();
})