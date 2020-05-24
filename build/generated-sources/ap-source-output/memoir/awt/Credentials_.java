package memoir.awt;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import memoir.awt.Person;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-23T12:46:41")
@StaticMetamodel(Credentials.class)
public class Credentials_ { 

    public static volatile SingularAttribute<Credentials, Date> signupdate;
    public static volatile SingularAttribute<Credentials, String> password;
    public static volatile CollectionAttribute<Credentials, Person> personCollection;
    public static volatile SingularAttribute<Credentials, Integer> credentialsId;
    public static volatile SingularAttribute<Credentials, String> username;

}