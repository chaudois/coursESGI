<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="WebApplication1.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <p>
            Liste des utilisateurs
        </p>
        <p>
            <asp:Button ID="Button1" runat="server" Text="Ajouter un utilisateur" />
        </p>
        <p>
            <%
                for(int a = 0; a < i; a++)
                {%>
                    hello
                <%}
                %>

            &nbsp;
        </p>
    </form>
</body>
</html>
