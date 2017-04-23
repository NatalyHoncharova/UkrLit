<%@page  import="java.util.ArrayList"%>
<%@page  import="java.util.List"%>
<%@page  import="ua.kiev.prog.model.Town"%>
<%@page  import="ua.kiev.prog.model.TownServices"%>
<%@page  contentType="text/html"  pageEncoding="UTF-8"%>
<%@  taglib  prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE  html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP  Page</title>
    <script  type="text/javascript">

        function  ConfirmOnDelete(item) {
            if (confirm("Are you sure to delete " + item + "?") === true)
                return true;
            else
                return false;
        }
</script>
</head>
<body>
<h1>Town Grid</h1>

<fieldset>
        <legend>Town Managment</legend>
        ${errorMessage}
        ${successMessage}
    <div>
        <form action = "<c:url value="/townGrid" />" method="post">
            <%
                int limitStart = 0;
                int limitMax = 15;
                int pageSize = 15;
                int allTownCount = 0;
                int pageIndex = 0;  //start  page  from  index  0
                session = request.getSession(false);
                //check cookie
                Cookie[] collectionCookies = request.getCookies();
                String cookieValue = "";
                for (Cookie c : collectionCookies) {
                    if  (c.getName().equalsIgnoreCase("FirstTimeAccessTownManager")) {
                        if (c.getValue().equalsIgnoreCase("YES")){
                            c.setValue("NO");
                            cookieValue = "NO";
                        } else if (c.getValue().equalsIgnoreCase("")) {
                            c.setValue("YES");
                            session.removeAttribute("limitStart");
                            session.removeAttribute("limitMax");
                            session.removeAttribute("pageIndex");
                            session.removeAttribute("pageSize");
                        }
                    }
                }
                if (cookieValue.equalsIgnoreCase("")) {
                    Cookie  cookie = new Cookie("FirstTimeAccessTownManager", "YES");
                    response.addCookie(cookie);
                }

                if (session.getAttribute("limitStart") != null) {
                    limitStart = Integer.parseInt(session.getAttribute("limitStart").toString());
                } else {
                    session.setAttribute("limitStart", limitStart);
                }

                if (session.getAttribute("limitMax") != null) {
                    limitMax = Integer.parseInt(session.getAttribute("limitMax").toString());
                } else {
                    session.setAttribute("limitMax", limitMax);
                }

                if  (session.getAttribute("pageIndex") != null)  {
                    pageIndex = Integer.parseInt(session.getAttribute("pageIndex").toString());
                } else {
                    session.setAttribute("pageIndex", pageIndex);
                }

                if  (session.getAttribute("pageSize") != null)  {
                    pageSize = Integer.parseInt(session.getAttribute("pageSize").toString());
                }  else  {
                    session.setAttribute("pageSize", pageSize);
                }
                limitMax = pageSize;

                TownServices townsServ = new TownServices();
                List<Town> _collectionOfTowns = new ArrayList<Town>();
                allTownCount = townsServ.countTableDataRow("towns");
                _collectionOfTowns = townsServ.getAllTowns(limitStart,  limitMax);

                String  tableTown = "<table class=\"mainTable\"  cellspacing=\"0\"  rules=\"all\"  id=\"MainContent_GridView1\"  style=\"border-color:Gray;border-width:1px;border-style:Solid;width:95%;border-collapse:collapse;\">";
                tableTown += "<tr  style=\"color:White;background-color:#6699CC;font-weight:bold;  padding:4px;\">";
                tableTown += "<th  scope=\"col\">No.</th>";
                tableTown += "<th  scope=\"col\">Town ID</th>";
                tableTown += "<th  scope=\"col\">Town name</th>";
                tableTown += "<th  scope=\"col\">Creation date</th>";
                tableTown += "<th  scope=\"col\">Creation user ID</th>";
                tableTown += "<th  scope=\"col\">Updating date</th>";
                tableTown += "<th  scope=\"col\">Updating user ID</th>";
                tableTown += "<th  scope=\"col\">&nbsp;</th>";
                tableTown += "<th  scope=\"col\">&nbsp;</th></tr>";

                int numberRecord = pageIndex * pageSize;
                int balance = allTownCount - numberRecord;
                int startRekodToShow = numberRecord + 1;
                int index = startRekodToShow;
                for (Town  u  :  _collectionOfTowns)  {
                    tableTown  +=  "<tr  style=\"border-color:Gray;border-width:1px;border-style:Solid;\">";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:20px;padding:4px;\">";
                    tableTown  +=  Integer.toString(index);
                    tableTown  +=  "</td>";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:100px;padding:4px;\">";
                    tableTown  +=  u.getID();
                    tableTown  +=  "</td>";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:80px;padding:4px;\">";
                    tableTown  +=  u.getTownName();
                    tableTown  +=  "</td>";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:80px;padding:4px;\">";
                    tableTown  +=  u.getCreationDate();
                    tableTown  +=  "</td>";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:80px;padding:4px;\">";
                    tableTown  +=  u.getCreationUser_ID();
                    tableTown  +=  "</td>";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:80px;padding:4px;\">";
                    tableTown  +=  u.getUpdatingDate();
                    tableTown  +=  "</td>";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:80px;padding:4px;\">";
                    tableTown  +=  u.getUpdatingUser_ID();
                    tableTown  +=  "</td>";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:50px;padding:4px;\">";
                    tableTown  +=  "<input  type=\"submit\"  class=\"buttonLikeLink\"  name=\""  +  u.getID()  +  "\"  onclick=\"return  ConfirmOnDelete('"  +  u.getID()  +  "');\"  value=\"Remove\"  />";
                    tableTown  +=  "</td>";
                    tableTown  +=  "<td  style=\"border-color:#CCCCCC;border-width:1px;border-style:Solid;width:50px;padding:4px;\">";
                    tableTown  +=  "<input  type=\"submit\"  class=\"buttonLikeLink\"  name=\""  +  u.getID()  +  "\"  value=\"Modify\"  ></input>";
                    tableTown  +=  "</td>";
                    tableTown  +=  "</tr>";
                    index++;
                }
                tableTown  +=  "</table>";
                System.out.print(tableTown);
            %>
        </form>

        <!-- Create logic next and previous in this section -->
        <%
            if (allTownCount > pageSize) {
                String form = " ";
                if (pageIndex > 0) {
                    form += "<h1>Page " + Integer.toString(pageIndex) + "</h1> ";
                }
                form += "<form action=\"" + request.getContextPath() + "/navigateTownGrid\" method=\"POST\" >";
                if (limitStart > 0) {
                    if (balance > pageSize) {
                        if ((pageSize + numberRecord) == allTownCount) {
                            //do not show next
                        } else {
                            //show next
                            form += "<input type=\"submit\" class=\"buttonNav\" id=\"bNext\" name=\"action\" value=\"Next\" />";
                        }
                    } else {
                        if ((balance + numberRecord) == allTownCount) {
                            //do not show next
                        } else {
                            //show next
                            form += "<input type=\"submit\" class=\"buttonNav\" id=\"bNext\" name=\"action\" value=\"Next\" />";
                        }
                    }
                    if (startRekodToShow != 1) {
                        form += "<input type=\"submit\" class=\"buttonNav\" id=\"bPrevious\" name=\"action\" value=\"Previous\" />";
                    }
                } else {
                    form += "<input type=\"submit\" class=\"buttonNav\" id=\"bNext\" name=\"action\" value=\"Next\" />";
                }
                form += "</form>";
                System.out.print(form);
            }
        %>
    </div>

    <%
        if (allTownCount > 15) {
            String grid_row_controller = "<div id=\"MainContent_PanelDropDownGVPage\" style=\"display:inline;\">";
            grid_row_controller += "Total Towns Per Page: ";
            grid_row_controller += "<form method=\"POST\" action=\"" + request.getContextPath() + "/navigateTownGrid\" id=\"pageRowform\" >";
            grid_row_controller += "<select name=\"action\" style=\"width:50px;\" onchange=\"document.forms['pageRowform'].submit()\">";
            if (pageSize == 10) {
                grid_row_controller += "<option value=\"10\" selected=\"selected\">10</option>";
            } else {
                grid_row_controller += "<option value=\"10\">10</option>";
            }
            if (pageSize == 15) {
                grid_row_controller += "<option selected=\"selected\" value=\"15\">15</option>";
            } else {
                grid_row_controller += "<option value=\"15\">15</option>";
            }
            if (pageSize == 25) {
                grid_row_controller += "<option selected=\"selected\" value=\"25\">25</option>";
            } else {
                grid_row_controller += "<option value=\"25\">25</option>";
            }
            if (pageSize == 35) {
                grid_row_controller += "<option selected=\"selected\" value=\"35\">35</option>";
            } else {
                grid_row_controller += "<option value=\"35\">35</option>";
            }
            if (pageSize == 50) {
                grid_row_controller += "<option selected=\"selected\" value=\"50\">50</option>";
            } else {
                grid_row_controller += "<option value=\"50\">50</option>";
            }
            grid_row_controller += "</select>";
            grid_row_controller += "</form>";
            System.out.print(grid_row_controller);
        }
    %>

            Show        
    <%
        if (allTownCount > pageSize) {
            if (pageIndex == 0) {
                System.out.print("1 - " + pageSize);
            } else {
                if (balance > pageSize) {
                    System.out.print(startRekodToShow + " - " + (pageSize + numberRecord));
                } else {
                    System.out.print(startRekodToShow + " - " + (balance + numberRecord));
                }
            }
        } else if (allTownCount == 0) {
            System.out.print("0");
        } else {
            System.out.print("1 - " + allTownCount);
        }
    %>
    Record(s)  From
    <%
        System.out.print(allTownCount);
    %>
    User(s)
    </div>
</fieldset>