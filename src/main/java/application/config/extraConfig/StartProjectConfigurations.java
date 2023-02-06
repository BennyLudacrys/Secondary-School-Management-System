package application.config.extraConfig;

import application.entity.ClassRoom;
import application.entity.Responsibility;
import application.entity.Role;
import application.enumerated.ClassShift;
import application.entity.user.Principal;
import application.entity.user.Student;
import application.entity.user.Teacher;
import application.repository.*;
import application.repository.RoleRepository;
import application.vo.AddressVO;
import application.vo.RegistrationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


import java.time.Instant;
import java.util.Arrays;


@Configuration
@Profile(value = {"test","dev"})
public class StartProjectConfigurations implements CommandLineRunner { // Essa classe é uma clase separada de configuração, Ela serve para popular o banco de dados!
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ClassRoomRepository classRoomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResponsibilityRepository responsibilityRepository;

    @Override
    public void run(String... args) { // Esse método fala que toda vez que o programa iniciar(aplicação for pro ar) em ambiente de DEV E TEST.
        // , esse método vai ser chamado, e também oq tem dentro.
        System.out.println(ApplicationDetails.text());                                // Preste atenção como as associações são feitas no método!

        String passwordEncode = "$2a$10$KT5rbfQTU8103kP6uEmkkO3W8XTc4MFH2peGPuL3sQ3X5ne.kz2oK";

        ClassRoom cr1 = new ClassRoom('A', ClassShift.MANHA);


        Student u1 = new Student("Joao Muvambo", "joao@gmail.com", passwordEncode, cr1);
        Student u2 = new Student("Victor Placido", "victor@live.com", passwordEncode, cr1);
        Student u3 = new Student("Renato Langa", "renato@hotmail.com", passwordEncode, cr1);
        Student u4 = new Student("Ginovaldo Cumbe", "ginovaldo@gmail.com", passwordEncode, null);
        Principal principal = new Principal("Benedito Muianga", "Bennedito01@gmail.com", passwordEncode);

        RegistrationVO registration1 = new RegistrationVO(Instant.parse("2022-06-20T19:53:07Z"));
        RegistrationVO registration2 = new RegistrationVO(Instant.parse("2022-07-21T03:42:10Z"));
        RegistrationVO registration3 = new RegistrationVO(Instant.parse("2022-07-22T15:21:22Z"));
        RegistrationVO registration4 = new RegistrationVO(Instant.parse("2022-06-20T19:53:07Z"));
        RegistrationVO registration5 = new RegistrationVO(Instant.parse("2022-07-21T03:42:10Z"));

        AddressVO a1 = new AddressVO("Belo Horizonte", "Boane", "Mozambique");
        AddressVO a2 = new AddressVO("Maputo", "Matola", "Mozambique");
        AddressVO a3 = new AddressVO("Tete", "Tete", "Mozambique");
        AddressVO a4 = new AddressVO("Cabo delegado", "Mocimboa da praia", "Mozambique");
        AddressVO principalAddress = new AddressVO("Maputo", "NKOBE", "Mozambique");

        Responsibility res1 = new Responsibility("Verifica quais estudantes que nao tem classes!");
        Responsibility res2 = new Responsibility("verifica se algum professor nao tem classe!");
        Responsibility res3 = new Responsibility("verifica quantos alunos/ professores estao no sistema!");
        Responsibility res4 = new Responsibility("Tem que registar Turmas / Professores / alunos no sistema e deleta-os!");

        responsibilityRepository.saveAll(Arrays.asList(res1, res2, res3, res4));
        principal.addResponsibility(res1);
        principal.addResponsibility(res2);
        principal.addResponsibility(res3);
        principal.addResponsibility(res4);

        u1.setRegistration(registration1);
        u2.setRegistration(registration2);
        u3.setRegistration(registration3);
        u4.setRegistration(registration4);
        principal.setRegistration(registration5);
        u1.setAddress(a1);
        u2.setAddress(a2);
        u3.setAddress(a3);
        u4.setAddress(a4);
        principal.setAddress(principalAddress);


        classRoomRepository.save(cr1);
        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, principal));

        Teacher teacher1 = new Teacher("Biote", "Ngovene@gmail.com", passwordEncode, cr1);
        AddressVO a5 = new AddressVO("Tampa", "Florida", "Mozambique");
        RegistrationVO registration6 = new RegistrationVO(Instant.parse("2022-11-22T03:42:10Z"));
        teacher1.setAddress(a5);
        teacher1.setRegistration(registration6);

        userRepository.save(teacher1);

        cr1.setTeacher(teacher1);


        Role role1 = new Role("ROLE_STUDENT");
        Role role2 = new Role("ROLE_TEACHER");
        Role role3 = new Role("ROLE_PRINCIPAL");


        roleRepository.saveAll(Arrays.asList(role1, role2, role3));
        u1.addRole(role1);
        u2.addRole(role1);
        u3.addRole(role1);
        u4.addRole(role1);
        teacher1.addRole(role2);
        principal.addRole(role3);

        userRepository.saveAll(Arrays.asList(u1, u2, u3, u4, principal));

        ClassRoom cr2 = new ClassRoom('B', ClassShift.TARDE);

        classRoomRepository.saveAll(Arrays.asList(cr1, cr2));


        Teacher teacher2 = new Teacher("Jordao", "Jordao@gmail.com", passwordEncode);
        AddressVO a6 = new AddressVO("Maputo", "Nkobe", "Mozambique");
        RegistrationVO registration7 = new RegistrationVO(Instant.parse("2022-07-21T03:42:10Z"));
        teacher2.addRole(role2);
        teacher2.setAddress(a6);
        teacher2.setRegistration(registration7);
        userRepository.save(teacher2);
    }
}