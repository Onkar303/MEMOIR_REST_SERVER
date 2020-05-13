package memoir.awt;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import memoir.awt.Credentials;
import memoir.awt.Memoir;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-22T16:00:34")
@StaticMetamodel(Person.class)
public class Person_ { 

    public static volatile SingularAttribute<Person, String> address;
    public static volatile SingularAttribute<Person, String> gender;
    public static volatile SingularAttribute<Person, String> surname;
    public static volatile SingularAttribute<Person, Date> dob;
    public static volatile CollectionAttribute<Person, Memoir> memoirCollection;
    public static volatile SingularAttribute<Person, String> name;
    public static volatile SingularAttribute<Person, Integer> personId;
    public static volatile SingularAttribute<Person, Credentials> credentialsId;

}