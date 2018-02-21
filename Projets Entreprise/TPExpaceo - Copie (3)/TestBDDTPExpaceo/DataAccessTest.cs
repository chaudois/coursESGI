using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using BLLTPExpaceo;
using MySql.Data.MySqlClient;
using System.Collections.Generic;
using DALtpExpaceo;
using System.Data;

namespace TestBDDTPExpaceo
{
    [TestClass]
    public class DataAccessTest
    {
        static DataAccess bdd = new DataAccess();
        static int dt = DateTime.Now.Millisecond;

        [TestMethod]
        public void TestRemoveUser()
        {
            if (bdd.AddUser("userTest_" + dt.ToString(), "passTest", 2) != 1) Assert.IsTrue(false);
            if (bdd.getIdUser("userTest_" + dt.ToString()) == null) Assert.IsTrue(false);
            if (bdd.RemoveUser("userTest_" + dt.ToString()) != 1) Assert.IsTrue(false);
            if (bdd.getIdUser("userTest_" + dt.ToString()) != null) Assert.IsTrue(false);
            Assert.IsTrue(true);

        }

        [TestMethod]
        public void TestAddUser()
        {
            if (bdd.AddUser("userTest1_" + dt.ToString(), "passTest", 2) != 1)
            {
                Assert.IsTrue(false);
            }
            bdd.AddUser("userTest2_" + dt.ToString(), "passTest", 3);
            Assert.IsTrue(bdd.getIdUser("userTest1_" + dt.ToString()) != null);
        }


        [TestMethod]
        public void TestGetUsersOrderByName()
        {
            List<User> savedUsers = bdd.GetUsersOrderByName();
            if (savedUsers.Count < 1) Assert.IsTrue(false);
            List<string> usersName = new List<string>();
            foreach (User u in savedUsers)
            {
                usersName.Add(u.name);
            }
            usersName.Sort();
            for (int i = 0; i < usersName.Count; i++)
            {
                if (!usersName[i].Equals(savedUsers[i].name)) Assert.IsTrue(false);
            }


            Assert.IsTrue(true);
        }
        [TestMethod]
        public void TestGetUsersOrderByRight()
        {
            List<User> savedUsers = bdd.GetUsersOrderByRight();
            if (savedUsers.Count < 1) Assert.IsTrue(false);
            List<int> usersRights = new List<int>();
            foreach (User u in savedUsers)
            {
                usersRights.Add(u.user_right);
            }
            usersRights.Sort();
            for (int i = 0; i < usersRights.Count; i++)
            {
                if (usersRights[i] != savedUsers[i].user_right) Assert.IsTrue(false);
            }


            Assert.IsTrue(true);
        }
        [TestMethod]
        public void TestGetUsersOrderById()
        {
            List<User> savedUsers = bdd.GetUsersOrderById();
            if (savedUsers.Count < 1) Assert.IsTrue(false);
            List<int> usersId = new List<int>();
            foreach (User u in savedUsers)
            {
                usersId.Add(u.id);
            }
            usersId.Sort();
            for (int i = 0; i < usersId.Count; i++)
            {
                if (usersId[i] != savedUsers[i].id) Assert.IsTrue(false);
            }


            Assert.IsTrue(true);
        }
        [TestMethod]
        public void TestAddRight()
        {
            if (bdd.AddRight("dégénéré" + dt.ToString()) != 1)
            {
                Assert.IsTrue(false);
            }

            Dictionary<string, string> clauseWHere = new Dictionary<string, string>();
            clauseWHere.Add("description", "dégénéré" + dt.ToString());
            DataTable retour = bdd.access.GetData(new string[] { "description" }, new string[] { "user_right" }, clauseWHere);
            Assert.AreEqual("dégénéré" + dt.ToString(), retour.Rows[0]["description"]);
        }
        [TestMethod]
        public void TestModifyUserName()
        {
            int? idUser = bdd.getIdUser("userTest1_" + dt.ToString());
            if (idUser == null) Assert.IsTrue(false);
            if (bdd.ModifyUserName(idUser.Value, "newUserName" + dt.ToString()) != 1) Assert.IsTrue(false);
            if (bdd.getIdUser("newUserName" + dt.ToString()) == null) Assert.IsTrue(false);
            Assert.IsTrue(true);
        }
        [TestMethod]
        public void TestModifyUserPass()
        {
            int? idUser = bdd.getIdUser("userTest2_" + dt.ToString());
            if (idUser == null) Assert.IsTrue(false);
            if (bdd.ModifyUserPass(idUser.Value, "newUserPass" + dt.ToString()) != 1) Assert.IsTrue(false);
            List<User> modifiedUser = bdd.GetUsersOrderById();
            foreach (User u in modifiedUser)
            {
                if (u.name.Equals("userTest2_" + dt.ToString()))
                {
                    if (!u.password.Equals("newUserPass" + dt.ToString())) Assert.IsTrue(false);
                }
            }
            Assert.IsTrue(true);

        }
        [TestMethod]
        public void TestModifyUserMail()
        {
            int? idUser = bdd.getIdUser("userTest2_" + dt.ToString());
            if (idUser == null) Assert.IsTrue(false);
            if (bdd.ModifyUserMail(idUser.Value, "newUserMail" + dt.ToString()) != 1) Assert.IsTrue(false);
            List<User> modifiedUser = bdd.GetUsersOrderById();
            foreach (User u in modifiedUser)
            {
                if (u.name.Equals("userTest2_" + dt.ToString()))
                {
                    if (!u.mail.Equals("newUserMail" + dt.ToString())) Assert.IsTrue(false);
                }
            }
            Assert.IsTrue(true);

        }
        [TestMethod]
        public void TestModifyUserRight()
        {
            int? idUser = bdd.getIdUser("userTest2_" + dt.ToString());
            if (idUser == null) Assert.IsTrue(false);
            if (bdd.ModifyUserRight(idUser.Value,"banned") != 1) Assert.IsTrue(false);
            List<User> modifiedUser = bdd.GetUsersOrderById();
            foreach (User u in modifiedUser)
            {
                if (u.name.Equals("userTest2_" + dt.ToString()))
                {
                    if (u.user_right!=4) Assert.IsTrue(false);
                }
            }
            Assert.IsTrue(true);

        }
    }
}
