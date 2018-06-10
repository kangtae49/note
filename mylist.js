
var MyList = function (target, options){
	
	this.settings = {};
	
	var default_settings = {
		skip: 1,
		col_fun: {}, 
		use_tr_clone: true,
		data: [],
		id: '',
		event: {},
	};
	
	var settings = $.extend({}, default_settings, options);
	var table = $(target);
	var columns = [];
	var coltypes = {}
	
	this.settings = settings;
	settings.table = table;
	settings.columns = columns;
	settings.coltypes = coltypes;
	
	table.find('th').each(function(i, th){
		var name = $(th).attr('name');
		
		if(!name){
			name = '' + i;
		}
		col_info = name.split("|")
		if(col_info.length > 1){
			colname = col_info[0];
			coltype = col_info[1];
		}else{
			colname = col_info[0];
			coltype = '';
		}
		columns.push(colname);
		coltypes[colname] = coltype;
	})

	this.load(settings.data);

}

MyList.prototype.load = function (data) {
	
	var settings = this.settings;
	settings.data = data;
	var table = settings.table;
	
	if(settings.use_tr_clone){
		table.find('tr:gt(' + (settings.skip-1) + ')').hide();
	}else{
		table.find('tr:gt(' + (settings.skip-1) + ')').remove();
	}
	
	var tbody = table.find('tbody');
	$.each(settings.data, function(row_i, row){
		var tr;
		tr = table.find('tr:eq(' + settings.skip + ')').clone(true, true);
		tr.show();
		var id = row[settings.id];
		tr.attr('id', id);
		
		td_dict = {}
		$.each(settings.columns, function(col_i, colname){
			coltype= settings.coltypes[colname];
			col_i = settings.columns.indexOf(colname);
			td = tr.find('td:eq(' + col_i + ')');
			
			td_dict[colname] = td;
		});
		
		$.each(settings.columns, function(col_i, colname){
			td = tr.find('td:eq(' + col_i + ')');
			coltype = settings.coltypes[colname];
			if(coltype){
				tag_val = td.find(coltype);
				if(coltype.indexOf('checkbox') >= 0){
					tag_val.attr('checked', row[colname]);
				}else if(coltype.indexOf('radio') >= 0){
					tag_val.each(function(i){
						var nm = $(this).attr('name');
						$(this).attr('name', nm + '_' + id + '_' + colname);
						if ($(this).val() == row[colname]){
							$(this).attr('checked', true);
						}else{
							$(this).attr('checked', false);
						}
					});
				}else if(coltype == 'textarea'){
					tag_val.val(row[colname]);
				}else{
					tag_val.html(row[colname]);
				}
			}else{
				td.html(row[colname]);
			}
			
			if(settings.col_fun.hasOwnProperty(colname)){
				settings.col_fun[colname](id, colname, row, tr, td);
			}
			if(settings.event){
				td.click({id:id, colname:colname, row:row, tr:tr, td:td}, function (event){
					var id = event.data.id;
					var colname = event.data.colname;
					var row = event.data.row;
					var td = event.data.td;
					settings.event['click'](id, colname, row, tr, td);
				});
			}
		});
		
		tbody.append(tr);
	});
	
	
}

MyList.prototype.refresh = function () {
	this.load(this.settings.data);
}

MyList.prototype.append = function (row) {
	this.settings.data.push(row);
	this.refresh();
}

MyList.prototype.remove = function (id) {
	var id_nm = this.settings.id;
	var settings = this.settings;
	var remove_i = -1;
	$.each(settings.data, function(i, row){
		if (row[id_nm] == id){
			remove_i = i;
			return;
		}
	});
	
	settings.data.splice(remove_i, 1);
	this.refresh();
}

