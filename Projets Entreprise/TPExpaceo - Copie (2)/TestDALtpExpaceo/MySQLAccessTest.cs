using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DALtpExpaceo;
using System.Collections.Generic;
using MySql.Data.MySqlClient;

namespace TestDALtpExpaceo
{
    [TestClass]
    public class MySQLAccessTest
    {
        MySQLAccess access = new MySQLAccess("tpexpaceo", "localhost");


        [TestMethod]
        public void TestRemoveAllUsers()
        {
            access.RemoveData("user", null);
            MySqlDataReader retour = access.GetData(null, new string[] { "user" });
            Assert.IsFalse(retour.HasRows);
            retour.Close();

        }
        [TestMethod]
        public void TestAddUser()
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("name", "test");
            data.Add("password", "passTest");
            data.Add("mail", "mail@test.com");
            data.Add("user_right", "5");
            if (access.AddData("user", data) != 1)
            {
                Assert.IsTrue(false);
            }
            else
            {
                Assert.IsTrue(true);
            }
        }
        [TestMethod]
        public void TestGetIdViaName()
        {
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            clauseWhere.Add("name", "test");
            MySqlDataReader retour = access.GetData(new string[] { "id" }, new string[] { "user" }, clauseWhere);
            retour.Read();
            int idUserAdded = int.Parse(retour["id"].ToString());
            clauseWhere.Clear();
            retour.Close();
            clauseWhere.Add("id", idUserAdded.ToString());
            retour = access.GetData(new string[] { "name" },
                new string[] { "user" },
                clauseWhere);
            retour.Read();
            Assert.AreEqual("test", retour["name"]);
        }
       

        [TestMethod]
        public void TestUpdateUser()
        {
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            Dictionary<string, string> data = new Dictionary<string, string>();
            clauseWhere.Add("name", "test");
            data.Add("mail", "newMail@newTest.com");
            Assert.AreEqual(1, access.UpdateData("user",data,clauseWhere));
            MySqlDataReader retour = access.GetData(new string[] { "mail" }, new string[] { "user" }, clauseWhere);
            retour.Read();
            Assert.AreEqual("newMail@newTest.com", retour["mail"]);
            retour.Close();
        }


    }
}
