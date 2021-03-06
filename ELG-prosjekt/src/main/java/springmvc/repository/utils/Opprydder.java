/*
T.H - kommentar:
Klassen er en hjelpeklasse som brukes i 
springmvc.respository.PersonDatabaseRepositoryImpl for opprydning.

Klassen trengs ikke om man velger å bruke
springmvc.respository.PersonDatabaseJdbcTemplateRepositoryImpl som 
implementasjon for PersonRepository (interface).

*/

package springmvc.repository.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Grethe
 */
public class Opprydder {
    public static void lukkResSet(ResultSet res) {
    try {
      if (res != null &&!res.isClosed()) res.close();
    } catch (SQLException e) {
      skrivMelding(e, "lukkResSet()");
    }
  }

  public static void lukkSetning(Statement stm) {
    try {
      if (stm != null && !stm.isClosed()) stm.close();
    } catch (SQLException e) {
      skrivMelding(e, "lukkSetning()");
    }
  }

  public static void lukkForbindelse(Connection forbindelse) {
    try {
      if (forbindelse != null && !forbindelse.isClosed()) forbindelse.close();
    } catch (SQLException e) {
      skrivMelding(e, "lukkForbindelse()");
    }
  }

  public static void rullTilbake(Connection forbindelse) {
    try {
      if (forbindelse != null && !forbindelse.getAutoCommit()) forbindelse.rollback();
    } catch (SQLException e) {
      skrivMelding(e, "rollback()");
    }
  }

  public static void settAutoCommit(Connection forbindelse) {
    try {
      if (forbindelse != null && !forbindelse.getAutoCommit()) forbindelse.setAutoCommit(true);
    } catch (SQLException e) {
      skrivMelding(e, "settAutoCommit()");
    }
  }

  public static void skrivMelding(Exception e, String melding) {
    e.printStackTrace(System.err);
  }
}
