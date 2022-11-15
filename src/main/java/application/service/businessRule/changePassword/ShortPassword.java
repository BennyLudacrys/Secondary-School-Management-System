package application.service.businessRule.changePassword;

import application.service.exception.studentAreaService.ShortPasswordException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ShortPassword implements ChangePasswordCheck {

    @Override
    public void validation(String newPassword, String oldPassword) {
        oldPassword = null; // Só para provar que não tem utilidade

        if (newPassword.length() < 6) {
            throw new ShortPasswordException("A senha nao pode ser menor que 6 caracters");
        }

    }
}
