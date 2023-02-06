package application.service.businessRule.updateGrade;

import application.entity.ClassRoom;
import application.dto.request.NewGradesForm;
import application.service.exception.classRoomService.ThereIsntTeacherInThisClassException;
import application.service.exception.general.NoPermissionException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@Order(1)
public class TeacherAllowed implements UpdateGradesCheck {
    @Override
    public void validation(NewGradesForm newGrades, ClassRoom classRoom, Principal user) { // O professor que for alterar a nota do aluno tem q ser o mesmo professor da classe.
        newGrades = null;     //  Nullos porquê não tem função nessa classe.

        String userLoggedName = user.getName();
        String classTeacherName;

        if(classRoom.getTeacher() == null) {
            throw new ThereIsntTeacherInThisClassException("Esta turma nao tem nenum professor");
        }
        classTeacherName = classRoom.getTeacher().getEmail();

        if (!classTeacherName.equals(userLoggedName)) {
            throw new NoPermissionException("Precisas de permissao! Apenas o professor da turma pode aceder aqui!");
        }

    }
}
