/**
*
*/

$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
{
$("#alertSuccess").hide();
}
$("#alertError").hide();
});




// SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
$("#alertSuccess").text("");
$("#alertSuccess").hide();
$("#alertError").text("");
$("#alertError").hide();

// Form validation-------------------
var status = validateBillForm();
if (status != true)
{
$("#alertError").text(status);
$("#alertError").show();
return;
}



// If valid------------------------
var type = ($("#hidbillIDSave").val() == "") ? "POST" : "PUT";
$.ajax(
{
url : "BillAPI",
type : type,
data : $("#formBill").serialize(),
dataType : "text",

complete : function(response, status)
{
onBillSaveComplete(response.responseText, status);
}
});
});




function onBillSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divBillGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}

$("#hidbillIDSave").val("");
$("#formBill")[0].reset();
}





// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidbillIDSave").val($(this).closest("tr").find('#hidbillIDUpdate').val());
$("#billCode").val($(this).closest("tr").find('td:eq(0)').text());
$("#cusID").val($(this).closest("tr").find('td:eq(1)').text());
$("#units").val($(this).closest("tr").find('td:eq(2)').text());
$("#kwhCharge").val($(this).closest("tr").find('td:eq(3)').text());
$("#fixCharge").val($(this).closest("tr").find('td:eq(4)').text());
$("#total").val($(this).closest("tr").find('td:eq(5)').text());
});




// CLIENT-MODEL================================================================
function validateBillForm()
{
// Name
if ($("#billCode").val().trim() == "")
{
return "Insert Bill Code.";
}
// Email
if ($("#cusID").val().trim() == "")
{
return "Insert Customer ID.";
}

// Age
if ($("#units").val().trim() == "")
{
return "Insert Units.";
}


//Password
if ($("#kwhCharge").val().trim() == "")
{
return "Insert KWH  Charge.";
}

//Phone
if ($("#fixCharge").val().trim() == "")
{
return "Insert Fix Charge.";
}

//nic
if ($("#total").val().trim() == "")
{
return "Insert Total.";
}

return true;
}



//DELETE
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "BillAPI",
type : "DELETE",
data : "billID=" + $(this).data("billid"),
dataType : "text",
complete : function(response, status)
{
onBillDeleteComplete(response.responseText, status);
}
});
});




function onBillDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divBillGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}