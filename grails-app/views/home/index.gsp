<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="orghomepage"/>
        <title>MFS</title>
    </head>
    <body>
        <style>
            #searchApprovedFrom_day {width: 50px;}
            #searchApprovedFrom_month {width: 110px;}
            #searchApprovedFrom_year {width: 82px;}
            #searchApprovedTill_day {width: 50px;}
            #searchApprovedTill_month {width: 110px;}
            #searchApprovedTill_year {width: 82px;}            
        </style>        
        <div class="content-holder">
            <!-- Left Pane -->
            <div class="col-2">
                <g:render template="/common/accordion" model ="[menuList: menuList]"/>
                <div class="summary">
                    <div class="summary-row">
                        <div class="summary-cell highlighted">
                            Total Members
                        </div>
                        <div class="summary-cell righted highlighted">
                            125
                        </div>                    
                    </div>
                    <div class="summary-row">
                        <div class="summary-cell padded">
                            <i>Pending Approval</i>
                        </div>
                        <div class="summary-cell righted">
                            0
                        </div>                    
                    </div>
                    <div class="summary-row">
                        <div class="summary-cell padded">
                            Approved
                        </div>
                        <div class="summary-cell righted">
                            125
                        </div>                    
                    </div>
                    <div class="summary-row">
                        <div class="summary-cell padded">
                            Rejected
                        </div>
                        <div class="summary-cell righted">
                            0
                        </div>                    
                    </div>                    
                    <div class="summary-row">
                        <div class="summary-cell highlighted">
                            Total Facilities
                        </div>
                        <div class="summary-cell righted highlighted">
                            125
                        </div>                    
                    </div>                
                </div>
            </div>
            
            <!-- Right Pane (Content) -->
            <div class="col-10">
                <table class="main-content separated">
                    <caption>Approved Members</caption>
                    <tr>
                        <td colspan="5" style="padding: 0;">
                            <g:render template="/common/searchForm" model="[searchForm: searchForm, searchData: searchData]"/>
                        </td>
                    </tr>
                    <th width="2%">No</th>
                    <th width="25%">Name</th>
                    <th width="10%">IC Number</th>
                    <th width="10%">Birth Date</th>
                    <th width="10%">State</th>
                    <g:if test="${instancesList?.size() > 0}">
                        <g:each in="${instancesList}" var="instance" status="index">
                            <tr>
                                <td>${index + 1}</td>
                                <td>${instance?.name}</td>
                                <td>${instance?.icNumber}</td>
                                <td><g:formatDate format="dd/MM/yyyy" date="${instance?.birthDate}"/></td>
                                <td>${instance?.state}</td>
                            </tr>
                        </g:each>
                    </g:if>
                    <g:else>
                        <tr>
                            <td class="empty-list" colspan="5">No Records are found</td>
                        </tr>
                    </g:else>
                </table>
            </div>
        </div>

    </body>
</html>
