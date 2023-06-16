package by.vstu.model.generator;

import by.vstu.model.abiturient.Abiturient;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class AbiturientIdGenerator implements IdentifierGenerator {

    private static Long idValue;

    public static void setIdValue(Long idValue) {
        AbiturientIdGenerator.idValue = idValue;
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Long id = ((Abiturient) object).getId();
        if (id != null) {
            return id;
        } else {
            return ++idValue;
        }
    }
}
