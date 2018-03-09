package bank.db.dao;


import bank.db.entity.RolesData;

public interface RoleDao {
    RolesData getOne(int numberOfRole);
}
