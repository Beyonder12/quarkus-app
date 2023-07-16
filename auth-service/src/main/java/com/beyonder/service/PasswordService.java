package com.beyonder.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.wildfly.security.password.Password;
import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.interfaces.BCryptPassword;
import org.wildfly.security.password.interfaces.ClearPassword;
import org.wildfly.security.password.spec.ClearPasswordSpec;
import org.wildfly.security.password.spec.EncryptablePasswordSpec;
import org.wildfly.security.password.spec.IteratedSaltedPasswordAlgorithmSpec;
import org.wildfly.security.password.util.ModularCrypt;

import java.security.InvalidKeyException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

@ApplicationScoped
public class PasswordService {

    @Inject
    PasswordFactory passwordFactory;

    public String hashPassword(String clearPassword) throws InvalidKeySpecException {
        byte[] salt = new byte[BCryptPassword.BCRYPT_SALT_SIZE];
        new SecureRandom().nextBytes(salt);

        IteratedSaltedPasswordAlgorithmSpec iteratedAlgorithmSpec = new IteratedSaltedPasswordAlgorithmSpec(10, salt);
        EncryptablePasswordSpec encryptableSpec = new EncryptablePasswordSpec(clearPassword.toCharArray(), iteratedAlgorithmSpec);

        BCryptPassword bcryptPassword = (BCryptPassword) passwordFactory.generatePassword(encryptableSpec);
        return ModularCrypt.encodeAsString(bcryptPassword);
    }

    public boolean checkPassword(String hashedPassword, String clearPassword) throws InvalidKeySpecException, InvalidKeyException {
        BCryptPassword storedPassword = (BCryptPassword) passwordFactory.translate(ModularCrypt.decode(hashedPassword));

        return passwordFactory.verify(storedPassword, clearPassword.toCharArray());
    }

}

