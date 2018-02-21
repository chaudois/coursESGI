using System;
using System.Collections.Generic;
using System.Text;

namespace BLLTPExpaceo
{
    public class UserRight
    {
        int id;
        public string description;

        public UserRight(int id, string description=null)
        {
            this.id = id;
            this.description = description;
        }
    }
}
