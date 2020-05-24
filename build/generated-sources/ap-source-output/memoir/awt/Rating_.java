package memoir.awt;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import memoir.awt.Memoir;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-05-23T12:46:41")
@StaticMetamodel(Rating.class)
public class Rating_ { 

    public static volatile SingularAttribute<Rating, BigDecimal> star;
    public static volatile CollectionAttribute<Rating, Memoir> memoirCollection;
    public static volatile SingularAttribute<Rating, String> scale;

}