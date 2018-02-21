using System;
using MySql.Data.MySqlClient;
using System.Collections.Generic;
using System.Runtime.InteropServices.ComTypes;
using System.Data;

namespace DALtpExpaceo
{
    public class MySQLAccess
    {
        MySqlConnection connection;
        /// <summary>
        /// créer un connexion vers une base MySQL
        /// </summary>
        /// <param name="BDD">nom de la base ou se connecter</param>
        /// <param name="adresse">adresse de la base</param>
        /// <param name="port">port de la base</param>
        /// <param name="userName">login pour se connecter à la base</param>
        /// <param name="password">mot de passe de l'utilisateur renseigné en parametre</param>
        public MySQLAccess(string BDD, string adresse, int? port = null, string userName = null, string password = null)
        {

            string connData = "Server=" + adresse;
            connData += port == null ? "" : ";Port=" + port;
            connData += ";Database=" + BDD;
            connData += ";Uid=";
            connData += userName == null ? "root" : userName;
            connData += password == null ? ";" : ";Pwd=" + password + ";";
            connection = new MySqlConnection(connData);
            connection.Open();
        }
        /// <summary>
        /// rajoute une entré dans la base connecté à l'objet
        /// </summary>
        /// <param name="table">table ou ajouter les données</param>
        /// <param name="data">couple nomDeChamp=>donnéeAInserer</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int AddData(string table, Dictionary<string, string> data)
        {
            if (table == null) throw new ArgumentException("le parametre table ne peut etre null");
            MySqlCommand query = connection.CreateCommand();
            #region creationRequete
            string requete = "INSERT INTO ";
            requete += table;
            requete += " ( ";

            foreach (KeyValuePair<string, string> k in data)
            {
                requete += k.Key.ToString();
                requete += " , ";
            }
            requete = requete.Substring(0, requete.Length - 2);//on enleve la dernière virgule
            requete += " ) VALUES (";
            foreach (KeyValuePair<string, string> k in data)
            {
                try//on check le type de data (string ou int)
                {
                    double value = double.Parse(k.Value.ToString());
                    requete += k.Value.ToString();
                    requete += " , ";
                }
                catch (Exception e)
                {
                    requete += " '" + k.Value.ToString() + "' ";
                    requete += " , ";
                }
            }
            requete = requete.Substring(0, requete.Length - 2);//on enleve la dernière virgule

            requete += " );";
            #endregion
            query.CommandText = requete;

            return query.ExecuteNonQuery();
        }
        /// <summary>
        /// supprime une ou plusieurs entré dans la table de l'objet dans les champs voulues
        /// </summary>
        /// <param name="table">table d'ou supprimer les données</param>
        /// <param name="clauseWhere">couple champAVerfier=>Valeur (AND si plusieur lignes)</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int RemoveData(string table, Dictionary<string, string> clauseWhere)
        {
            if (table == null) throw new ArgumentException("le parametre table ne peut etre null");
            MySqlCommand query = connection.CreateCommand();

            string requete = "DELETE FROM ";
            #region creationRequete
            requete += table;
            if (clauseWhere != null)
            {
                requete += " WHERE ";
                int cpt = 0;
                foreach (KeyValuePair<string, string> k in clauseWhere)
                {
                    if (cpt != 0) requete += " AND ";
                    requete += k.Key.ToString();
                    requete += " = ";
                    requete +="'"+ k.Value.ToString()+"'";
                    requete += ", ";
                    cpt++;
                }
                requete = requete.Substring(0, requete.Length - 2);//on enleve la dernière virgule
            }
            requete += ";";
            #endregion
            query.CommandText = requete;

            return query.ExecuteNonQuery();

        }
        /// <summary>
        /// met à jour un ou plusieurs champ dans la base de l'objet sur la table choisis
        /// </summary>
        /// <param name="table">table à update</param>
        /// <param name="dataToUpdate">couple nomDuChamp=>nouvelleValeur</param>
        /// <param name="clauseWhere">couple champAVerfier=>Valeur (AND si plusieur lignes)</param>
        /// <returns>retourne le code d'erreur de l'execution de la requete via query.ExecuteNonQuery()</returns>
        public int UpdateData(string table, Dictionary<string, string> dataToUpdate, Dictionary<string, string> clauseWhere)
        {
            if (dataToUpdate == null
                || dataToUpdate.Count == 0) throw new ArgumentException("il faut au mois UPDATE un champ");
            MySqlCommand query = connection.CreateCommand();
            #region creationRequete
            string requete = "UPDATE ";
            requete += table;
            requete += " SET ";
            foreach (KeyValuePair<string, string> k in dataToUpdate)
            {
                requete += k.Key.ToString();
                requete += " = ";

                try//on check le type de data (string ou int)
                {
                    double value = double.Parse(k.Value.ToString());
                    requete += k.Value.ToString();
                    requete += " , ";
                }
                catch (Exception e)
                {
                    requete += " '" + k.Value.ToString() + "' ";
                    requete += " , ";
                }

            }
            requete = requete.Substring(0, requete.Length - 2);//on enleve la dernière virgule

            requete += " WHERE ";
            int cpt = 0;
            foreach (KeyValuePair<string, string> k in clauseWhere)
            {
                if (cpt != 0) requete += " AND ";
                requete += k.Key.ToString();
                requete += " = ";
                requete += "'"+k.Value.ToString()+"'";
                requete += ", ";
                cpt++;

            }
            requete = requete.Substring(0, requete.Length - 2);//on enleve la dernière virgule
            requete += ";";
            #endregion
            query.CommandText = requete;

            return query.ExecuteNonQuery();
        }
        /// <summary>
        /// retourn le resultat de l'execution de la requete SQL construite avec les parametres d'entrés
        /// </summary>
        /// <param name="table">contenu du SELECT</param>
        /// <param name="champs">contenu du FROM</param>
        /// <param name="clauseWhere">contenu du WHERE (AND si count>1)</param>
        /// <param name="orderBy">contenu du ORDER BY</param>
        /// <returns>retourne le resultat de la requete généré.ex : accès à la variable "name" de la 2eme ligne faire retour.read();retour.read();string name=retour["name"].toString()</returns>
        public DataTable GetData(string[] champs, string[] table, Dictionary<string, string> clauseWhere = null, string[] orderBy = null)
        {
            string requete = "";
            MySqlCommand query = connection.CreateCommand();
            #region creationRequete
            requete += "SELECT ";
            if (table.Length < 1) throw new ArgumentException("le parametre table est vide");
            if (champs == null || champs.Length == 0)
            {
                requete += " * ";
            }
            else
            {
                for (int i = 0; i < champs.Length; i++)
                {
                    if (i != 0) requete += ",";
                    requete += champs[i];
                }

            }
            requete += " FROM ";
            for (int i = 0; i < table.Length; i++)
            {
                if (i != 0) requete += ",";
                requete += table[i];
            }
            if (clauseWhere != null)
            {
                requete += " WHERE ";
                int cpt = 0;
                foreach (KeyValuePair<string, string> k in clauseWhere)
                {
                    if (cpt != 0) requete += " AND ";
                    requete += k.Key.ToString();
                    requete += " = ";
                    requete += "'" + k.Value.ToString() + "'";
                    requete += ", ";
                    cpt++;

                }
                requete = requete.Substring(0, requete.Length - 2);//on enleve la dernière virgule

            }
            if (orderBy != null)
            {
                requete += " ORDER BY ";
                for (int i = 0; i < orderBy.Length; i++)
                {
                    if (i != 0) requete += ",";
                    requete += orderBy[i];
                }
            }
            #endregion
            query.CommandText = requete;

            query.ExecuteNonQuery();

            MySqlDataReader dataReader = query.ExecuteReader();

            DataSet dataSet = new DataSet();
            DataTable dataTable = new DataTable();
            dataSet.Tables.Add(dataTable);
            dataTable.Load(dataReader);
            dataReader.Close();
            return dataTable;
        }
    }
    
}
