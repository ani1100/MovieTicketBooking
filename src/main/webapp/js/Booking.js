"use strict"
var myApp = angular.module('myApp',[]);
myApp.controller('Booking',Booking);

function Booking($scope,$http)
{
	$scope.lismovies=[];
	$scope.getlist=function()
	{
		$http({
	    method : "GET",
	      url : "getactivemovies",
	  }).then(function mySuccess(response) {
	  console.log(response.data);
	  if(response.data[0]=="Failed")
	  {
		$scope.alert(response.data[1]);
	  }
	  else
	  {
		$scope.lismovies=response.data.slice(1);
	  }
	  }, function myError(response) {
	  $scope.alert("Something went wrong.Please try Again Later");
	});
	}
	
	$scope.moviesel=function()
	{	
		$scope.rows=[];
		$scope.timing=null;
		$scope.listiming=[];
		$scope.theatre=null;
		$scope.listheatre=[];
		$scope.date=null;
	}
	
	
	$scope.dateselect=function()
	{
		$scope.rows=[];
		$scope.timing=null;
		$scope.listiming=[];
		$scope.theatre=null;
		$scope.listheatre=[];
		if($scope.date==null)
		{
			$scope.alert("Please select a date");
			return;
		}
		var d1 = new Date();
	  	var year1 = d1.getFullYear().toString();
	  	var month1 = (d1.getMonth()+1).toString();
     	if(month1.length==1)
      	{
			month1="0".concat(month1);
	  	}
      	var day1 = d1.getDate().toString();
      	if(day1.length==1)
      	{
	  	  day1="0".concat(day1);
      	}
      	$scope.currentdate=year1.concat("-").concat(month1).concat("-").concat(day1);		
		var d=$scope.date;
		year1 = d.getFullYear().toString();
	  	month1 = (d.getMonth()+1).toString();
     	if(month1.length==1)
      	{
			month1="0".concat(month1);
	  	}
      	day1 = d.getDate().toString();
      	if(day1.length==1)
      	{
	  	  day1="0".concat(day1);
      	}
      	$scope.date1=year1.concat("-").concat(month1).concat("-").concat(day1);	 
      	if($scope.date1<$scope.currentdate)
      	{
	       $scope.alert("Past Date cannot be selected");
	       $scope.date=null;
	       return;
		}
		else
		{
			if($scope.date1==$scope.currentdate)
			{
				   var h1=d1.getHours().toString();
			       if(h1.length==1)
			       {
						h1="0".concat(h1);
				   }	
			       var m1=d1.getMinutes().toString();
			       if(m1.length==1)
			       {
						m1="0".concat(m1);
				   }
				   var s1=d1.getSeconds().toString();
				   if(s1.length==1)
				   {
						s1="0".concat(s1);
				   }
				   $scope.time1=h1.concat(":").concat(m1).concat(":").concat(s1);
			}
			else
			{
				$scope.time1="00:00:00";
			}
		}
      	$scope.gettheatres();
	}
	
	$scope.gettheatres=function()
	{
		$scope.reqpojo={};
		$scope.reqpojo.moviename=$scope.movie;
		$scope.reqpojo.date=$scope.date1;
		$scope.reqpojo.time=$scope.time1;
		$http({
	    method : "POST",
	      url : "gettheatres",
	      data: $scope.reqpojo
	  }).then(function mySuccess(response) {
	   if(response.data=="")
	   {
		$scope.alert("No theatre Found for the selected movie and date");
	   }
	   else
	   {
		 $scope.listheatre=response.data;
		}
	  }, function myError(response) {
	  $scope.alert("Something went wrong.Please try Again Later");
	  });
	}
	
	$scope.gettimings=function()
	{
		$scope.rows=[];
		$scope.timing=null;
		$scope.listiming=[];
		$scope.timingreq={};
		$scope.timingreq.reqpojo=$scope.reqpojo;
		$scope.timingreq.thname=$scope.theatre;
		$http({
	    method : "POST",
	      url : "fetchtheatresbymovieanddate",
	      data: $scope.timingreq
	  }).then(function mySuccess(response) {
	   if(response.data=="")
	   {
		$scope.alert("No Show Available");
	   }
	   else
	   {
		 $scope.listiming=response.data;
		}
	  }, function myError(response) {
	  $scope.alert("Something went wrong.Please try Again Later");
	  });
	}
	
	$scope.alert=function(val)
	{
	$scope.alertmessage=val;
	document.getElementById("id1").click();
	}
	
	$scope.getseats=function()
	{
		$scope.rows=[];
		$http({
	    method : "POST",
	      url : "fetchseatdtls",
	      data: $scope.timing
	  }).then(function mySuccess(response) {
	  $scope.rows=[];
	  var ct=0;
	  $scope.items=[];
	  for(var i=0;i<response.data.length;i++)
	  {
		var obj={};
		obj.seat=response.data[i].seatno;
		if(response.data[i].status=="Y")
		{
			obj.color="grey";
			obj.status="A";
		}
		else
		{
			obj.color="white";
			obj.status="U";
		}
		$scope.items.push(obj);
		ct+=1;
		if(ct==7)
		{
			ct=0;
			$scope.rows.push($scope.items);
			$scope.items=[];
		} 
	  }
	  if($scope.items.length>0)
	  {
	  	$scope.rows.push($scope.items);
	  }
	  }, function myError(response) {
	  $scope.alert("Something went wrong.Please try Again Later");
	  });
	}
	
	$scope.selectseat=function(obj)
	{
		if(obj.color=="grey")
		{
			return;
		}
		else
		{
			if(obj.color=="white")
			{
				obj.color="red";
				obj.status="O";
			}
			else
			{
				obj.color="white";
				obj.status="U";
			}
		}
	}
	
	$scope.save=function()
	{
		$scope.seatslis=[];
		for(var i=0;i<$scope.rows.length;i++)
		{
			for(var j=0;j<$scope.rows[i].length;j++)
			{
				if($scope.rows[i][j].status=="O")
				{
					$scope.seatslis.push($scope.rows[i][j].seat);
				}
			}
		}
		if($scope.seatslis.length==0)
		{
			$scope.alert("No Seat Selected");
			return;
		}
		$scope.pojo={};
		$scope.pojo.seats=$scope.seatslis;
		$scope.pojo.movieid=$scope.timing.id;
		$http({
	    method : "POST",
	      url : "savedetails",
	      data: $scope.pojo
	  }).then(function mySuccess(response) {
		if(response.data[0]=="Failed")
		{
			if(response.data[1]=="Booking Closed for this show")
			{
				$scope.alert(response.data[1]);
				$scope.rows=[];
				$scope.timing=null;
				$scope.listiming=[];
				$scope.theatre=null;
			}
			else
			{
				$scope.alert(response.data[1]);
				$scope.getseats();
			}
		}
		else
		{
			$scope.alert(response.data[1]);
			$scope.getseats();
		}
	  
	  }, function myError(response) {
	  $scope.alert("Something went wrong.Please try Again Later");
	  });
	}
	
}