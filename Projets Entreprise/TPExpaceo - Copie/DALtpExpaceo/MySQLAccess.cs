using System;
using MySql.Data.MySqlClient;
using System.Collections.Generic;

namespace DALtpExpaceo
{
    public class MySQLAccess
    {
        string BDD;
        MySqlConnection connection;
        public MySQLAccess(String BDD)
        {
            this.BDD = BDD;
            connect();
        }

        private void connect()
        {
            string connData = "Server=localhost;Database=" + BDD + "; Uid=root;";
            connection = new MySqlConnection(connData);
            connection.Open();
        }
        public User getUniqueUser(int? id=null,
            string name = null,
            string password = null,
            string mail = null,
            int? user_right = null) 
        {
            #region creationRequete
            MySqlCommand query = connection.CreateCommand();
            string request = "SELECT id,name,password,mail,user_right FROM user ";
            if (id != null
                || name != null
                || password != null
                || mail != null
                || user_right != null) request += " WHERE ";
            if(id!=null)request+="id=" + id.ToString()+",";
            if (name != null) request += "name='" + name + "',";
            if (password != null) request += "password='" + password + "',";
            if (mail != null) request += "mail='" + mail + "',";
            if (user_right != null) request += "user_right=" + user_right + ",";
            request=request.Substring(0, request.Length - 1);
            request +=  ";";
            query.CommandText = request;
            query.ExecuteNonQuery();
            MySqlDataReader result = query.ExecuteReader();
#endregion

            result.Read();
            return new User(
                (int)result["id"],
                result["name"].ToString(),
                result["password"].ToString(),
                result["mail"].ToString(),
                (int)result["user_right"]);

        }
        public List<User> GetUserList(int? id = null,
            string name = null,
            string password = null,
            string mail = null,
            int? user_right = null)
        {
            List<User> retour=new List<User>();
            #region creationRequete
            MySqlCommand query = connection.CreateCommand();
            string request = "SELECT id,name,password,mail,user_right FROM user ";
            if (id != null
                || name != null
                || password != null
                || mail != null
                || user_right != null) request += " WHERE ";
            if (id != null) request += "id=" + id.ToString() + ",";
            if (name != null) request += "name='" + name + "',";
            if (password != null) request += "password='" + password + "',";
            if (mail != null) request += "mail='" + mail + "',";
            if (user_right != null) request += "user_right=" + user_right + ",";
            request = request.Substring(0, request.Length - 1);
            request += ";";
            query.CommandText = request;
            #endregion

            query.ExecuteNonQuery();
            MySqlDataReader result = query.ExecuteReader();
            while (result.Read())
            {
                retour.Add(new User((int)result["id"],
                    result["name"].ToString(),
                    result["password"].ToString(),
                    result["mail"].ToString(),
                    (int)result["user_right"]));

            }
            return retour;
        }

    }
}
