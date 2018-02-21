using System;
using System.Collections.Generic;
using System.Text;

namespace DALtpExpaceo
{
    public class User
    {
        public int id;
        public string name;
        public string password;
        public string mail;
        public int user_right;

        public User(int id, string name, string password, string mail=null, int user_right=3)
        {
            this.id = id;
            this.name = name;
            this.password = password;
            this.mail = mail;
            this.user_right = user_right;
        }

    }
}
