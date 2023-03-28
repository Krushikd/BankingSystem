<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
input[type=text],input[type=number] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

.container {
width: fit-content;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
 margin-left: auto;
  margin-right: auto;
  margin-top: 10%;
  padding: 10px;
   background-color:#ADD8E6;
}


</style>
</head>
<body>

<%@ include file="Header.jsp"%>

<div class="container">
  <form  method="post" action="Withdraw">

  <div>
  <h2 style="margin-left:80px" >Withdraw Money</h2>
  <div>
   <label >Account Number</label>
    <input type="number" id="accountNumber" name="accountNumber" autocomplete="off"  placeholder="Account Number" required>
  </div>
   <div>
   <label >Account Holder Name</label>
    <input type="text" id="accountHolderName" name="accountHolderName" autocomplete="off"  placeholder="Account Holder Name" required>
  </div>
  <div>
  <label >Withdraw Amount</label>
    <input type="number" id="balance" name="balance" autocomplete="off"  placeholder="Withdraw Amount" required>
  </div>
 
  </div>
   <input  type="submit" name="Withdraw" id="Withdraw"
									class="form-submit" value="Withdraw" />
  </form>
</div>

</body>
</html>