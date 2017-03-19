var app = angular.module('myApp', ['kendo.window', 'kendo.directives']);

app.controller('myCtrl', [ '$scope', '$http', '$filter', '$log', function($scope, $http, $filter, $log) {

	$scope.results=[];
	var indi = new Date();
	$scope.bugun=indi.toString("yyyy/MM/dd");
	$scope.tarix2=$scope.bugun;
	$scope.tarix=$scope.bugun;
	$scope.gunluk = [];
	
	$scope.xeberdarliq = [];
	
	angular.element(document).ready(function() {
		
		
		console.log("--------:  "+$scope.bugun);
		$("#tarix2").val($scope.bugun);
		$("#tarix1").val($scope.bugun);
		function myFunction() {
			angular.forEach($scope.xeberdarliq, function(item){
				console.log("-----------------");
                console.log(item.notifTime)
                var notifTime = new Date(item.notifTime);
                var startTime = new Date(item.startTime);
                var now = new Date();
                notifTime = notifTime.toString("dd/MM/yyyy HH:mm");
                now = now.toString("dd/MM/yyyy HH:mm");
                if(notifTime === now){
                	$.notify(startTime.toString("HH:mm")+" zamanında işin var", "info");
                }
                console.log("notifTime: "+notifTime);
                console.log("now: "+now);
			});
			
			
			
			var now = new Date();
			//now = now.format("dd/mm/yyyy HH:MM TT");
			//console.log($scope.xeberdarliq);
			console.log(now.toString("now: "+"dd/MM/yyyy hh:mm"));
		}

		var i = setInterval(function() { myFunction(); }, 10000);
		
		function endChange() {
			$scope.start = $("#start").val();
			console.log($scope.start);
		}

		function startChange() {
			var startTime = start.value();
			if (startTime) {
				startTime = new Date(startTime);
				end.max(startTime);
				startTime.setMinutes(startTime.getMinutes() + this.options.interval);
				end.min(startTime);
				end.value(startTime);
			}
			$scope.end = $("#end").val();
			console.log($scope.end);
		}

		//init start timepicker
		
		var start = $("#start").kendoTimePicker({
			format: "HH:mm",
			change : startChange
		}).data("kendoTimePicker");

		//init end timepicker
		var end = $("#end").kendoTimePicker({
			format: "HH:mm",
			change : endChange
		}).data("kendoTimePicker");
		//define min/max range

		start.min("00:00");
		start.max("23:30");
		//define min/max range
		end.min("00:30");
		end.max("24:00");
	});
	
	$scope.logout = function(){
		$http.get("/logout").success(
				function(data) {
					console.log(data);
					if(data.RESULT=='OK'){
						$.notify("Tapşırıq silindi!", "success");
						$scope.getusertasksbydate($scope.tarix2);
					}else{
						//$scope.usertasks = data.USERTASKS;
						console.log("Xəta baş verdi");
						$.notify("Tapşırıq silinən zaman xəta baş verdi.", "warn");
					}
				});
	}
	
	$("#tarix1").kendoDatePicker({
		change : function() {
			$scope.tarix = $("#tarix1").val();
			$scope.start = $("#start").val();
			$scope.end = $("#end").val();
			console.log($scope.tarix + " " + $scope.start);
			console.log($scope.tarix + " " + $scope.end);
		},
		format : "yyyy/MM/dd"
	});
	
	$("#tarix2").kendoDatePicker({
		change : function() {
			$scope.tarix2 = $("#tarix2").val();
			console.log($scope.tarix2);
			$scope.getusertasksbydate($scope.tarix2);
		},
		format : "yyyy/MM/dd"
	});

	$("#notif").kendoMaskedTextBox({
		mask : "000",
		promptChar : " " //specify prompt char as empty char
	});

	$scope.user = {};
	$scope.userTask = {};
	
	$scope.insertuser = function() {
		console.log($scope.user);
		$http.post("/hellorest", $scope.user).success(
			function(data) {
				console.log(data);
			});
	}

	/*dəqiqəni saata çevirir*/
	/*String.prototype.toHHMMSS = function () {
	    var hours   = Math.floor(timeinsec / 60);
	    var minutes = Math.floor(timeinsec - (hours * 60));

	    if (hours   < 10) {hours   = "0"+hours;}
	    if (minutes < 10) {minutes = "0"+minutes;}
	    var time    = hours+':'+minutes;
	    return time;
	}
	document.getElementById("mmss").innerHTML = timeinsec.toHHMMSS();*/
	
	$scope.usertasks = [];
	
	/*$scope.convertTime12to24 = function testtt(time12h){
		const [time, modifier] = time12h.split(' ');

		  let [hours, minutes] = time.split(':');

		  if (hours === '12') {
			  hours = '00';
		  }
		  
		  if (hours<10){
			  hours = '0'+hours;
		  }

		  if (modifier === 'PM') {
			  hours = parseInt(hours, 10) + 12;
		  }

		  return hours + ':' + minutes;
	}*/
	
	$scope.deleteusertask = function(taskId) {
		$http.delete("/deleteusertask/"+taskId).success(
			function(data) {
				console.log(data);
				if(data.RESULT=='OK'){
					$.notify("Tapşırıq silindi!", "success");
					$scope.getusertasksbydate($scope.tarix2);
				}else{
					//$scope.usertasks = data.USERTASKS;
					console.log("Xəta baş verdi");
					$.notify("Tapşırıq silinən zaman xəta baş verdi.", "warn");
				}
			});
	}
	
	
	$scope.newEditRow = {};
	$scope.getNewEditRow = function(){
		$scope.start = $("#start").val();
		$scope.end = $("#end").val();
		$scope.tarix = $("#tarix1").val();
		
		$scope.userTask.taskId = $scope.userTask.taskId;
		$scope.userTask.startTime = $scope.tarix + ' ' + $scope.start;
		$scope.userTask.endTime = $scope.tarix + ' ' + $scope.end;
		$scope.userTask.notif = $("#notif").val();
		$scope.userTask.taskName = $("#name").val();
		$scope.userTask.taskContent = $("#content").val();
		return $scope.userTask;
		
	}
	
	$scope.newTask = function(){
		$scope.checked=true; 
		$scope.basliq='Yeni tapşırıq';
		$("#tarix2").val($scope.bugun);
		$("#start").val('12:00');
		$("#end").val('12:30');
		$("#notif").val('');
		$("#name").val('');
		$("#content").val('');
	}
	
	$scope.insertusertask = function(){
		//$scope.getNewEditRow();
		$http.post("/newtask", $scope.getNewEditRow()).success(
			function(data) {
				console.log(data);
				if(data.RESULT=='OK'){
					$.notify("Tapşırıq qeydə alındı", "success");
					console.log("Tapşırıq qeydə alındı");
					if($scope.tarix==$scope.tarix2){
						$scope.getusertasksbydate($scope.tarix2);
					}
					$scope.checked=false;
				}else{
					$.notify(data.MESSAGE, "warn");
				}
			});
		$scope.userTask = {};
	}
	
	$scope.open = function(){
		$.notify("Access granted", "success");
	}
	
	$scope.editusertask = function(row) {
		$scope.basliq='Tapşırığı düzəlt';
		
		//$scope.userTask = row;
		
		$scope.userTask.taskId = row.taskId;
		$scope.userTask.startTime = row.startTime;
		$scope.userTask.endTime = row.endTime;
		$scope.userTask.notif = row.notif;
		$scope.userTask.taskName = row.taskName;
		$scope.userTask.taskContent = row.taskContent;		
		
		var sTime = new Date(row.startTime);
		var eTime = new Date(row.endTime);
		
		var time = sTime.toString("yyyy/MM/dd");
		sTime = sTime.toString("HH:mm");
		eTime = eTime.toString("HH:mm")

		$("#tarix2").val(time);
		$("#start").val(sTime);
		$("#end").val(eTime);
		$("#notif").val(row.notif);
		$("#name").val(row.taskName);
		$("#content").val(row.taskContent);
		
		$scope.checked = true;
	}
	
	$scope.getusertasksbydate = function(toDay) {
		$http.post("/getusertaskbydate", "tarix="+toDay, {headers: {'Content-Type': 'application/x-www-form-urlencoded'}}).success(
			function(data) {
				console.log(data);
				$scope.results = data;
				if(toDay === $scope.bugun){
					$scope.xeberdarliq = $scope.results;
				}
			});
	}
	
	$scope.getusertasksbydate($scope.bugun);	
} ]);
