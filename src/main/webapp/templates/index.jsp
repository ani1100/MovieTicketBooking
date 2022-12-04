<html ng-app="myApp">

<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-A3rJD856KowSb7dwlZdYEkO39Gagi7vIsF0jrRAoQmDKKtQBHUuLZ9AsSv4jD4Xa" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="js/Booking.js"></script>
<link rel="stylesheet" href="css/stylesheet.css">
</head>

<body>
  <br>
  <div data-ng-controller="Booking" data-ng-init= "getlist()" class="container" style="background-color:#e7feff;">
  <h2 class="d-flex justify-content-center">Movie Booking</h2>
  <br>
  <form>
	  <table style="margin-left:5%">
		  <tbody>
			  <tr>
				     <td><label>Movies</label></td>
				     <td style="width:5%"></td>
				     <td><select ng-model="movie" data-ng-options="x for x in lismovies" class="form-control" ng-change="moviesel()"></select></td> 
			  </tr>
			  <tr ng-show="movie!=null">
				     <td><label>Date</label></td>
				     <td style="width:5%"></td>
				     <td><input type="date"  id="depdate" data-ng-model="date" class="form-control"></td> 
				     <td style="width:5%"></td>
				     <td><button type="button" data-ng-click="dateselect()" class="btn btn-success">Search</button></td>
			  </tr>
			  <tr ng-show="listheatre.length>0">
				     <td><label>Theatres</label></td>
				     <td style="width:5%"></td>
				     <td><select ng-model="theatre"  data-ng-options="x for x in listheatre" class="form-control" ng-change="gettimings()"></select></td> 
			  </tr>
			  <tr ng-show="listiming.length>0">
				     <td><label>Show Timing</label></td>
				     <td style="width:5%"></td>
				     <td><select ng-model="timing"  data-ng-options="x.starttime for x in listiming" class="form-control" data-ng-change="getseats()"></select></td> 
			  </tr>
		  </tbody>
	  </table>
  <br>
  </form>
  <div class="custbook1" ng-show="rows.length>0">
	<table>
	<tr data-ng-repeat="m2 in rows">
	<td data-ng-repeat="m3 in m2"><div class="custbook2" style="background-color:{{m3.color}};" data-ng-click="selectseat(m3)"></div></td>
	</tr>
	</table>
</div>
<br>
<h1 class="clear1">&nbsp;</h1>
  <button type="button" data-ng-click="save()" class="btn btn-success" style="margin-left:5%" ng-show="rows.length>0">Submit</button> 
  
  <br>
  <br>
  <button type="button" data-toggle="modal" data-target="#myModal" id="id1" ng-show="false">Alert</button>
 <div class="modal fade" id="myModal" role="dialog" data-backdrop="static" data-keyboard="true">
    <div class="modal-dialog modal-dialog-centered">
      <div class="modal-content">
        <div class="modal-header">
          <h4 class="modal-title d-flex justify-content-center">{{alertmessage}}</h4>
        </div>
        <div class="modal-footer d-flex justify-content-center">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">OK</button>
        </div>
      </div>
    </div>
  </div>
  
  
</div>  
</body>

</html>