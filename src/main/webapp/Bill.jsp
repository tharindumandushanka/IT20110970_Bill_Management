<%@page import="com.model.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bills Management</title><link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Bill.js"></script></head><body>
<div class="container"><div class="row"><div class="col-6">
<h1>Bills Management </h1>
<form id="formBill" name="formBill">

Bill code:
<input id="billCode" name="billCode" type="text"
class="form-control form-control-sm">
<br>

 Customer ID:
<input id="cusID" name="cusID" type="text"
class="form-control form-control-sm">
<br> 

Units:
<input id="units" name="units" type="text"
class="form-control form-control-sm">
<br> 

KWH Charge:
<input id="kwhCharge" name="kwhCharge" type="text"
class="form-control form-control-sm">
<br> 

Fix Charge:
<input id="fixCharge" name="fixCharge" type="text"
class="form-control form-control-sm">
<br> 

Total:
<input id="total" name="total" type="text"
class="form-control form-control-sm">
<br>

<input id="btnSave" name="btnSave" type="button" value="Save"
class="btn btn-primary">

<input type="hidden" id="hidbillIDSave"
name="hidbillIDSave" value="">
</form>

<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divBillGrid">
<%
Bill billObj = new Bill();
out.print(billObj.readBill());
%>
</div>
</div> </div> </div>
</body>
</html>

