using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using BLLTPExpaceo;
using MySql.Data.MySqlClient;
using System.Collections.Generic;

namespace TestBDDTPExpaceo
{
   [TestClass]
    public class UnitTest1
    {
        BDD bdd = new BDD();
        static int dt = DateTime.Now.Millisecond;
        [TestMethod]
        public void TestAddUser()
        {            
            if(bdd.AddUser("userTest" + dt.ToString(), "passTest", 2) != 1)
            {
                Assert.IsTrue(false);
            }
            Assert.IsTrue(bdd.getIdUser("userTest" + dt.ToString()) != null) ;
        }
        [TestMethod]
        public void TestAddRight()
        {
            if (bdd.AddRight("dégénéré"+ dt.ToString()) != 1)
            {
                Assert.IsTrue(false);
            }

            Dictionary<string, string> clauseWHere = new Dictionary<string, string>();
            clauseWHere.Add("description", "dégénéré" + dt.ToString());
            MySqlDataReader retour= bdd.access.GetData(new string[] { "description" }, new string[] { "user_right" }, clauseWHere);
            retour.Read();
            Assert.AreEqual("dégénéré" + dt.ToString(), retour["description"]);
        }
        [TestMethod]
        public void TestRemoveUser()
        {
            if(bdd.RemoveUser("userTest" + dt.ToString())!=1) Assert.IsTrue(false);

            
            if (bdd.getIdUser("userTest" + dt.ToString()) != null) Assert.IsTrue(false);
            Assert.IsTrue(true);
        }
    }
}
