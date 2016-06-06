<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="layout" content="orghomepage"/>
        <title>MFS</title>
    </head>
    <body>
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
                    <caption>Approved Loans (Contracts)</caption>
                    <tr>
                        <td colspan="6" style="padding: 0;">
                            <div class="search-pane">
                                <div class="search-row">
                                    <div class="search-cell">
                                        Name
                                    </div>
                                    <div class="search-cell">
                                        <input type="text" class="data-entry" name="searchName" value="${searchName}"/>
                                    </div>
                                    <div class="search-cell">
                                        IC Type
                                    </div>
                                    <div class="search-cell">
                                        <input type="text" class="data-entry" name="searchName" value="${searchICType}"/>
                                    </div>                                    
                                </div>
                                <div class="search-row">
                                    <div class="search-cell">
                                       Approved From
                                    </div>
                                    <div class="search-cell">
                                        <g:datePicker name="searchApprovedFrom" default="none" noSelection="${['':'']}" precision="day"/>
                                    </div>
                                    <div class="search-cell">
                                        IC Number
                                    </div>
                                    <div class="search-cell">
                                        <input type="text" class="data-entry" name="searchName" value="${searchICNumber}"/>
                                    </div>                                     
                                </div>
                                <div class="search-row">
                                    <div class="search-cell">
                                       Approved Till
                                    </div>
                                    <div class="search-cell">
                                        <g:datePicker name="searchApprovedTill" default="none" noSelection="${['':'']}" precision="day"/>
                                    </div>
                                    <div class="search-cell">
                                        State
                                    </div>
                                    <div class="search-cell">
                                        <input type="text" class="data-entry" name="searchState" value="${searchState}"/>
                                    </div>                                     
                                </div>                                
                            </div>
                            
                        </td>
                    </tr>
                    <th width="2%">No</th>
                    <th width="25%">Name</th>
                    <th width="10%">IC Number</th>
                    <th width="10%">Account No</th>
                    <th width="10%">Amount</th>
                    <th>Tenure</th>
                    <g:if test="${contractsList?.size() > 0}">
                        
                    </g:if>
                    <g:else>
                        <tr>
                            <td class="empty-list" colspan="6">No Records are found</td>
                        </tr>
                    </g:else>
                </table>
            </div>
        </div>
    </body>
</html>
