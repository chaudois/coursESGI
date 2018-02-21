using System;
using DALtpExpaceo;
using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Data;

namespace BLLTPExpaceo
{
    public class DataAccess
    {
        public MySQLAccess access;

        /// <summary>
        /// se connecte à la base tpexpaceo en local à la construction
        /// </summary>
        public DataAccess()
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

            return DataTableToListOfUser( access.GetData(select, from, where, order_by));

           
        }
        /// <summary>
        /// renvoie la liste de tout les utilisateur present dans la table user par ordre de permission
        /// </summary>
        /// <returns></returns>
        public List<User> GetUsersOrderByRight()
        {
            string[] from = { "user" };
            string[] select = { "id", "name", "password", "mail", "user_right" };
            Dictionary<string, string> where = null;
            string[] order_by = { "user_right" };

            List<User> retour = new List<User>();
            return DataTableToListOfUser(access.GetData(select, from, where, order_by));

        }
        /// <summary>
        /// renvoie la liste de tout les utilisateur present dans la table user par ordre de création
        /// </summary>
        /// <returns></returns>
        public List<User> GetUsersOrderById()
        {
            string[] from = { "user" };
            string[] select = { "id", "name", "password", "mail", "user_right" };
            Dictionary<string, string> where = null;
            string[] order_by = { "id" };

            List<User> retour = new List<User>();
            return DataTableToListOfUser(access.GetData(select, from, where, order_by));

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
            DataTable result = access.GetData(new string[] { "id" },
                new string[] { "user_right" },
                clauseWhere);
            int idRight=int.Parse(result.Rows[0]["id"].ToString());
            clauseWhere.Clear();
            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("user_right", idRight.ToString());
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
            try
            {
                DataTable retourBase = access.GetData(new string[] { "id" },
                    new string[] { "user" },
                    clauseWhere);
                return int.Parse(retourBase.Rows[0]["id"].ToString());
            }catch(ArgumentOutOfRangeException aoorE)
            {
                return null;
            }catch(IndexOutOfRangeException ioorE)
            {
                return null;
            }


        }
        /// <summary>
        /// renvoie la liste de tout les droit d'utilisateur present dans la table user_right
        /// </summary>
        /// <returns></returns>
        public List<UserRight> GetUserRights()
        {
            string[] from = { "user_right" };
            string[] select = { "id", "description"};
            Dictionary<string, string> where = null;
            string[] order_by = { "id" };
            DataTable retourRequete = access.GetData(select, from, where, order_by);
            List<UserRight> retourFonction = new List<UserRight>();
            foreach(DataRow row in retourRequete.Rows)
            {
                try
                {
                    UserRight userRight = new UserRight(int.Parse(row["id"].ToString()),
                        row["description"].ToString());
                    retourFonction.Add(userRight);
                }catch(Exception e) { }
            }
            return retourFonction;
        }
        public UserRight getUserRightById(int id)
        {
            string[] from = { "user_right" };
            string[] select = { "id", "description" };
            Dictionary<string, string> where = new Dictionary<string, string>();
            where.Add("id", id.ToString());
            string[] order_by = { "name" };

            DataTable retourDeRequete = access.GetData(select, from, where, null);
            return new UserRight(id, retourDeRequete.Rows[0]["description"].ToString());
        }
        public string getUserRightDecscriptionById(int id)
        {
            string[] from = { "user_right" };
            string[] select = { "id", "description" };
            Dictionary<string, string> where = new Dictionary<string, string>();
            where.Add("id", id.ToString());
            string[] order_by = { "name" };

            DataTable retourDeRequete = access.GetData(select, from, where, null);
            return retourDeRequete.Rows[0]["description"].ToString();
        }
        /// <summary>
        /// transforme un dataTable en liste de user si celui ci contient des users
        /// </summary>
        /// <param name="table"></param>
        /// <returns>retourne tout les users contenue dans le DataTable. null si il y a autre chose</returns>
        private List<User> DataTableToListOfUser(DataTable table)
        {
            List<User> lstUser = new List<User>();
            foreach(DataRow row in table.Rows)
            {

                try
                {
                    User u = new User(int.Parse(row["id"].ToString()),
                        row["name"].ToString(),
                        row["password"].ToString(),
                        null,
                        null);

                    try
                    {
                        u.user_right =new UserRight(
                            int.Parse(row["user_right"].ToString())
                            ,getUserRightDecscriptionById(int.Parse(row["user_right"].ToString())));
                    }
                    catch (Exception e) { }
                    try
                    {
                        u.mail = row["mail"].ToString();
                    }catch(Exception e) { }
                    lstUser.Add(u);

                }
                catch (Exception e)
                {
                    continue;
                }

            }
            return lstUser;

        }
    }
}
