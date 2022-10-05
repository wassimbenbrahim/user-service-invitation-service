package Datejava.sql;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class Timestamp {
	
	 @Basic
	    private java.sql.Date sqlDate;

	    @Basic
	    private java.sql.Time sqlTime;

	    @Basic
	    private java.sql.Timestamp sqlTimestamp;
	    
	    

}
