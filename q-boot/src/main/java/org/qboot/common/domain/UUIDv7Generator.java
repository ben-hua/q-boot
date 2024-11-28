package org.qboot.common.domain;

import java.lang.reflect.Member;
import java.util.Properties;
import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import de.fxlae.typeid.TypeId;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class UUIDv7Generator implements IdentifierGenerator {

    public static final String UUID_TYPE = "UUID";
    public static final String STRING_TYPE = "String";

    private String type = STRING_TYPE;

    @Override
    public Object generate(SharedSessionContractImplementor session, Object object) {
        if(type.equals(UUID_TYPE)){
            return uuid();
        }
        return uuid().toString();
    }

    public static UUID uuid() {
        TypeId gen = TypeId.generate();
        return gen.uuid();
    }
}
