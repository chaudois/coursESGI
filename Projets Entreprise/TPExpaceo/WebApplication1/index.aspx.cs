using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using BLLTPExpaceo;
namespace WebApplication1
{
    public partial class index : System.Web.UI.Page
    {
        public int nbUser=0;
        DataAccess baseTest = new DataAccess();

        protected void Page_Load(object sender, EventArgs e)
        {
            Data.users = baseTest.GetUsersOrderById();
            Data.userRights = baseTest.GetUserRights();
            nbUser = Data.users.Count;

        }

        protected void Button1_Click(object sender, EventArgs e)
        {

        }
    }
}