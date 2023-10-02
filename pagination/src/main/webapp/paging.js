function callApi(METHOD, URL, DATA, SUCCESS)
{
  var xhttp = new XMLHttpRequest();
  xhttp.open(METHOD, URL, true);
  xhttp.setRequestHeader('Content-Type','application/json');
    
  xhttp.onreadystatechange = function()
  {
    if(this.readyState == 4)
    {
      if(this.status == 200)
        SUCCESS(this.responseText);
      else
        alert("404: Service unavailable");
    }
  };
  
  xhttp.send(DATA);
}

function getData()
{
	var psize = parseInt((content.offsetHeight - 32) / 32);
	
	var url = "http://localhost:8080/student/data/1/" + psize;
	callApi("GET", url, "", generateTable);
	
	var url = "http://localhost:8080/student/paging/1/" + psize;
	callApi("GET", url, "", loadPaging);
	
}

function generateTable(res)
{
	var data = JSON.parse(res);
	var table = `<table class="tbl">
				   <thead><tr>
				   		<th style="width:100px">ID</th>
				   		<th style="width:250px">NAME</th>
				   		<th style="width:100px">DEPT</th>
				   		<th></th>
				   </tr></thead>`;
	for(var x in data)
	{
		table += `<tr>
					<td style="text-align:center">`+ data[x].id +`</td>
					<td>`+ data[x].name +`</td>
					<td style="text-align:center">`+ data[x].dept +`</td>
					<td></td>
				  </tr>`;
	}
	table += `</table>`;
	viewdata.innerHTML = table;
}

function loadPaging(res)
{
	var data = JSON.parse(res);
	var pages = "";
	for(var x in data)
	{
		pages += `<label class="${data[x].selected? "page-selected" : "page-unselected"}" onclick="loadPage('${data[x].pgno}','${data[x].selected}')">`+ data[x].pgno +`</label>`;
	}
	viewpages.innerHTML = pages;
}

function loadPage(cpage, selected)
{
	var psize = parseInt((content.offsetHeight - 32) / 32);
	
	var url = "http://localhost:8080/student/data/"+ cpage +"/" + psize;
	callApi("GET", url, "", generateTable);
	
	var url = "http://localhost:8080/student/paging/"+ cpage +"/" + psize;
	callApi("GET", url, "", loadPaging);
}