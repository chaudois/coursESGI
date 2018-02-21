'use strict';
module.exports = function(sequelize, DataTypes) {
  var Student = sequelize.define('Student', {
    id: {
      type: DataTypes.BIGINT,
        primaryKey: true,
        autoIncrement: true
    },
      lastname: {
        type : DataTypes.STRING
      },
      firstname:{
        type:DataTypes.STRING
      },
      birthdate:{
        type:DataTypes.DATE
      }
  }, {
      paranoid: true,
      underscored: true,
      freezeTableName: true,
    classMethods: {
      associate: function(models) {
        Student.belongsTo(models.School);
        Student.belongsToMany(models.Project,{
          through : "StudentProject"
        })
        // associations can be defined here
      }
    },
    instanceMethods : {
      responsify : function(){
        let result={};
        result.id=this.id;
        result.lastname=this.lastname;
        result.firstname=this.firstname;
        result.birthdate=this.birthdate;
        if(this.School){
          result.school=this.School.responsify();
        }
        if(this.Projects){
          result.projects=this.Projects;
        }
        return result;
      }
    }
  });
  return Student;
};
