package com.hipergarzon.workpages;

import com.hipergarzon.workpages.models.*;
import com.hipergarzon.workpages.models.enums.*;
import com.hipergarzon.workpages.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class WorkpagesApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(WorkpagesApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserGeneralRepository userGeneralRepository, ItemEducationRepository itemEducationRepository,
									  ItemJobExperienceRepository itemJobExperienceRepository, ItemLanguagesRepository itemLanguagesRepository,
									  ItemSkillsRepository itemSkillsRepository, ItemOthersDataRepository itemOthersDataRepository,
									  ItemPersonalDataRepository itemPersonalDataRepository, RecruiterRepository recruiterRepository,
									  JobVacancyRepository jobVacancyRepository, SeekerRepository seekerRepository, JobApplicationRepository jobApplicationRepository,
									  ItemFilesRepository itemFilesRepository, ItemsCatalogueRepository itemsCatalogueRepository, ItemsCatalogueCategoryRepository itemsCatalogueCategoryRepository
	) {
		return(args) -> {
			//User Admin
			Admin admin = new Admin("Naylu", "Vivas", "naylu24@admin.com", passwordEncoder.encode("12345678"));
			userGeneralRepository.save(admin);

			//UserRecruiter
			Recruiter userRecruiter = new Recruiter("Luana", "Saldana", "naylu@elgarzon.com", Rol.RECRUITER, "acarigua");
			recruiterRepository.save(userRecruiter);

			Recruiter userRecruiterTwo = new Recruiter("Hannah", "Banana", "hannahbanana@elgarzon.com", Rol.RECRUITER, "rotaria");
			recruiterRepository.save(userRecruiterTwo);

			//Usuario Buscador
			Seeker seeker = new Seeker("Katherine", "Casanova", "naylu@gmail.com", passwordEncoder.encode("12345678"));
			seekerRepository.save(seeker);

			Seeker seeker1 = new Seeker("luis", "saldana", "luis@gmail.com", passwordEncoder.encode("12345678"));
			seekerRepository.save(seeker1);

			Seeker seeker2 = new Seeker("luana", "vivas", "nay@gmail.com", passwordEncoder.encode("12345678"));
			seekerRepository.save(seeker2);

			Seeker seeker3 = new Seeker("hannah", "josefina", "hannah@gmail.com", passwordEncoder.encode("12345678"));
			seekerRepository.save(seeker3);

			//Items curriculum
			ItemPersonalData itemPersonalData = new ItemPersonalData(seeker, "Katherine", "Casanova", "naylu@gmail.com", LocalDate.of(1991, 2, 4), "95629301", "CASADO", YesOrNo.SI, 2, "5", "1124003019", "1124003019", "Mexico 3154", "Balvanera", "Barinas", "Masculino");
			itemPersonalDataRepository.save(itemPersonalData);

			ItemPersonalData itemPersonalData1 = new ItemPersonalData(seeker1, "luis", "saldana", "luis@gmail.com", LocalDate.of(1991, 2, 4), "95327250", "CASADO", YesOrNo.NO, 1, "5", "1124003019", "1124003019", "Mexico 3154", "Balvanera", "Tachira", "Femenino");
			itemPersonalDataRepository.save(itemPersonalData1);

			ItemPersonalData itemPersonalData2 = new ItemPersonalData(seeker2, "luana", "vivas", "naylu@gmail.com", LocalDate.of(1991, 2, 4), "57520591", "CASADO", YesOrNo.SI, 0, "5", "1124003019", "1124003019", "Mexico 3154", "Balvanera", "Maracaibo", "Otro");
			itemPersonalDataRepository.save(itemPersonalData2);

			ItemPersonalData itemPersonalData3 = new ItemPersonalData(seeker3, "hannah", "josefina", "hannah@gmail.com", LocalDate.of(1991, 2, 4), "95327250", "CASADO", YesOrNo.NO, 0, "5", "1124003019", "1124003019", "Mexico 3154", "Balvanera", "Merida", "Masculino");
			itemPersonalDataRepository.save(itemPersonalData3);

			ItemEducation itemEducation = new ItemEducation(seeker, "Ingenierio", "Unefa", "Universitario", "Ing Civil", "Venezuela", "Graduado", LocalDate.of(2009, 07, 15), LocalDate.of(2015, 07, 15));
			itemEducationRepository.save(itemEducation);

			ItemEducation itemEducation2 = new ItemEducation(seeker, "Licenciado", "UNET", "Universitario", "Arquitectura", "Venezuela", "Graduado", LocalDate.of(2009, 07, 15), LocalDate.of(2015, 07, 15));
			itemEducationRepository.save(itemEducation2);

			ItemEducation itemEducation1 = new ItemEducation(seeker1, "Ingenierio", "Unefa", "Universitario", "Educacion", "Venezuela", "Graduado", LocalDate.of(2009, 07, 15), LocalDate.of(2015, 07, 15));
			itemEducationRepository.save(itemEducation1);

			ItemJobExperience itemJobExperience = new ItemJobExperience(seeker, "Garzon", "Alimenticia", "Ingeniero", "LevelExperience.TRAINE", "AreaJob.INGENIERÍA", "Venezuela", LocalDate.of(2015, 8,01), LocalDate.of(2016, 9, 30), "oficina tecnica de inspecicon, bla bla bla bla");
			itemJobExperienceRepository.save(itemJobExperience);

			ItemJobExperience itemJobExperience1 = new ItemJobExperience(seeker1, "Garzon", "Alimenticia", "Ingeniero", "LevelExperience.TRAINEE", "AreaJob.INGENIERÍA", "Venezuela", LocalDate.of(2015, 8,01), LocalDate.of(2016, 9, 30), "oficina tecnica de inspecicon, bla bla bla bla");
			itemJobExperienceRepository.save(itemJobExperience1);

			ItemJobExperience itemJobExperience2 = new ItemJobExperience(seeker, "Garzon", "Alimenticia", "Ingeniero", "LevelExperience.TRAINEE", "AreaJob.INGENIERÍA", "Venezuela", LocalDate.of(2015, 8,01), LocalDate.of(2016, 9, 30), "oficina tecnica de inspecicon, bla bla bla bla");
			itemJobExperienceRepository.save(itemJobExperience2);

			ItemLanguages itemLanguages = new ItemLanguages(seeker, "Languages.ESPAÑOL", "LevelLanguages.A", "LevelLanguages.A1");
			itemLanguagesRepository.save(itemLanguages);

			ItemLanguages itemLanguages1 = new ItemLanguages(seeker1, "Languages.ESPAÑOL", "LevelLanguages.A2", "LevelLanguages.A1");
			itemLanguagesRepository.save(itemLanguages1);

			ItemSkills itemSkills = new ItemSkills(seeker1, "Trabajo en equipo", "Java  -  Spring");
			itemSkillsRepository.save(itemSkills);

			ItemSkills itemSkills1 = new ItemSkills(seeker, "Trabajo en equipo", "Java  -  Spring");
			itemSkillsRepository.save(itemSkills1);

			ItemOthersData itemOthersData = new ItemOthersData(seeker, "SalaryRange.RANGE_50_100"
					, YesOrNo.SI, "TypeDisability.COGNITIVA", YesOrNo.SI, "BranchOffice.ACARIGUA", "ReasonForResignation.OTRO", YesOrNo.SI, "Stage.ENTREVISTA", "JobAspires.ADMINISTRACION", "Availability.FULL_TIM", "TypeJob.PRESENCIAL", YesOrNo.SI, YesOrNo.SI, YesOrNo.SI, "Relationship.OTRO", "TypeTransport.PRIVADO");
			itemOthersDataRepository.save(itemOthersData);

			ItemOthersData itemOthersData1 = new ItemOthersData(seeker1, "SalaryRange.RANGE_100_150", YesOrNo.SI, "TypeDisability.COGNITIVA", YesOrNo.SI, "BranchOffice.ACARIGUA", "ReasonForResignation.OTRO", YesOrNo.SI, "Stage.ENTREVISTA", "JobAspires.ADMINISTRACION", "Availability.FULL_TIME", "TypeJob.PRESENCIAL", YesOrNo.SI, YesOrNo.SI, YesOrNo.SI, "Relationship.OTRO", "TypeTransport.PRIVADO");
			itemOthersDataRepository.save(itemOthersData1);

			//Vacantes
			JobVacancy jobVacancy = new JobVacancy(userRecruiter, "Gerente general", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Acarigua", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy);

			JobVacancy jobVacancy1 = new JobVacancy(userRecruiter, "Gerente general", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Acarigua", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy1);

			JobVacancy jobVacancy2 = new JobVacancy(userRecruiter, "charcutero", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Acarigua", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy2);

			JobVacancy jobVacancy3 = new JobVacancy(userRecruiter, "limpieza", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Acarigua", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy3);

			JobVacancy jobVacancy4 = new JobVacancy(userRecruiter, "RRHH", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Acarigua", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy4);

			JobVacancy jobVacancy5 = new JobVacancy(userRecruiterTwo, "mercadeo", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Acarigua", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy5);

			JobVacancy jobVacancy6 = new JobVacancy(userRecruiterTwo, "arq", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Acarigua", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy6);

			JobVacancy jobVacancy7 = new JobVacancy(userRecruiterTwo, "atencion al cliente", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Barinas", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy7);

			JobVacancy jobVacancy8 = new JobVacancy(userRecruiterTwo, "cajera", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Rotaria", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy8);

			JobVacancy jobVacancy9 = new JobVacancy(userRecruiterTwo, "repositor", "Aportar información de valor al Gerente General para la toma de decisiones", " Liderazgo", "Lagunillas", "2 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy9);

			JobVacancy jobVacancy10 = new JobVacancy(userRecruiterTwo, "Carnicero", "Aportar información de valor al Gerente General para la toma de decisiones", "Trabajo en equipo", "Pueblo nuevo", "1 años", "TRAINEE", "GERENTE_DE_PROYECTOS", LocalDate.now(), true);
			jobVacancyRepository.save(jobVacancy10);

			//Aplicar a vacantes
			JobApplication jobApplication = new JobApplication(seeker, jobVacancy);
			jobApplicationRepository.save(jobApplication);
			JobApplication jobApplication1 = new JobApplication(seeker, jobVacancy1);
			jobApplicationRepository.save(jobApplication1);
			JobApplication jobApplication2 = new JobApplication(seeker, jobVacancy2);
			jobApplicationRepository.save(jobApplication2);
			JobApplication jobApplication3 = new JobApplication(seeker, jobVacancy3);
			jobApplicationRepository.save(jobApplication3);
			JobApplication jobApplication4 = new JobApplication(seeker, jobVacancy4);
			jobApplicationRepository.save(jobApplication4);

			JobApplication jobApplication5 = new JobApplication(seeker1, jobVacancy2);
			jobApplicationRepository.save(jobApplication5);
			JobApplication jobApplication6 = new JobApplication(seeker1, jobVacancy5);
			jobApplicationRepository.save(jobApplication6);
			JobApplication jobApplication7 = new JobApplication(seeker1, jobVacancy8);
			jobApplicationRepository.save(jobApplication7);
			JobApplication jobApplication8 = new JobApplication(seeker1, jobVacancy4);
			jobApplicationRepository.save(jobApplication8);
			JobApplication jobApplication9 = new JobApplication(seeker1, jobVacancy9);
			jobApplicationRepository.save(jobApplication9);

			JobApplication jobApplication10 = new JobApplication(seeker2, jobVacancy6);
			jobApplicationRepository.save(jobApplication10);
			JobApplication jobApplication11 = new JobApplication(seeker2, jobVacancy5);
			jobApplicationRepository.save(jobApplication11);
			JobApplication jobApplication12 = new JobApplication(seeker2, jobVacancy);
			jobApplicationRepository.save(jobApplication12);
			JobApplication jobApplication13 = new JobApplication(seeker2, jobVacancy3);
			jobApplicationRepository.save(jobApplication13);
			JobApplication jobApplication14 = new JobApplication(seeker2, jobVacancy10);
			jobApplicationRepository.save(jobApplication14);

			/*CATEGORIES*/
			ItemsCatalogueCategory itemsCatalogueCategory1 = new ItemsCatalogueCategory("Actividad de la Compañia");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory1);
			ItemsCatalogueCategory itemsCatalogueCategory2 = new ItemsCatalogueCategory("Área de trabajo");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory2);
			ItemsCatalogueCategory itemsCatalogueCategory3 = new ItemsCatalogueCategory("Área de estudio");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory3);
			ItemsCatalogueCategory itemsCatalogueCategory4 = new ItemsCatalogueCategory("Disponibilidad horaria");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory4);
			ItemsCatalogueCategory itemsCatalogueCategory5 = new ItemsCatalogueCategory("Estado civil");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory5);
			ItemsCatalogueCategory itemsCatalogueCategory6 = new ItemsCatalogueCategory("Estado/postulación");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory6);
			ItemsCatalogueCategory itemsCatalogueCategory7 = new ItemsCatalogueCategory("Estado/educación");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory7);
			ItemsCatalogueCategory itemsCatalogueCategory8 = new ItemsCatalogueCategory("Estado/Aplicación");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory8);
			ItemsCatalogueCategory itemsCatalogueCategory9 = new ItemsCatalogueCategory("Genero");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory9);
			ItemsCatalogueCategory itemsCatalogueCategory10 = new ItemsCatalogueCategory("Lenguajes");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory10);
			ItemsCatalogueCategory itemsCatalogueCategory11 = new ItemsCatalogueCategory("Nivel de experiencia");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory11);
			ItemsCatalogueCategory itemsCatalogueCategory12 = new ItemsCatalogueCategory("Nivel del idioma");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory12);
			ItemsCatalogueCategory itemsCatalogueCategory13 = new ItemsCatalogueCategory("Puesto al que aspira");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory13);
			ItemsCatalogueCategory itemsCatalogueCategory14 = new ItemsCatalogueCategory("Razón del retiro");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory14);
			ItemsCatalogueCategory itemsCatalogueCategory15 = new ItemsCatalogueCategory("Rol del reclutador");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory15);
			ItemsCatalogueCategory itemsCatalogueCategory16 = new ItemsCatalogueCategory("Relaciones/personal");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory16);
			ItemsCatalogueCategory itemsCatalogueCategory17 = new ItemsCatalogueCategory("Rol");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory17);
			ItemsCatalogueCategory itemsCatalogueCategory18 = new ItemsCatalogueCategory("Rango de salario");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory18);
			ItemsCatalogueCategory itemsCatalogueCategory19 = new ItemsCatalogueCategory("Sucursales");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory19);
			ItemsCatalogueCategory itemsCatalogueCategory20 = new ItemsCatalogueCategory("Tipo de discapacidad");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory20);
			ItemsCatalogueCategory itemsCatalogueCategory21 = new ItemsCatalogueCategory("Tipo de trabajo");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory21);
			ItemsCatalogueCategory itemsCatalogueCategory22 = new ItemsCatalogueCategory("Tipo de estudio");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory22);
			ItemsCatalogueCategory itemsCatalogueCategory23 = new ItemsCatalogueCategory("Tipo de transporte");
			itemsCatalogueCategoryRepository.save(itemsCatalogueCategory23);



			/* CATALOGUE */
			ItemsCatalogue itemsCatalogueAdministracion = new ItemsCatalogue("Administración", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueAdministracion);
			ItemsCatalogue itemsCatalogueAgroindustrial = new ItemsCatalogue("Agroindustrial", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueAgroindustrial);
			ItemsCatalogue itemsCatalogueAgropecuaria = new ItemsCatalogue("Agropecuaría", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueAgropecuaria);
			ItemsCatalogue itemsCatalogueAlimenticia = new ItemsCatalogue("Alimenticia", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueAlimenticia);
			ItemsCatalogue itemsCatalogueArquitectura = new ItemsCatalogue("Arquitectura", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueArquitectura);
			ItemsCatalogue itemsCatalogueArtesanal = new ItemsCatalogue("Artesanal", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueArtesanal);
			ItemsCatalogue itemsCatalogueAtencionCliente = new ItemsCatalogue("Atención al cliente", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueAtencionCliente);
			ItemsCatalogue itemsCatalogueComercio = new ItemsCatalogue("Comercio", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueComercio);
			ItemsCatalogue itemsCatalogueComunicaciones = new ItemsCatalogue("Comunicaciones", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueComunicaciones);
			ItemsCatalogue itemsCatalogueConstruccion = new ItemsCatalogue("Construcción", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueConstruccion);
			ItemsCatalogue itemsCatalogueConsultoria = new ItemsCatalogue("Consultoría", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueConsultoria);
			ItemsCatalogue itemsCatalogueDiseno = new ItemsCatalogue("Diseño", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueDiseno);
			ItemsCatalogue itemsCatalogueEducacion = new ItemsCatalogue("Educación", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueEducacion);
			ItemsCatalogue itemsCatalogueEnergia = new ItemsCatalogue("Energía, minería, petróleo, gas", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueEnergia);
			ItemsCatalogue itemsCatalogueEntretenimiento = new ItemsCatalogue("Entretenimiento, publicidad", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueEntretenimiento);
			ItemsCatalogue itemsCatalogueFarmaceutica = new ItemsCatalogue("Farmacéutica, laboratorio, salud, óptica", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueFarmaceutica);
			ItemsCatalogue itemsCatalogueFinanciera = new ItemsCatalogue("Financiera", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueFinanciera);
			ItemsCatalogue itemsCatalogueGanaderia = new ItemsCatalogue("Ganadería, pesca", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueGanaderia);
			ItemsCatalogue itemsCatalogueGastronomia = new ItemsCatalogue("Gastronomía", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueGastronomia);
			ItemsCatalogue itemsCatalogueGobierno = new ItemsCatalogue("Gobierno", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueGobierno);
			ItemsCatalogue itemsCataloguePerfumeria = new ItemsCatalogue("Perfumería", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCataloguePerfumeria);
			ItemsCatalogue itemsCatalogueImprenta = new ItemsCatalogue("Imprenta", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueImprenta);
			ItemsCatalogue itemsCatalogueIndustrial = new ItemsCatalogue("Industrial", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueIndustrial);
			ItemsCatalogue itemsCatalogueTecnologia = new ItemsCatalogue("Tecnología", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueTecnologia);
			ItemsCatalogue itemsCatalogueInmobiliaria = new ItemsCatalogue("Inmobiliaria", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueInmobiliaria);
			ItemsCatalogue itemsCatalogueJuridica = new ItemsCatalogue("Jurídica", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueJuridica);
			ItemsCatalogue itemsCatalogueLaboratorio = new ItemsCatalogue("Laboratorio", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueLaboratorio);
			ItemsCatalogue itemsCatalogueLegales = new ItemsCatalogue("Legales", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueLegales);
			ItemsCatalogue itemsCatalogueManufactura = new ItemsCatalogue("Manufactura", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueManufactura);
			ItemsCatalogue itemsCataloguePapelera = new ItemsCatalogue("Papelera", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCataloguePapelera);
			ItemsCatalogue itemsCatalogueRecursosHumanos = new ItemsCatalogue("Recursos humanos", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueRecursosHumanos);
			ItemsCatalogue itemsCatalogueSupermercado = new ItemsCatalogue("Supermercado", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueSupermercado);
			ItemsCatalogue itemsCatalogueTelecomunicaciones = new ItemsCatalogue("Telecomunicaciones", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueTelecomunicaciones);
			ItemsCatalogue itemsCatalogueTextil = new ItemsCatalogue("Textil", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueTextil);
			ItemsCatalogue itemsCatalogueTransporte = new ItemsCatalogue("Transporte", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueTransporte);
			ItemsCatalogue itemsCatalogueTurismo = new ItemsCatalogue("Turismo", itemsCatalogueCategory1, true);
			itemsCatalogueRepository.save(itemsCatalogueTurismo);

			ItemsCatalogue itemsCatalogueGenderM = new ItemsCatalogue("Masculino", itemsCatalogueCategory9, true);
			itemsCatalogueRepository.save(itemsCatalogueGenderM);
			ItemsCatalogue itemsCatalogueGenderF = new ItemsCatalogue("Femenino", itemsCatalogueCategory9, true);
			itemsCatalogueRepository.save(itemsCatalogueGenderF);
			ItemsCatalogue itemsCatalogueGenderOther = new ItemsCatalogue("Otro", itemsCatalogueCategory9, true);
			itemsCatalogueRepository.save(itemsCatalogueGenderOther);
			ItemsCatalogue itemsCatalogueSoltero = new ItemsCatalogue("Soltero", itemsCatalogueCategory5, true);
			itemsCatalogueRepository.save(itemsCatalogueSoltero);
			ItemsCatalogue itemsCatalogueCasado = new ItemsCatalogue("Casado", itemsCatalogueCategory5, true);
			itemsCatalogueRepository.save(itemsCatalogueCasado);
			ItemsCatalogue itemsCatalogueConyugue = new ItemsCatalogue("Conyugue", itemsCatalogueCategory5, true);
			itemsCatalogueRepository.save(itemsCatalogueConyugue);
			ItemsCatalogue itemsCatalogueDivorciado = new ItemsCatalogue("Divorciado", itemsCatalogueCategory5, true);
			itemsCatalogueRepository.save(itemsCatalogueDivorciado);
			ItemsCatalogue itemsCatalogueViudo = new ItemsCatalogue("Viudo", itemsCatalogueCategory5, true);
			itemsCatalogueRepository.save(itemsCatalogueViudo);
			ItemsCatalogue itemsCatalogueOtro = new ItemsCatalogue("Otro", itemsCatalogueCategory5, true);
			itemsCatalogueRepository.save(itemsCatalogueOtro);
			ItemsCatalogue itemsCatalogueFullTime = new ItemsCatalogue("Tiempo completo", itemsCatalogueCategory4, true);
			itemsCatalogueRepository.save(itemsCatalogueFullTime);
			ItemsCatalogue itemsCataloguePartTime = new ItemsCatalogue("Tiempo parcial", itemsCatalogueCategory4, true);
			itemsCatalogueRepository.save(itemsCataloguePartTime);
			ItemsCatalogue itemsCatalogueRotaria = new ItemsCatalogue("Rotaria", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueRotaria);
			ItemsCatalogue itemsCatalogueSanJosecito = new ItemsCatalogue("San Josecito", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueSanJosecito);
			ItemsCatalogue itemsCatalogueParamillo = new ItemsCatalogue("Paramillo", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueParamillo);
			ItemsCatalogue itemsCataloguePuebloNuevo = new ItemsCatalogue("Pueblo Nuevo", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCataloguePuebloNuevo);
			ItemsCatalogue itemsCatalogueCastellana = new ItemsCatalogue("Castellana", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueCastellana);
			ItemsCatalogue itemsCatalogueLagunillas = new ItemsCatalogue("Lagunillas", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueLagunillas);
			ItemsCatalogue itemsCatalogueAltoChama = new ItemsCatalogue("Alto Chama", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueAltoChama);
			ItemsCatalogue itemsCatalogueEjido = new ItemsCatalogue("Ejido", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueEjido);
			ItemsCatalogue itemsCataloguePedregosa = new ItemsCatalogue("Pedregosa", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCataloguePedregosa);
			ItemsCatalogue itemsCatalogueGuanare = new ItemsCatalogue("Guanare", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueGuanare);
			ItemsCatalogue itemsCatalogueAcarigua = new ItemsCatalogue("Acarigua", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueAcarigua);
			ItemsCatalogue itemsCatalogueBarquisimeto = new ItemsCatalogue("Barquisimeto", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueBarquisimeto);
			ItemsCatalogue itemsCatalogueCabimas = new ItemsCatalogue("Cabimas", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueCabimas);
			ItemsCatalogue itemsCatalogueSantaBarbara = new ItemsCatalogue("Santa Barbara", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueSantaBarbara);
			ItemsCatalogue itemsCatalogueBarinas = new ItemsCatalogue("Barinas", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueBarinas);
			ItemsCatalogue itemsCatalogueAll = new ItemsCatalogue("Todas", itemsCatalogueCategory19, true);
			itemsCatalogueRepository.save(itemsCatalogueAll);
			ItemsCatalogue itemsCatalogue37 = new ItemsCatalogue("Linea de caja", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue37);
			ItemsCatalogue itemsCatalogue38 = new ItemsCatalogue("Perecederos", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue38);
			ItemsCatalogue itemsCatalogue39 = new ItemsCatalogue("Talento humano", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue39);
			ItemsCatalogue itemsCatalogue40 = new ItemsCatalogue("Sistemas", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue40);
			ItemsCatalogue itemsCatalogue41 = new ItemsCatalogue("Mtto industrial", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue41);
			ItemsCatalogue itemsCatalogue42 = new ItemsCatalogue("Gerente/Coodinador", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue42);
			ItemsCatalogue itemsCatalogue43 = new ItemsCatalogue("Administración", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue43);
			ItemsCatalogue itemsCatalogue44 = new ItemsCatalogue("Self-service", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue44);
			ItemsCatalogue itemsCatalogue45 = new ItemsCatalogue("Servicios generales", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue45);
			ItemsCatalogue itemsCatalogue46 = new ItemsCatalogue("Otro", itemsCatalogueCategory13, true);
			itemsCatalogueRepository.save(itemsCatalogue46);
			ItemsCatalogue itemsCatalogue48 = new ItemsCatalogue("Frances", itemsCatalogueCategory10, true);
			itemsCatalogueRepository.save(itemsCatalogue48);

			ItemsCatalogue itemsCatalogue49 = new ItemsCatalogue("Ingles", itemsCatalogueCategory10, true);
			itemsCatalogueRepository.save(itemsCatalogue49);
			ItemsCatalogue itemsCatalogue50 = new ItemsCatalogue("Italiano", itemsCatalogueCategory10, true);
			itemsCatalogueRepository.save(itemsCatalogue50);
			ItemsCatalogue itemsCatalogue51 = new ItemsCatalogue("Portugues", itemsCatalogueCategory10, true);
			itemsCatalogueRepository.save(itemsCatalogue51);
			ItemsCatalogue itemsCatalogue52 = new ItemsCatalogue("Otro", itemsCatalogueCategory10, true);
			itemsCatalogueRepository.save(itemsCatalogue52);
			ItemsCatalogue itemsCatalogue53 = new ItemsCatalogue("Trainee", itemsCatalogueCategory11, true);
			itemsCatalogueRepository.save(itemsCatalogue53);
			ItemsCatalogue itemsCatalogue54 = new ItemsCatalogue("Junior", itemsCatalogueCategory11, true);
			itemsCatalogueRepository.save(itemsCatalogue54);
			ItemsCatalogue itemsCatalogue55 = new ItemsCatalogue("Semi-senior", itemsCatalogueCategory11, true);
			itemsCatalogueRepository.save(itemsCatalogue55);
			ItemsCatalogue itemsCatalogue56 = new ItemsCatalogue("Senior", itemsCatalogueCategory11, true);
			itemsCatalogueRepository.save(itemsCatalogue56);
			ItemsCatalogue itemsCatalogue57 = new ItemsCatalogue("A1", itemsCatalogueCategory12, true);
			itemsCatalogueRepository.save(itemsCatalogue57);
			ItemsCatalogue itemsCatalogue58 = new ItemsCatalogue("A2", itemsCatalogueCategory12, true);
			itemsCatalogueRepository.save(itemsCatalogue58);
			ItemsCatalogue itemsCatalogue59 = new ItemsCatalogue("B1", itemsCatalogueCategory12, true);
			itemsCatalogueRepository.save(itemsCatalogue59);
			ItemsCatalogue itemsCatalogue60 = new ItemsCatalogue("B2", itemsCatalogueCategory12, true);
			itemsCatalogueRepository.save(itemsCatalogue60);
			ItemsCatalogue itemsCatalogue61 = new ItemsCatalogue("C1", itemsCatalogueCategory12, true);
			itemsCatalogueRepository.save(itemsCatalogue61);
			ItemsCatalogue itemsCatalogue62 = new ItemsCatalogue("Nativo", itemsCatalogueCategory12, true);
			itemsCatalogueRepository.save(itemsCatalogue62);
			ItemsCatalogue itemsCatalogue63 = new ItemsCatalogue("Voluntario", itemsCatalogueCategory14, true);
			itemsCatalogueRepository.save(itemsCatalogue63);
			ItemsCatalogue itemsCatalogue64 = new ItemsCatalogue("Finalización de contrato", itemsCatalogueCategory14, true);
			itemsCatalogueRepository.save(itemsCatalogue64);
			ItemsCatalogue itemsCatalogue65 = new ItemsCatalogue("Despido", itemsCatalogueCategory14, true);
			itemsCatalogueRepository.save(itemsCatalogue65);
			ItemsCatalogue itemsCatalogue66 = new ItemsCatalogue("Abandono laboral", itemsCatalogueCategory14, true);
			itemsCatalogueRepository.save(itemsCatalogue66);
			ItemsCatalogue itemsCatalogue67 = new ItemsCatalogue("Otro", itemsCatalogueCategory14, true);
			itemsCatalogueRepository.save(itemsCatalogue67);
			ItemsCatalogue itemsCatalogue68 = new ItemsCatalogue("Familiar", itemsCatalogueCategory16, true);
			itemsCatalogueRepository.save(itemsCatalogue68);
			ItemsCatalogue itemsCatalogue69 = new ItemsCatalogue("Amigo", itemsCatalogueCategory16, true);
			itemsCatalogueRepository.save(itemsCatalogue69);
			ItemsCatalogue itemsCatalogue70 = new ItemsCatalogue("Conocido", itemsCatalogueCategory16, true);
			itemsCatalogueRepository.save(itemsCatalogue70);
			ItemsCatalogue itemsCatalogue71 = new ItemsCatalogue("Otro", itemsCatalogueCategory16, true);
			itemsCatalogueRepository.save(itemsCatalogue71);

			ItemsCatalogue itemsCatalogue73 = new ItemsCatalogue("Gerente de RRHH", itemsCatalogueCategory17, true);
			itemsCatalogueRepository.save(itemsCatalogue73);
			ItemsCatalogue itemsCatalogue74 = new ItemsCatalogue("Gerente de sistemas", itemsCatalogueCategory17, true);
			itemsCatalogueRepository.save(itemsCatalogue74);
			ItemsCatalogue itemsCatalogue75 = new ItemsCatalogue("0-50", itemsCatalogueCategory18, true);
			itemsCatalogueRepository.save(itemsCatalogue75);
			ItemsCatalogue itemsCatalogue76 = new ItemsCatalogue("50-100", itemsCatalogueCategory18, true);
			itemsCatalogueRepository.save(itemsCatalogue76);
			ItemsCatalogue itemsCatalogue77 = new ItemsCatalogue("100-150", itemsCatalogueCategory18, true);
			itemsCatalogueRepository.save(itemsCatalogue77);
			ItemsCatalogue itemsCatalogue78 = new ItemsCatalogue("150-200", itemsCatalogueCategory18, true);
			itemsCatalogueRepository.save(itemsCatalogue78);
			ItemsCatalogue itemsCatalogue79 = new ItemsCatalogue("200-250", itemsCatalogueCategory18, true);
			itemsCatalogueRepository.save(itemsCatalogue79);
			ItemsCatalogue itemsCatalogue80 = new ItemsCatalogue("250-300", itemsCatalogueCategory18, true);
			itemsCatalogueRepository.save(itemsCatalogue80);
			ItemsCatalogue itemsCatalogue81 = new ItemsCatalogue("Entrevista", itemsCatalogueCategory6, true);
			itemsCatalogueRepository.save(itemsCatalogue81);
			ItemsCatalogue itemsCatalogue82 = new ItemsCatalogue("Entrevista técnica", itemsCatalogueCategory6, true);
			itemsCatalogueRepository.save(itemsCatalogue82);
			ItemsCatalogue itemsCatalogue83 = new ItemsCatalogue("Examenes medicos", itemsCatalogueCategory6, true);
			itemsCatalogueRepository.save(itemsCatalogue83);
			ItemsCatalogue itemsCatalogue84 = new ItemsCatalogue("Evalución medica", itemsCatalogueCategory6, true);
			itemsCatalogueRepository.save(itemsCatalogue84);
			ItemsCatalogue itemsCatalogue85 = new ItemsCatalogue("Pruebas psicotécnicas", itemsCatalogueCategory6, true);
			itemsCatalogueRepository.save(itemsCatalogue85);
			ItemsCatalogue itemsCatalogue86 = new ItemsCatalogue("Otro", itemsCatalogueCategory6, true);
			itemsCatalogueRepository.save(itemsCatalogue86);
			ItemsCatalogue itemsCatalogue87 = new ItemsCatalogue("En curso", itemsCatalogueCategory7, true);
			itemsCatalogueRepository.save(itemsCatalogue87);
			ItemsCatalogue itemsCatalogue88 = new ItemsCatalogue("GraduadoO", itemsCatalogueCategory7, true);
			itemsCatalogueRepository.save(itemsCatalogue88);
			ItemsCatalogue itemsCatalogue89 = new ItemsCatalogue("Abandonado", itemsCatalogueCategory7, true);
			itemsCatalogueRepository.save(itemsCatalogue89);
			ItemsCatalogue itemsCatalogue90 = new ItemsCatalogue("Datos recibidos", itemsCatalogueCategory8, true);
			itemsCatalogueRepository.save(itemsCatalogue90);
			ItemsCatalogue itemsCatalogue91 = new ItemsCatalogue("Curriculum revisado", itemsCatalogueCategory8, true);
			itemsCatalogueRepository.save(itemsCatalogue91);
			ItemsCatalogue itemsCatalogue92 = new ItemsCatalogue("Entrevista programada", itemsCatalogueCategory8, true);
			itemsCatalogueRepository.save(itemsCatalogue92);
			ItemsCatalogue itemsCatalogue93 = new ItemsCatalogue("Examen Psicotécnico programado", itemsCatalogueCategory8, true);
			itemsCatalogueRepository.save(itemsCatalogue93);
			ItemsCatalogue itemsCatalogue94 = new ItemsCatalogue("Contratación ofrecida", itemsCatalogueCategory8, true);
			itemsCatalogueRepository.save(itemsCatalogue94);
			ItemsCatalogue itemsCatalogue95 = new ItemsCatalogue("Contratación aceptada", itemsCatalogueCategory8, true);
			itemsCatalogueRepository.save(itemsCatalogue95);
			ItemsCatalogue itemsCatalogue96 = new ItemsCatalogue("Contratación rechazada", itemsCatalogueCategory8, true);
			itemsCatalogueRepository.save(itemsCatalogue96);
			ItemsCatalogue itemsCatalogue97 = new ItemsCatalogue("Presencial", itemsCatalogueCategory21, true);
			itemsCatalogueRepository.save(itemsCatalogue97);
			ItemsCatalogue itemsCatalogue98 = new ItemsCatalogue("Hibrido", itemsCatalogueCategory21, true);
			itemsCatalogueRepository.save(itemsCatalogue98);
			ItemsCatalogue itemsCatalogue99 = new ItemsCatalogue("Remoto", itemsCatalogueCategory21, true);
			itemsCatalogueRepository.save(itemsCatalogue99);
			ItemsCatalogue itemsCatalogue100 = new ItemsCatalogue("Otro", itemsCatalogueCategory21, true);
			itemsCatalogueRepository.save(itemsCatalogue100);
			ItemsCatalogue itemsCatalogue101 = new ItemsCatalogue("Motora", itemsCatalogueCategory20, true);
			itemsCatalogueRepository.save(itemsCatalogue101);
			ItemsCatalogue itemsCatalogue102 = new ItemsCatalogue("Visual", itemsCatalogueCategory20, true);
			itemsCatalogueRepository.save(itemsCatalogue102);
			ItemsCatalogue itemsCatalogue103 = new ItemsCatalogue("Cognitiva", itemsCatalogueCategory20, true);
			itemsCatalogueRepository.save(itemsCatalogue103);
			ItemsCatalogue itemsCatalogue104 = new ItemsCatalogue("Otro", itemsCatalogueCategory20, true);
			itemsCatalogueRepository.save(itemsCatalogue104);
			ItemsCatalogue itemsCatalogue105 = new ItemsCatalogue("Primaria", itemsCatalogueCategory22, true);
			itemsCatalogueRepository.save(itemsCatalogue105);
			ItemsCatalogue itemsCatalogue106 = new ItemsCatalogue("Bachiller", itemsCatalogueCategory22, true);
			itemsCatalogueRepository.save(itemsCatalogue106);
			ItemsCatalogue itemsCatalogue108 = new ItemsCatalogue("Técnico superior", itemsCatalogueCategory22, true);
			itemsCatalogueRepository.save(itemsCatalogue108);
			ItemsCatalogue itemsCatalogue109 = new ItemsCatalogue("Grado", itemsCatalogueCategory22, true);
			itemsCatalogueRepository.save(itemsCatalogue109);
			ItemsCatalogue itemsCatalogue110 = new ItemsCatalogue("Post-Grado", itemsCatalogueCategory22, true);
			itemsCatalogueRepository.save(itemsCatalogue110);
			ItemsCatalogue itemsCatalogue111 = new ItemsCatalogue("Master", itemsCatalogueCategory22, true);
			itemsCatalogueRepository.save(itemsCatalogue111);
			ItemsCatalogue itemsCatalogue112 = new ItemsCatalogue("Doctorado", itemsCatalogueCategory22, true);
			itemsCatalogueRepository.save(itemsCatalogue112);
			ItemsCatalogue itemsCatalogue113 = new ItemsCatalogue("Otro", itemsCatalogueCategory22, true);
			itemsCatalogueRepository.save(itemsCatalogue113);
			ItemsCatalogue itemsCatalogue114 = new ItemsCatalogue("Publico", itemsCatalogueCategory23, true);
			itemsCatalogueRepository.save(itemsCatalogue114);
			ItemsCatalogue itemsCatalogue115 = new ItemsCatalogue("Privado", itemsCatalogueCategory23, true);
			itemsCatalogueRepository.save(itemsCatalogue115);
			ItemsCatalogue itemsCatalogue116 = new ItemsCatalogue("Otro", itemsCatalogueCategory23, true);
			itemsCatalogueRepository.save(itemsCatalogue116);






		};
	}
}
