package memoir.awt;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import memoir.awt.Memoir;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-14T18:38:50")
@StaticMetamodel(Cinema.class)
public class Cinema_ { 

    public static volatile SingularAttribute<Cinema, Integer> cinemaId;
    public static volatile CollectionAttribute<Cinema, Memoir> memoirCollection;
    public static volatile SingularAttribute<Cinema, String> cinemaName;
    public static volatile SingularAttribute<Cinema, String> location;

}