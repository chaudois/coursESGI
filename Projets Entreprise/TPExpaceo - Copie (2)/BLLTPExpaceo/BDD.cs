using System;
using DALtpExpaceo;
using System.Collections.Generic;
using MySql.Data.MySqlClient;

namespace BLLTPExpaceo
{
    public class BDD
    {
        public MySQLAccess access;

        /// <summary>
        /// se connecte à la base tpexpaceo en local à la construction
        /// </summary>
        public BDD()
        {
            access = new MySQLAccess("tpexpaceo", "localhost");
        }
        /// <summary>
        /// ajoute un utilisateur à la base tpexpaceo
        /// </summary>
        /// <param name="name"></param>
        /// <param name="password"></param>
        /// <param name="userRight"></param>
        /// <param name="mail"></param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery() (1=OK) </returns>
        public int AddUser(string name, string password, int userRight, string mail = null)
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("name", name);
            data.Add("password", password);
            data.Add("user_right", userRight.ToString());
            if (mail != null) data.Add("mail", mail);

            return access.AddData("user", data);
        }
        /// <summary>
        /// ajoute un nouveau droit dans la table user_right
        /// </summary>
        /// <param name="description">nom du droit à ajouter</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int AddRight(string description)
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("description", description);

            return access.AddData("user_right", data);

        }
        /// <summary>
        /// supprime un utilisateur de la table user
        /// </summary>
        /// <param name="userId">id de l'utilisateur à supprimer</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int RemoveUser(int userId)
        {
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            clauseWhere.Add("id", userId.ToString());
            return access.RemoveData("user", clauseWhere);

        }
        /// <summary>
        /// supprime un utilisateur de la table user
        /// </summary>
        /// <param name="userName">nom de l'utilisateur à supprimer</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int RemoveUser(string userName)
        {
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            clauseWhere.Add("name", userName);
            return access.RemoveData("user", clauseWhere);

        }
        /// <summary>
        /// renvoie la liste de tout les utilisateur present dans la table user par ordre alphabetique de leur nom
        /// </summary>
        /// <returns></returns>
        public List<User> GetUsersOrderByName()
        {
            string[] from = { "user" };
            string[] select = { "id", "name", "password", "mail", "user_right" };
            Dictionary<string, string> where = null;
            string[] order_by = { "name" };

            List<User> retour = new List<User>();

            MySqlDataReader result = access.GetData(select, from, where, order_by);

            while (result.Read())
            {
                retour.Add(new User(int.Parse(result["id"].ToString()),
                    result["name"].ToString(),
                    result["password"].ToString(),
                    result["mail"].ToString(),
                    int.Parse(result["user_right"].ToString()))
                    );
            }

            return retour;
        }
        /// <summary>
        /// renvoie la liste de tout les utilisateur present dans la table user par ordre de création
        /// </summary>
        /// <returns></returns>
        public List<User> GetUsersOrderByRight()
        {
            string[] from = { "user" };
            string[] select = { "id", "name", "password", "mail", "user_right" };
            Dictionary<string, string> where = null;
            string[] order_by = { "user_right" };

            List<User> retour = new List<User>();
            MySqlDataReader result = access.GetData(select, from, where, order_by);

            while (result.Read())
            {
                retour.Add(new User(int.Parse(result["id"].ToString()),
                    result["name"].ToString(),
                    result["password"].ToString(),
                    result["mail"].ToString(),
                    int.Parse(result["user_right"].ToString()))
                    );
            }

            return retour;
        }
        /// <summary>
        /// met à jour le nom d'un utilisateur en base
        /// </summary>
        /// <param name="userId">id de l'utilisateur à mettre à jour</param>
        /// <param name="newName">nouveau nom inseré dans le champ "name" de l'utilisateur</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int ModifyUserName(int userId, string newName)
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            data.Add("name", newName);
            clauseWhere.Add("id", userId.ToString());
            return access.UpdateData("user", data, clauseWhere);
        }
        /// <summary>
        /// met à jour le mot de passe d'un utilisateur en base
        /// </summary>
        /// <param name="userId">id de l'utilisateur à mettre à jour</param>
        /// <param name="newPass">nouveau mot de passe inseré dans le champ "password" de l'utilisateur</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int ModifyUserPass(int userId, string newPass)
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            data.Add("password", newPass);
            clauseWhere.Add("id", userId.ToString());
            return access.UpdateData("user", data, clauseWhere);
        }
        /// <summary>
        /// met à jour le mail d'un utilisateur en base
        /// </summary>
        /// <param name="userId">id de l'utilisateur à mettre à jour</param>
        /// <param name="newPass">nouveau mail inseré dans le champ "mail" de l'utilisateur</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int ModifyUserMail(int userId, string newMail)
        {
            Dictionary<string, string> data = new Dictionary<string, string>();
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            data.Add("mail", newMail);
            clauseWhere.Add("id", userId.ToString());
            return access.UpdateData("user", data, clauseWhere);
        }
        /// <summary>
        /// met à jour les droits d'un utilisateur en base
        /// </summary>
        /// <param name="userId">id de l'utilisateur à mettre à jour</param>
        /// <param name="newRight">nouveau droit affecté à l'utilisateur (transformation en idUser_right automatique)</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int ModifyUserRight(int userId, string newRight)
        {
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            clauseWhere.Add("description", newRight);
            MySqlDataReader result = access.GetData(new string[] { "id" }, new string[] { "user_right" }, clauseWhere);
            result.Read();
            int idUserRight = int.Parse(result["id"].ToString());
            clauseWhere.Clear();

            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("user_right", idUserRight.ToString());
            clauseWhere.Add("id", userId.ToString());
            return access.UpdateData("user", data, clauseWhere);
        }
        /// <summary>
        /// obtient l'id d'un utilisateur
        /// </summary>
        /// <param name="name">nom de l'utilisateur voulu</param>
        /// <returns></returns>
        public int? getIdUser(string name)
        {
            Dictionary<string, string> clauseWhere = new Dictionary<string, string>();
            clauseWhere.Add("name", name);
            MySqlDataReader result = access.GetData(new string[] { "id" }, new string[] { "user" }, clauseWhere);
            result.Read();
            int retour;
            try
            {
                retour = int.Parse(result["id"].ToString());
            }catch(Exception e)
            {
                return null;
            }
            return retour;
        }
    }
}
