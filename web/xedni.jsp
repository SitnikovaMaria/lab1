<%@page import="model.Meeting"%>
<%@page import="java.util.List"%>
<%@page import="SSB.MeetingJpaController"%>
<html>
<head>
    <link href="https://bryntum.com/examples/extjs-6.0.0/build/classic/theme-classic/resources/theme-classic-all.css" rel="stylesheet" type="text/css"/>
    <link href="https://bryntum.com/examples/gantt-latest/resources/css/sch-gantt-all.css" rel="stylesheet" type="text/css" />

    <script src="ext-all-debug.js" type="text/javascript"></script>
    <script src="gnt-all-debug.js" type="text/javascript"></script>

    <script language="javascript" type="text/javascript">
	Ext.onReady(function() { generateGantt(); });

        function generateGantt()
        {
            var tasks = [];
            var dependencies = [];
            <%
            MeetingJpaController test = new MeetingJpaController();    
            List<Meeting> testMeetingList = test.getAll();
           
                            
            for (int i = 0; i < testMeetingList.size(); i++) {
            %>
		tasks.push({
                    StartDate: new Date(<%=(testMeetingList.get(i).getBegin().getYear()+1900)%>,<%=(testMeetingList.get(i).getBegin().getMonth())%>,<%=( testMeetingList.get(i).getBegin().getDate())%>),
                    EndDate: new Date(<%=(testMeetingList.get(i).getEnd().getYear()+1900)%>, <%=(testMeetingList.get(i).getEnd().getMonth())%>, <%=(testMeetingList.get(i).getEnd().getDate())%>),
                    Id: <%=i%>,
                    Name: "<%=(testMeetingList.get(i).getName())%>",
                    PercentDone: <%=i%>*10,
                    ManuallyScheduled : true,
                    ReadOnly: true,
                    children : [ 
                            <% for (int j = 0; j < testMeetingList.get(i).getUsers().size(); j++) {
%>           {   
            Id : <%=j+100%>,
            leaf : true,
            ManuallyScheduled : true,
            ReadOnly: true,
            Name : "<%=(testMeetingList.get(i).getUsers().get(j).getName())%>",
            PercentDone : 30,
            StartDate : new Date(<%=(testMeetingList.get(i).getBegin().getYear()+1900)%>,<%=(testMeetingList.get(i).getBegin().getMonth())%>,<%=( testMeetingList.get(i).getBegin().getDate())%>),
            Duration : 0},
    <%}%>
        ]
                      
                });
            <%}%>
		var taskStore = Ext.create('Gnt.data.TaskStore', {
                autoLoad: true,
                proxy: {
                    type: 'memory',
                    reader: {
                        type: 'json'
                    },

                    data: tasks // eof data
                }
                // eof proxy
            });


            var dependencyStore = Ext.create("Gnt.data.DependencyStore", {
                autoLoad: true,
                proxy: {
                    type : 'memory',
                    reader: {
                        type : 'json'
                    },
                    data: dependencies
                }
            });

        
            var ganttPanel = Ext.create('Gnt.panel.Gantt', {
                                height: 800,
                                width: 1500,
                                rowHeight                : 35,
				taskStore: taskStore,
				highlightWeekends : true,
				viewPreset: 'weekAndDayLetter',
				startDate: new Date(2017, 2, 1),
				endDate: new Date(2017, 11, 31),




				columns: [
					{
						xtype       : 'treecolumn',
						header      : 'Tasks',
						sortable    : false,
						dataIndex   : 'Name',
						width       : 200
					}
				],
			});
			

            
                        //ganttPanel.setDraggable(false);
			ganttPanel.render(Ext.getBody());
        }
    </script>
</head>

<body>

<div id="example-container">
</div>

</body>
</html>