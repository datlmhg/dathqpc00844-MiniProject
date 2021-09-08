const app = angular.module("signup-app", []);
app.controller("signup-ctrl", function($scope, $http){
	
	$scope.items=[];
	$scope.confirmPassword="";
	$scope.initialize = function(){
		$http.get("/rest/accounts/signup").then(resp => {
			$scope.items = resp.data;
			console.log($scope.items)
		});
	}
	
	$scope.account={
		username:"",
		password:"",
		fullname:"",
		email:"",
		role:[
			"user"
		],
		create(){
			var account = angular.copy(this);
			var item = $scope.items.find(ac => ac.username==account.username);
			if(account.username == ""){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập tài khoản!'
				})
			} else if(item){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Tài khoản đã tồn tại!'
				})
			}else if(account.fullname == ""){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập họ và tên!'
				})
			}else if(account.email == ""){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập email!'
				})
			} else if(account.password == ""){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập mật khẩu!'
				})
			}else if($scope.confirmPassword == ""){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Vui lòng nhập xác nhận mật khẩu!'
				})
			} else if(account.password!= $scope.confirmPassword){
				Swal.fire({
				  icon: 'error',
				  title: 'Thất bại',
				  text: 'Xác nhận mật khẩu không đúng!'
				})
			} else{
				$http.post("/rest/accounts/signup", account).then(resp => {
					Swal.fire({
					  position: 'top-end',
					  icon: 'success',
					  title: 'Đăng ký thành công!',
					  showConfirmButton: false,
					  timer: 1500
					})
					$scope.initialize();
				}).catch(error => {
					alert("Đặt hàng lỗi!")
					console.log(error)
				})
			}
		}
	}
	
	$scope.initialize();
	
})