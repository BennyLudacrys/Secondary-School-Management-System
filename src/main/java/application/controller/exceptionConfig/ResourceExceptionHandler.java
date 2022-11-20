package application.controller.exceptionConfig;

import application.service.exception.classRoomService.*;
import application.service.exception.general.DatabaseException;
import application.service.exception.general.InvalidParamException;
import application.service.exception.general.NoPermissionException;
import application.service.exception.general.ResourceNotFoundException;
import application.service.exception.studentAreaService.SamePasswordException;
import application.service.exception.studentAreaService.ShortPasswordException;
import application.service.exception.general.EmailAlreadyRegisteredException;
import application.service.exception.userService.RemovePrincipalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {  // Se ocorrer alguma das execções abaixo durante o programa, o Spring vai cair nessa classe e vai retornar o erro
    // de uma forma mais agradavél pro cliente.
    @ExceptionHandler(ResourceNotFoundException.class)          // Caso algum recurso não seja encontrado.
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Recurso nao encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)  // Algum problema com o banco de dados.
    public ResponseEntity<StandardError> dataBase(DatabaseException e, HttpServletRequest request) {
        String error = "Erro na Database";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NoPermissionException.class)        // Sem permissão para fazer alguma operação dentro do sistema.
    public ResponseEntity<StandardError> noPermission(NoPermissionException e, HttpServletRequest request) {
        String error = "Forbidden";
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ClassContainsSameStudentException.class)
    // Quando tenta adicionar um aluno em uma classe em que ele já está adicionado.
    public ResponseEntity<StandardError> classContainsSameStudent(ClassContainsSameStudentException e, HttpServletRequest request) {
        String error = "Estudante ja existe";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TeacherHasAnotherClassException.class)
    // Quando tentam setar o professor na mesma classe que ele pertence.
    public ResponseEntity<StandardError> teacherHasAnotherClass(TeacherHasAnotherClassException e, HttpServletRequest request) {
        String error = "Este professor ja tem uma Turma";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(StudentHasAnotherClassException.class)
    // Quando tenta adicionar um usuário em uma classe sendo que ele já está em outra.
    public ResponseEntity<StandardError> studentHasAnotherClass(StudentHasAnotherClassException e, HttpServletRequest request) {
        String error = "Aluno Ja tem uma turma.";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(GradeLimitException.class) // Quando tenta adicionar uma nota incorreta á um usuário.
    public ResponseEntity<StandardError> gradeLimit(GradeLimitException e, HttpServletRequest request) {
        String error = "Valor nao permitido";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(NullPointerException.class) // Quando algo é nulo que não poderia ser!
    public ResponseEntity<StandardError> nullPointer(NullPointerException e, HttpServletRequest request) {
        String error = "Valor nao pode ser nulo";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(IllegalArgumentException.class) // Quando passa alguma informação errada!
    public ResponseEntity<StandardError> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        String error = "Valor errado, Passe um outro valor!";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(StudentDoesntExistInThisClassException.class)
    // Quando tenta buscar um usuário que não existe na sala!
    public ResponseEntity<StandardError> studentDoesntExistInThisClassRoom(StudentDoesntExistInThisClassException e, HttpServletRequest request) {
        String error = "Aluno nao encontrado aqui!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(SamePasswordException.class) // Quando a senha alterada é igual á anterior.
    public ResponseEntity<StandardError> samePassword(SamePasswordException e, HttpServletRequest request) {
        String error = "Mesma semha";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(ShortPasswordException.class) // Quando a senha é muito pequena para ser alterada !
    public ResponseEntity<StandardError> shortPassword(ShortPasswordException e, HttpServletRequest request) {
        String error = "senha curta";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ThereIsntTeacherInThisClassException.class) // Quando a classe não tem nenhum professor!
    public ResponseEntity<StandardError> thereIsNotTeacherInThisClass(ThereIsntTeacherInThisClassException e, HttpServletRequest request) {
        String error = "Nao tem professor aqui";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(InvalidParamException.class) // Quando o usuário passa algum parametro errado!
    public ResponseEntity<StandardError> invalidParam(InvalidParamException e, HttpServletRequest request) {
        String error = "Parametro Invalido";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(SameTeacherException.class) // Quando o mesmo professor é setado na mesma classa que já está!
    public ResponseEntity<StandardError> sameTeacher(SameTeacherException e, HttpServletRequest request) {
        String error = "Este professor ja esta aqui.";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(FullListException.class) // Quando a lista de alunos está cheia!
    public ResponseEntity<StandardError> fullList(FullListException e, HttpServletRequest request) {
        String error = "A lista esta cheia";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class) // Quando tenta cadastrar um usuário com o mesmmo email!
    public ResponseEntity<StandardError> emailAlreadyRegistered(EmailAlreadyRegisteredException e, HttpServletRequest request) {
        String error = "Email ja existe";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(TeacherDoesntHaveAnyClassException.class)
    // Quando tenta buscar a sala do professor porém o professor não tem nenhuma sala!
    public ResponseEntity<StandardError> teacherDoesntHaveAnyClass(TeacherDoesntHaveAnyClassException e, HttpServletRequest request) {
        String error = "Professor nao tem turma";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MaximumOfClassRoomsException.class)
    // Quando tenta criar uma sala no sistema mas já tem o máximo de classes permitido!
    public ResponseEntity<StandardError> maximumOfClassesRoom(MaximumOfClassRoomsException e, HttpServletRequest request) {
        String error = "Muitas turmas";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(RemovePrincipalException.class) // Quando tenta remover um principal(diretor) do sistema!
    public ResponseEntity<StandardError> removePrincipal(RemovePrincipalException e, HttpServletRequest request) {
        String error = "Deletado o admin";
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}
