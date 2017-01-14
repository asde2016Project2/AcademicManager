package it.unical.asde.uam.persistence;

import it.unical.asde.uam.dao.DBHandler;
import it.unical.asde.uam.model.ExamSession;

public class ExamSessionDAOImp implements ExamSessionDAO {

	private DBHandler dbHandler;
	
	  public DBHandler getDbHandler() {
	        return dbHandler;
	    }

	    public void setDbHandler(DBHandler dbHandler) {
	        this.dbHandler = dbHandler;
	    }

	@Override
	public void create(ExamSession examSession) {
		dbHandler.create(examSession);
	}

	@Override
	public void saveUpdates(ExamSession examSession) {
		
		dbHandler.update(examSession);
	}

	@Override
	public void deleteAttempt(ExamSession examSession) {
		
		dbHandler.delete(examSession);
	}
	
	

}