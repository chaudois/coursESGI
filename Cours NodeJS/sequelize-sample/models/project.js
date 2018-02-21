'use strict';
module.exports = function(sequelize, DataTypes) {
  var Project = sequelize.define('Project', {
    id: {
      type: DataTypes.BIGINT,
        primaryKey: true,
        autoIncrement: true
    },
      title: {
        type : DataTypes.STRING
      },
      description:{
        type:DataTypes.STRING
      },
      release_date:{
        type:DataTypes.DATE
      }
  }, {
      paranoid: true,
      underscored: true,
      freezeTableName: true,
    classMethods: {
      associate: function(models) {
        Project.belongsToMany(models.Student,{
          through: "StudentProject"
        });

        // associations can be defined here
      }
    },
    instanceMethods : {
      responsify : function(){
        return this;
      }
    }
  });
  return Project;
};
