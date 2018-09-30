window.onload = function() {
	var config = {
		id: "tg1",
		/*width: "2500",*/
		renderTo: "div12",
		headerAlign: "left",
		headerHeight: "40",
		dataAlign: "left",
		indentation: "20",
        folderOpenIcon: "../../images/folderOpen.png",
        folderCloseIcon: "../../images/folderClose.png",
        defaultLeafIcon: "../../images/defaultLeaf.gif",
		hoverRowBackground: "false",
		folderColumnIndex: "1",
		itemClick: "itemClickEvent",
		columns: [{
			headerText: "",
			headerAlign: "center",
			dataAlign: "center",
			width: "10"
		},
		{
			headerText: "部门编码",
			dataField: "name",
			headerAlign: "center",
			handler: "customOrgName",
            width: "100"
		},
		{
			headerText: "部门名称",
			dataField: "assignee",
			headerAlign: "center",
			dataAlign: "center",
			width: "100"
		},
		{
			headerText: "部门负责人",
			headerAlign: "center",
			dataAlign: "center",
            width: "100"
		}
            ,
            {
                headerText: "电话",
                headerAlign: "center",
                dataAlign: "center",
                width: "100"
                /*	handler: "customLook"*/
            } ,
            {
                headerText: "传真",
                headerAlign: "center",
                dataAlign: "center",
                width: "100"
                /*	handler: "customLook"*/
            },
            {
                headerText: "email",
                headerAlign: "center",
                dataAlign: "center",
                width: "100"
                /*	handler: "customLook"*/
            },
            {
                headerText: "邮编",
                headerAlign: "center",
                dataAlign: "center",
                width: "100",

                	handler: "customLook"
            }],
		data: [
		{
			name: "总校区",
			code: "CQ",
			assignee: "",
			children: [{
				name: "财务部"
			},

			{
				name: "半环服务厅"
			},
				{
					name: "分校",
					children: [{
						name: "财务部"
					},
						{
							name: "财务部"
						},
						{
							name: "教务部",
							children: [{
								name: "教务1"
							},
								{
									name: "教务2"
								},
								{
									name: "教务3"
								},
								{
									name: "教务4"
								}]
						}]
				}
	]}/*,
		{
			name: "清新分公司",
			code: "QX",
			assignee: "",
			children: [
				/!*自己内容在这里加进去   同上*!/
			]
		},
		{
			name: "英德分公司",
			code: "YD",
			assignee: "",
			children: [
				/!*自己内容在这里加进去   同上*!/
			]
		},
		{
			name: "佛冈分公司",
			code: "FG",
			assignee: "",
			children: [
				/!*自己内容在这里加进去   同上*!/
			]
		}*/]
	};
	/*debugger;*/
	/*var treeGrid = new TreeGrid(config);
	treeGrid.show()*/
}
