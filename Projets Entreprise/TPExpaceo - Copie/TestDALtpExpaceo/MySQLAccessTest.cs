using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using DALtpExpaceo;
using System.Collections.Generic;

namespace TestDALtpExpaceo
{
    [TestClass]
    public class MySQLAccessTest
    {
        #region testGetUniqueUser
        [TestMethod]
        public void TestGetNameViaId()
        {
            MySQLAccess access = new MySQLAccess("tpexpaceo");
            User user = access.getUniqueUser(1);
            Assert.AreEqual("Damien", user.name);
        }
        [TestMethod]
        public void TestGetNameViaMail()
        {
            MySQLAccess access = new MySQLAccess("tpexpaceo");
            User user = access.getUniqueUser(null,null,null,"damien.chaudois@gmail.com");
            Assert.AreEqual("Damien", user.name);
        }
        [TestMethod]
        public void TestGetNameViaPwd()
        {
            MySQLAccess access = new MySQLAccess("tpexpaceo");
            User user = access.getUniqueUser(null, null, "root");
            Assert.AreEqual("Damien", user.name);
        }
        [TestMethod]
        public void TestGetIdViaName()
        {
            MySQLAccess access = new MySQLAccess("tpexpaceo");
            User user = access.getUniqueUser(null, "chloe");
            Assert.AreEqual(7, user.id);
        }
        #endregion

        #region testGetUserArray
        [TestMethod]
        public void TestGetAllVisitor()
        {
            MySQLAccess access = new MySQLAccess("tpexpaceo");
            List<User> users = access.GetUserList(null,null,null,null,3);
            foreach(User u in users)
            {
                Assert.AreEqual(3, u.user_right);
            }
        }
        [TestMethod]
        public void TestGetAllUnregisteredMailUserViaPassword()
        {
            MySQLAccess access = new MySQLAccess("tpexpaceo");
            List<User> users = access.GetUserList(null, null, null, "");
            foreach(User u in users)
            {
                if(u.password== "passPierre"
                    ||u.password== "passPascal"
                    ||u.password== "passJean"
                    ||u.password== "passOlga")
                {
                    Assert.IsTrue(true);
                }
            }


        }
        #endregion
    }
}
