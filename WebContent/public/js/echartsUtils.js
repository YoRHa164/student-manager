/**
 * 
 */
	//获取图表数据，渲染
    function getDataRender($){
    	//获取python数据,并渲染
    	var pythonCount = getSubjectDataRender($,"python");
    	//获取java数据,并渲染
    	var javaCount = getSubjectDataRender($,"java");
    	//获取linux数据,并渲染
    	var linuxCount = getSubjectDataRender($,"linux");
    	//获取sql数据,并渲染
    	var sqlCount = getSubjectDataRender($,"sql");
    	//渲染总图表
    	
    }
    //渲染单个学科图表
    function getSubjectDataRender($,subject){
    	var retData;
    	var param = "subjectName="+subject;
    	$.ajax({
    		url:"../api/student/scoreCount/default.do"
    		,data: param
    		,async: false
    		,success: function(data){
    			retData = fetchXYData(data.data);
    			subjectRender(retData,subject,$);
    		}
    	})
    	return retData;
    }
  	//从ajax返回的数据中提取x和y轴数据
    function fetchXYData(data){
    	//返回的数据
    	var retData = {};
    	var Xdata = [],Ydata = [];
    	data.forEach(function(item,index){
    		Xdata.push(item.name);
    		Ydata.push(item.value);
		})
		retData.XData = Xdata;  retData.YData = Ydata;
		return retData;
    }
  	//用数据渲染指定的科目图表
    function subjectRender(data,subject,$){
    	var chart = echarts.init(document.getElementById(subject));
    	chartRander(chart,data,subject + "成绩统计");
    }
    //渲染所有学科的总图表
    function allSubjectRender(python,java,linux,sql){
    	alert("allSubjectRender");
    }
    //渲染图表x和y轴数据
    function chartRander(chart,Data,titleText,name){
    	chart.setOption({
			title:{
				text: titleText
			},
		    xAxis: {
		        type: 'category',
		        data: Data.XData
		    },
		    yAxis: {
		        type: 'value'
		    },
		    tooltip: {},
		    legend: {},
		    series: [{
		        data: Data.YData,
		        type: 'line',
		        name: 'name'
		    }]
        });
    }