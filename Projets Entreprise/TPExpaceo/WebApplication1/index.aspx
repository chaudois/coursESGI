<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="WebApplication1.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="style.css" />

    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <p>
            Annuaire des utilisateurs du site</p>
        <p>
            <Table style=" border-width:3px; 
 border-style:solid; 
 border-color:black;
 width:50%;">
                <%for (int i = 0; i < WebApplication1.Data.users.Count; i++)
                    { %>
                <tr style="border-width:1px;
 border-style:solid; 
 border-color:black;
 width:50%;">
                    <td  style="border-width:1px;
 border-style:solid; 
 border-color:black;
 width:50%;"><%=WebApplication1.Data.users[i].name.ToString() %></td>
                    <td style="border-width:1px;
 border-style:solid; 
 border-color:black;
 width:50%;"><%=WebApplication1.Data.users[i].mail.ToString() %></td>
                    <td style="border-width:1px;
 border-style:solid; 
 border-color:black;
 width:50%;"><%=WebApplication1.Data.users[i].user_right.description %></td>

                </tr>
                <%} %>
        </Table>
            &nbsp;</p>
        <p>
            

            &nbsp;
        </p>
    </form>
</body>
</html>
