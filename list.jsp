<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resources/js/jquery-1.8.0.min.js" ></script>
<script type="text/javascript" src="/resources/js/mylist.js" ></script>

<title>Insert title here</title>

<script>

$(document).ready(function(){

	data = [
		{
			'id': 1,
			'star': true,
			'nm': 'nm1',
			'val': 'val1',
			'chk': 'aa',
		},
		{
			'id': 2,
			'star': false,
			'nm': 'nm2',
			'val': 'val2',
			'chk': 'bb',
		},
	]
	
	var list = new MyList('#tbl', {
		id:'id', 
		data:data,
		col_fun: {
			'col5': function(id, colname, row, tr, td){
				td.html("hello");
			}
		},
		event: {
			click: function (id, colname, row, tr, td){
				alert(id + ',' + colname);
				alert(tr.html());
				alert(td.html());
			}
		}

	});
	
	list.append({
		'id': 3,
		'star': false,
		'nm': 'nm3',
		'val': 'val3',
		'chk': 'aa',
	});

	list.remove(2);	
	
});

</script>
</head>
<body>
<table id="tbl">
	<tr>
		<th name="id">ID</th>
		<th name="star|input[type='checkbox']"></th>
		<th name="nm">NAME</th>
		<th name="val">VAL</th>
		<th name="chk|input[type='radio']">CHK</th>
		<th name="col5">col5</th>
	</tr>
	<tr>
		<td>col1</td>
		<td><input type="checkbox" /></td>
		<td><span><a>col3</a></span></td>
		<td><span><a>col4</a></span></td>
		<td><input type='radio' name='chk' value="aa" /><input type='radio' name='chk' value="bb" /></td>
		<td></td>
	</tr>
</table>
</body>
</html>
